package by.epam.javatraining.beseda.webproject.controller;

import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPAttribute.CUSTOMER;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPParameter.CARS_ID;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPParameter.CURRENT_PAGE;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPParameter.CUSTOMER_ID;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPParameter.ID;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPParameter.NEW_REQUEST_ID;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPParameter.NEW_REQUEST_TEXT;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPParameter.REQUEST_TEXT;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPParameter.ROUTE_NAME;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPPath.ADD_NEW_ROUTE_PAGE;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPSessionAttribute.CURRENT_ROUTE;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPSessionAttribute.TASK_LIST;
import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import by.epam.javatraining.beseda.webproject.controller.command.util.Decoder;
import by.epam.javatraining.beseda.webproject.entity.route.Route;
import by.epam.javatraining.beseda.webproject.entity.route.Task;
import by.epam.javatraining.beseda.webproject.entity.user.Customer;
import by.epam.javatraining.beseda.webproject.service.entityservice.CustomerService;
import by.epam.javatraining.beseda.webproject.service.entityservice.RouteService;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;

@Controller
@ResponseBody
public class AdminRouteController {

	private static Logger log = Logger.getLogger(ERROR_LOGGER);

	@Autowired
	private CustomerService customerService;

	@Autowired
	private RouteService routeService;

	@PostMapping(value = { "/admin/create_route" })
	public ModelAndView gotoAddNewRoute(HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		Integer requestId = Integer.parseInt(request.getParameter(ID));
		int customerId = Integer.parseInt(request.getParameter(CUSTOMER_ID));
		Customer customer = customerService.getEntityById(customerId);
		request.setAttribute(CUSTOMER, customer);

		String text = request.getParameter(REQUEST_TEXT);
		session.setAttribute(NEW_REQUEST_ID, requestId);
		session.setAttribute(NEW_REQUEST_TEXT, text);
		mav.setViewName(ADD_NEW_ROUTE_PAGE);
		return mav;
	}

	@PostMapping(value = { "/admin/add_new_route" })
	public ModelAndView addNewRoute(HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView();

		Route route = null;
		int routeId = -1;
		try {
			String routeName = Decoder.decode(request.getParameter(ROUTE_NAME));
			routeId = Integer.parseInt(request.getParameter(ID));
			route = routeService.createRoute(routeId, routeName);
			routeService.add(route);
		} catch (ServiceLayerException e) {
			log.error(e);
		}
		String[] carsId = request.getParameterValues(CARS_ID);
		for (int i = 0; i < carsId.length; i++) {
			int carId = Integer.parseInt(carsId[i]);
			routeService.addCar(routeId, carId);
		}
		session.setAttribute(CURRENT_ROUTE, route);
		session.setAttribute(TASK_LIST, new ArrayList<Task>());

		mav.setViewName(request.getParameter(CURRENT_PAGE));
		return mav;
	}
	
	
}
