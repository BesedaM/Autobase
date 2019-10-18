package by.epam.javatraining.beseda.webproject.controller;

import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPAttribute.ERROR_MESSAGE;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPAttribute.STATUS_TRUE;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPPath.ADMIN_MAIN_PAGE;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPPath.CUSTOMER_MAIN_PAGE;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPPath.DRIVER_MAIN_PAGE;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPPath.LOGIN_PAGE;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPSessionAttribute.USER_DATA;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.USER_ADMIN;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.USER_CUSTOMER;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.USER_DRIVER;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.USER_ROLE;
import static by.epam.javatraining.beseda.webproject.util.LoggerName.AUTHORIZATION_LOGGER;
import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import by.epam.javatraining.beseda.webproject.entity.user.User;
import by.epam.javatraining.beseda.webproject.service.entityservice.CustomerService;
import by.epam.javatraining.beseda.webproject.service.entityservice.DriverService;
import by.epam.javatraining.beseda.webproject.service.entityservice.UserService;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLogicException;
import by.epam.javatraining.beseda.webproject.service.processors.CarsDataProcessor;
import by.epam.javatraining.beseda.webproject.service.processors.CustomerProcessor;
import by.epam.javatraining.beseda.webproject.service.processors.DriverProcessor;

@Controller
public class LoginController {

	private static Logger log;
	private static Logger authLog;

	@Autowired
	private DriverService driverService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private CarsDataProcessor carsDataProcessor;
	
	@Autowired
	private CustomerProcessor customerProcessor;
	
	@Autowired
	private DriverProcessor driverProcessor;
	
	{
		log = Logger.getLogger(ERROR_LOGGER);
		authLog = Logger.getLogger(AUTHORIZATION_LOGGER);
	}
	
	
	@GetMapping(value = { "/", "/login", "/index" })
	public ModelAndView gotoLoginPage(HttpSession session) {
		session.invalidate();
		ModelAndView mv = new ModelAndView();
		mv.setViewName(LOGIN_PAGE);
		return mv;
	}

	
	
	@PostMapping(value = { "/first_page" })
	public ModelAndView logIn(@RequestParam String login, @RequestParam String password, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		String page = LOGIN_PAGE;
		try {
			User user = userService.getUserByLoginAndPassword(login, password);
			if (user != null) {
				String userRole = user.getRole();
				session.setAttribute(USER_ROLE, userRole);
				bindUserData(user, session);
				page = definePageByUserRole(userRole);
			} else {
				mav.addObject(ERROR_MESSAGE, STATUS_TRUE);
			}
		} catch (ServiceLayerException e) {
			log.error(e);
		}
		mav.setViewName(page);
		return mav;
	}
	


	private String definePageByUserRole(String userRole) {
		String page = LOGIN_PAGE;
		if (userRole != null) {
			switch (userRole) {
			case USER_ADMIN:
				page = ADMIN_MAIN_PAGE;
				break;
			case USER_CUSTOMER:
				page = CUSTOMER_MAIN_PAGE;
				break;
			case USER_DRIVER:
				page = DRIVER_MAIN_PAGE;
				break;
			}
		}
		return page;
	}

	
	private void bindUserData(User user, HttpSession session) throws ServiceLayerException {
		String userRole = user.getRole();
		int userId = user.getId();
		User fullUserData = null;
		if (userRole != null) {
			switch (userRole) {
			case USER_ADMIN:
				carsDataProcessor.processCarsData(session);
				break;
			case USER_CUSTOMER:
				fullUserData = customerService.getEntityById(userId);
				session.setAttribute(USER_DATA, fullUserData);
				customerProcessor.processCustomerData(session);
				break;
			case USER_DRIVER:
				fullUserData = driverService.getEntityById(userId);
				session.setAttribute(USER_DATA, fullUserData);
				driverProcessor.processDriverData(session);
				break;
			default:
				throw new ServiceLogicException("Unknown user type");

			}
			authLog.trace(fullUserData);
		}
	}

}
