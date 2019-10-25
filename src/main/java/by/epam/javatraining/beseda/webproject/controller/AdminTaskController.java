package by.epam.javatraining.beseda.webproject.controller;


import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPParameter.BUILDING;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPParameter.CITY;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPParameter.COUNTRY;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPParameter.CURRENT_PAGE;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPParameter.DATE;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPParameter.DETAILS;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPParameter.DISTRICT;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPParameter.HOUSE;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPParameter.ROUTE_ID;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPParameter.STREET;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPParameter.TIME;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPSessionAttribute.ADD_TASK_FLAG;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPSessionAttribute.CHANGE_CAR;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPSessionAttribute.CHANGING_ROUTE;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPSessionAttribute.STATUS_TRUE;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPSessionAttribute.TASK_LIST;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPSessionAttribute.TASK_TO_CHANGE;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.USER_ADMIN;
import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.epam.javatraining.beseda.webproject.controller.util.CurrentPageProcessor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import by.epam.javatraining.beseda.webproject.controller.util.Decoder;
import by.epam.javatraining.beseda.webproject.controller.util.constant.CommandConstant;
import by.epam.javatraining.beseda.webproject.entity.route.Address;
import by.epam.javatraining.beseda.webproject.entity.route.Route;
import by.epam.javatraining.beseda.webproject.entity.route.Task;
import by.epam.javatraining.beseda.webproject.service.entityservice.AddressService;
import by.epam.javatraining.beseda.webproject.service.entityservice.TaskService;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;

@Controller
@PreAuthorize("hasAuthority('" + USER_ADMIN + "')")
@ResponseBody
public class AdminTaskController {

	private static Logger log = Logger.getLogger(ERROR_LOGGER);
	
	@Autowired
	private TaskService taskService;

	@Autowired
	private AddressService addressService;
	
	
	@PostMapping(value = { "/admin/tasks/add_task" })
	public ModelAndView addTask(@RequestParam String current_page, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(CurrentPageProcessor.processPage(current_page));
		session.setAttribute(ADD_TASK_FLAG, STATUS_TRUE);
		return mav;
	}

	@PostMapping(value = { "/admin/tasks/delete_task" })
	public ModelAndView deleteTask(@RequestParam String current_page, @RequestParam String id, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(CurrentPageProcessor.processPage(current_page));
		Route route = (Route) session.getAttribute(CHANGING_ROUTE);
		int taskId = Integer.parseInt(id);
		taskService.delete(taskId);
		route.removeTask(taskId);
		return mav;
	}

	
	@PostMapping(value = { "/admin/tasks/change_task" })
	public ModelAndView changeTask(@RequestParam String current_page, @RequestParam String id, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(CurrentPageProcessor.processPage(current_page));
		
		int taskId = Integer.parseInt(id);
		Route route = (Route) session.getAttribute(CHANGING_ROUTE);
		List<Task> taskList = route.getTasksList();
		Task changingTask = null;
		for (Task task : taskList) {
			if (task.getId() == taskId) {
				changingTask = task;
			}
		}
		session.setAttribute(CHANGE_CAR, null);
		session.setAttribute(TASK_TO_CHANGE, changingTask);
		
		return mav;
	}
	
	
	@PostMapping(value = { "/admin/tasks/process_task" })
	public ModelAndView processTask(HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(CurrentPageProcessor.processPage(request.getParameter(CURRENT_PAGE)));
		
		Task taskToChange = null;
		Route route = null;
		try {

			if (session.getAttribute(TASK_TO_CHANGE) != null) {
				taskToChange = (Task) session.getAttribute(TASK_TO_CHANGE);
			}

			int routeId = Integer.parseInt(request.getParameter(ROUTE_ID));
			String details = Decoder.decode(request.getParameter(DETAILS));

			Date time = getTime(request);
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(time);

			Address address = getAddress(request);
			addressService.add(address);

			Task newTask = taskService.createTask(calendar, details);
			if (taskToChange != null) {
				newTask.setId(taskToChange.getId());
				taskService.update(newTask);
			} else {
				taskService.add(newTask);
				taskService.setRoute(routeId, newTask.getId());
			}
			taskService.setAddress(address.getId(),newTask.getId());
			newTask.setAddress(address);

			if (taskToChange != null) {
				route = (Route) session.getAttribute(CHANGING_ROUTE);
				route.removeTask(taskToChange.getId());
				route.addTask(newTask);
				session.setAttribute(TASK_TO_CHANGE, null);
			} else if (session.getAttribute(ADD_TASK_FLAG) != null) {
				route = (Route) session.getAttribute(CHANGING_ROUTE);
				route.addTask(newTask);
				session.setAttribute(ADD_TASK_FLAG, null);
			} else {
				List<Task> taskList = (List<Task>) session.getAttribute(TASK_LIST);
				taskList.add(newTask);
			}

		} catch (ServiceLayerException  e) {
			log.error(e);
		}
		
		return mav;
	}
	
	
	private Address getAddress(HttpServletRequest request) throws ServiceLayerException {
		String country = Decoder.decode(request.getParameter(COUNTRY));
		String district = Decoder.decode(request.getParameter(DISTRICT));
		String city = Decoder.decode(request.getParameter(CITY));
		String street = Decoder.decode(request.getParameter(STREET));
		String houseStr = Decoder.decode(request.getParameter(HOUSE));
		int house = 0;
		if (houseStr.length() > 0) {
			house = Integer.parseInt(houseStr);
		}
		String building = Decoder.decode(request.getParameter(BUILDING));
		return addressService.createAddress(country, district, city, street, house, building);
	}

	
	private Date getTime(HttpServletRequest request) throws ServiceLayerException {
		String date = Decoder.decode(request.getParameter(DATE));
		String time = Decoder.decode(request.getParameter(TIME));

		SimpleDateFormat dateFormat = new SimpleDateFormat(CommandConstant.DATE_FORMAT);
		SimpleDateFormat timeFormat = new SimpleDateFormat(CommandConstant.TIME_FORMAT);
		timeFormat.setTimeZone(TimeZone.getTimeZone(CommandConstant.TIMEZONE));
		Date parsedDate = null;
		Date parsedTime = null;
		try {
			parsedDate = dateFormat.parse(date);
			parsedTime = timeFormat.parse(time);
		} catch (ParseException e) {
			throw new ServiceLayerException(e);
		}
		return new Date(parsedDate.getTime() + parsedTime.getTime());
	}
}
