package by.epam.javatraining.beseda.webproject.controller;

import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.CommandConstant.MINIMUM_COMPANY_NAME_LENGTH;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPAttribute.ERROR_REGISTER_LOGIN;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPAttribute.ERROR_REGISTER_PASSWORD;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPAttribute.STATUS_TRUE;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPAttribute.UNSAFE_PASSWORD;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPParameter.COMPANY_NAME;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPParameter.EMAIL;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPParameter.LOGIN;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPParameter.NAME;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPParameter.PASSWORD;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPParameter.PASSWORD_CONFIRM;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPParameter.PHONE;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPParameter.SURNAME;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPPath.CUSTOMER_MAIN_PAGE;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPPath.CUSTOMER_REGISTER_PAGE;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPSessionAttribute.USER_DATA;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.CUSTOMER_INDIVIDUAL;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.CUSTOMER_LEGAL;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.USER_CUSTOMER;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.USER_ROLE;
import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import by.epam.javatraining.beseda.webproject.controller.command.util.Decoder;
import by.epam.javatraining.beseda.webproject.entity.user.Customer;
import by.epam.javatraining.beseda.webproject.entity.user.User;
import by.epam.javatraining.beseda.webproject.logic.RegisterLogic;
import by.epam.javatraining.beseda.webproject.service.entityservice.CustomerService;
import by.epam.javatraining.beseda.webproject.service.entityservice.UserService;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;

@Controller
@ResponseBody
@RequestMapping(value= {"/register"})
public class RegisterController {

	private static Logger log = Logger.getLogger(ERROR_LOGGER);

	@Autowired
	private UserService userService;

	@Autowired
	private CustomerService customerService;

	@GetMapping(value = { "/customer" })
	public ModelAndView gotoCustomerRegister() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(CUSTOMER_REGISTER_PAGE);
		return mav;
	}

	@PostMapping(value = "/customer/add")
	public ModelAndView registerCustomer(HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(CUSTOMER_REGISTER_PAGE);
		Map<String, String[]> data = request.getParameterMap();
		String login = data.get(LOGIN)[0];
		String password = data.get(PASSWORD)[0];
		String passwordConfirm = data.get(PASSWORD_CONFIRM)[0];

		try {
			if (isValidUserData(request, login, password, passwordConfirm)) {
				String companyName = Decoder.decode(data.get(COMPANY_NAME)[0]);
				String customerType = defineCustomerType(companyName);
				String name = Decoder.decode(data.get(NAME)[0]);
				String surname = Decoder.decode(data.get(SURNAME)[0]);
				String phone = data.get(PHONE)[0];
				String email = data.get(EMAIL)[0];

				User user = userService.createEntity(login, password, USER_CUSTOMER);
				userService.add(user);

				Customer customer = customerService.createCustomer(user, name, surname, customerType, phone, email,
						companyName);
				customerService.add(customer);
				session.setAttribute(USER_DATA, user);
				session.setAttribute(USER_ROLE, USER_CUSTOMER);
				mav.setViewName(CUSTOMER_MAIN_PAGE);
			}
		} catch (ServiceLayerException e) {
			log.error(e);
		}
		return mav;
	}

	private boolean isValidUserData(HttpServletRequest request, String login, String password, String passwordConfirm) {
		boolean correctData = true;
		try {
			if (userService.loginExists(login)) {
				request.setAttribute(ERROR_REGISTER_LOGIN, STATUS_TRUE);
				correctData = false;
			}
			if (!password.equals(passwordConfirm)) {
				request.setAttribute(ERROR_REGISTER_PASSWORD, STATUS_TRUE);
				correctData = false;
			}
			if (!RegisterLogic.legalPassword(password)) {
				request.setAttribute(UNSAFE_PASSWORD, STATUS_TRUE);
				correctData = false;
			}
		} catch (ServiceLayerException e) {
			log.error(e);
		}
		return correctData;
	}

	private String defineCustomerType(String companyName) {
		String customerType;
		if (companyName == null || companyName.trim().length() < MINIMUM_COMPANY_NAME_LENGTH) {
			customerType = CUSTOMER_INDIVIDUAL;
		} else {
			customerType = CUSTOMER_LEGAL;
		}
		return customerType;
	}

}
