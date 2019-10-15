package by.epam.javatraining.beseda.webproject.controller;

import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPPath.ADMIN_MAIN_PAGE;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPPath.CURRENT_REQUESTS_PAGE;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPPath.FULFILLED_REQUESTS_PAGE;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPPath.NEW_REQUESTS_PAGE;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPPath.REJECTED_REQUESTS_PAGE;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPSessionAttribute.CHANGE_CAR;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPSessionAttribute.CHANGING_ROUTE;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPSessionAttribute.REQUEST_CUSTOMER_MAP;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPSessionAttribute.TASK_TO_CHANGE;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.ADOPTED_REQUEST;
import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import by.epam.javatraining.beseda.webproject.controller.command.util.Decoder;
import by.epam.javatraining.beseda.webproject.entity.Request;
import by.epam.javatraining.beseda.webproject.entity.route.Route;
import by.epam.javatraining.beseda.webproject.entity.user.Customer;
import by.epam.javatraining.beseda.webproject.service.entitybuilder.RouteBuilder;
import by.epam.javatraining.beseda.webproject.service.entityservice.CustomerService;
import by.epam.javatraining.beseda.webproject.service.entityservice.RequestService;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.service.processors.CarsDataProcessor;

@Controller
@ResponseBody
public class AdminRequestsController {

	private static Logger log = Logger.getLogger(ERROR_LOGGER);

	@Autowired
	private RequestService requestService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CarsDataProcessor carsDataProcessor;

	@Autowired
	private RouteBuilder routeBuilder;

	@PostMapping(value = { "/admin/new_requests" })
	public ModelAndView getNewRequestsPage(HttpSession session) {
		ModelAndView mav = new ModelAndView();

		Map<Request, Customer> requestCustomerMap = new TreeMap<>();
		List<Request> requestList = requestService.getNewRequests();
		Collections.sort(requestList);
		for (int i = 0; i < requestList.size(); i++) {
			int customerId = requestService.getCustomerId(requestList.get(i).getId());
			Customer customer = customerService.getEntityById(customerId);
			requestCustomerMap.put(requestList.get(i), customer);
		}
		session.setAttribute(REQUEST_CUSTOMER_MAP, requestCustomerMap);
		mav.setViewName(NEW_REQUESTS_PAGE);
		return mav;
	}

	@PostMapping(value = { "/admin/current_requests" })
	public ModelAndView getCurrentRequestsPage(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(CURRENT_REQUESTS_PAGE);
		Map<Request, Customer> requestCustomerMap = new TreeMap<>();
		List<Request> requests = requestService.getCurrentRequests();
		for (int i = 0; i < requests.size(); i++) {
			Request request = requests.get(i);
			Route route = routeBuilder.getEntity(request.getId());
			if (route != null) {
				routeBuilder.addCarList(route);
				request.setRoute(route);
			}
			int customerId = requestService.getCustomerId(requests.get(i).getId());
			Customer customer = customerService.getEntityById(customerId);
			requestCustomerMap.put(requests.get(i), customer);
		}
		session.setAttribute(REQUEST_CUSTOMER_MAP, requestCustomerMap);
		return mav;
	}

	@PostMapping(value = { "/admin/current_requests_redirect" })
	public ModelAndView gotoCurrentRequestsPage(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(CURRENT_REQUESTS_PAGE);
		session.setAttribute(CHANGE_CAR, null);
		session.setAttribute(CHANGING_ROUTE, null);
		session.setAttribute(TASK_TO_CHANGE, null);
		carsDataProcessor.processCarsData(session);
		return mav;
	}

	@PostMapping(value = { "/admin/fulfilled_requests" })
	public ModelAndView getFulfilledRequestsPage(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(FULFILLED_REQUESTS_PAGE);
		Map<Request, Customer> requestCustomerMap = new TreeMap<>();
		List<Request> requests = requestService.getFulfilledRequests();
		for (int i = 0; i < requests.size(); i++) {
			Request request = requests.get(i);
			Route route = routeBuilder.getEntity(request.getId());
			if (route != null) {
				routeBuilder.addCarList(route);
				request.setRoute(route);
			}
			int customerId = requestService.getCustomerId(requests.get(i).getId());
			Customer customer = customerService.getEntityById(customerId);
			requestCustomerMap.put(requests.get(i), customer);
		}
		session.setAttribute(REQUEST_CUSTOMER_MAP, requestCustomerMap);
		return mav;
	}

	@PostMapping(value = { "/admin/rejected_requests" })
	public ModelAndView getRejectedRequestsPage(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(REJECTED_REQUESTS_PAGE);
		Map<Request, Customer> requestCustomerMap = new TreeMap<>();
		List<Request> requests = requestService.getRejectedRequests();
		Collections.sort(requests);
		for (int i = 0; i < requests.size(); i++) {
			int customerId = requestService.getCustomerId(requests.get(i).getId());
			Customer customer = customerService.getEntityById(customerId);
			requestCustomerMap.put(requests.get(i), customer);
		}
		session.setAttribute(REQUEST_CUSTOMER_MAP, requestCustomerMap);
		return mav;
	}
	
	
	@SuppressWarnings("unchecked")
	@PostMapping(value = { "/admin/new_requests/process_request" })
	public ModelAndView processRequest(@RequestParam String current_page, @RequestParam String status, @RequestParam String id, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(current_page);
		try {
			String newRequestStatus = Decoder.decode(status);
			int requestId = Integer.parseInt(id);
			Map<Request, Customer> requestCustomerMap = (Map<Request, Customer>) session.getAttribute(REQUEST_CUSTOMER_MAP);
			Set<Request> requests = requestCustomerMap.keySet();
			for (Request req : requests) {
				if (req.getId() == requestId) {
					Customer customer = requestCustomerMap.get(req);
					requestCustomerMap.remove(req);
					req.setStatus(newRequestStatus);
					requestService.update(req);
					if (newRequestStatus.equals(ADOPTED_REQUEST)) {
						requestCustomerMap.put(req, customer);
					}
				}
			}
		} catch (ServiceLayerException e) {
			log.error(e);
		}
		return mav;
	}
	
	@PostMapping(value = { "/admin/main" })
	public ModelAndView gotoMain(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(ADMIN_MAIN_PAGE);
		session.removeAttribute(REQUEST_CUSTOMER_MAP);
		return mav;
	}
	
}
