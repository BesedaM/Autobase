package by.epam.javatraining.beseda.webproject.controller;

import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPAttribute.DATA_CHANGED;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPAttribute.ERROR_MESSAGE;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPAttribute.PASSWORD_CHANGED;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPAttribute.STATUS_TRUE;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPParameter.COMPANY_NAME;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPParameter.CURRENT_PAGE;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPParameter.EMAIL;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPParameter.NAME;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPParameter.NEW_PASSWORD;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPParameter.NEW_REQUEST;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPParameter.PASSWORD;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPParameter.PASSWORD_CONFIRM;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPParameter.PHONE;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPParameter.SURNAME;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPPath.CUSTOMER_MAIN_PAGE;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPPath.CUSTOMER_PERSONAL_DATA_PAGE;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPSessionAttribute.REQUEST_LIST;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPSessionAttribute.USER_DATA;
import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import by.epam.javatraining.beseda.webproject.controller.util.Decoder;
import by.epam.javatraining.beseda.webproject.entity.Request;
import by.epam.javatraining.beseda.webproject.entity.user.Customer;
import by.epam.javatraining.beseda.webproject.entity.user.User;
import by.epam.javatraining.beseda.webproject.logic.RegisterLogic;
import by.epam.javatraining.beseda.webproject.service.entityservice.CustomerService;
import by.epam.javatraining.beseda.webproject.service.entityservice.RequestService;
import by.epam.javatraining.beseda.webproject.service.entityservice.UserService;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.service.processors.CustomerProcessor;

@Controller
@ResponseBody
@RequestMapping(value= {"/customer"})
public class CustomerController {

	private static Logger log = Logger.getLogger(ERROR_LOGGER);

	@Autowired
	private CustomerProcessor customerProcessor;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private RequestService requestService;

	@Autowired
	private UserService userService;

	
	@GetMapping(value = { "/customer_main_redirect" })
	public ModelAndView gotoCustomerMain() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(CUSTOMER_MAIN_PAGE);
		return mav;
	}

	
	@GetMapping(value = { "/customer_personal_data" })
	public ModelAndView gotoCustomerPersonalData() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(CUSTOMER_PERSONAL_DATA_PAGE);
		return mav;
	}

	
	@PostMapping(value = { "/delete_request" })
	public ModelAndView deleteRequest(@RequestParam String id, @RequestParam String current_page, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(current_page);
		try {
			int requestId = Integer.parseInt(id);
			requestService.delete(requestId);
			customerProcessor.processCustomerData(session);
		} catch (ServiceLayerException e) {
			log.error(this.getClass().getSimpleName() + e);
		}
		return mav;
	}

	
	@PostMapping(value = { "/add_request" })
	public ModelAndView addRequest(@RequestParam String new_request_text, @RequestParam String current_page,
			HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(current_page);
		Customer customer = (Customer) session.getAttribute(USER_DATA);
		try {
			String requestText = Decoder.decode(new_request_text);
			Request request = requestService.createRequest(customer, requestText);
			requestService.add(request);
			session.setAttribute(NEW_REQUEST, request);
		} catch (ServiceLayerException e) {
			log.error(this.getClass().getSimpleName() + " " + e);
		}
		return mav;
	}

	
	@SuppressWarnings("unchecked")
	@PostMapping(value = { "/add_another_request" })
	public ModelAndView addAnotherRequest(@RequestParam String current_page, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(current_page);
		List<Request> requestList = (List<Request>) session.getAttribute(REQUEST_LIST);
		Request newRequest = (Request) session.getAttribute(NEW_REQUEST);
		requestList.add(newRequest);
		session.setAttribute(NEW_REQUEST, null);
		return mav;
	}

	
	@PostMapping(value = { "/change_data" })
	public ModelAndView addChangeData(HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(request.getParameter(CURRENT_PAGE));
		Customer customer = (Customer) session.getAttribute(USER_DATA);
		try {
			String companyName = Decoder.decode(request.getParameter(COMPANY_NAME));
			String name = Decoder.decode(request.getParameter(NAME));
			String surname = Decoder.decode(request.getParameter(SURNAME));
			String phone = request.getParameter(PHONE);
			String email = request.getParameter(EMAIL);
			boolean dataChanged = true;

			if (parameterNonNull(companyName) && parameterNonNull(name) && parameterNonNull(surname)
					&& parameterNonNull(phone) && parameterNonNull(email)) {
				customer.setCompanyName(companyName);
				customer.setName(name);
				customer.setSurname(surname);
				customer.setPhone(phone);
				customer.setEmail(email);
			} else {
				dataChanged = false;
			}
			customerService.update(customer);
			if (dataChanged) {
				request.setAttribute(DATA_CHANGED, STATUS_TRUE);
			}
		} catch (ServiceLayerException e) {
			log.error(e);
		}
		return mav;
	}

	private boolean parameterNonNull(String parameter) {
		return parameter.trim().length() > 0;
	}

	
	@PostMapping(value = { "/change_password" })
	public ModelAndView changePassword(HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(request.getParameter(CURRENT_PAGE));

		String newPassword = request.getParameter(NEW_PASSWORD);
		String password = request.getParameter(PASSWORD);
		String passwordConfirm = request.getParameter(PASSWORD_CONFIRM);

		if (password.equals(passwordConfirm) && RegisterLogic.legalPassword(newPassword)) {
			User user = (User) session.getAttribute(USER_DATA);
			userService.changePassword(user.getId(), newPassword);
			request.setAttribute(PASSWORD_CHANGED, STATUS_TRUE);
		} else {
			request.setAttribute(ERROR_MESSAGE, STATUS_TRUE);
		}
		return mav;
	}

}
