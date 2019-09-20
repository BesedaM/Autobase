package by.epam.javatraining.beseda.webproject.controller.command.implementation.post;

import by.epam.javatraining.beseda.webproject.controller.command.ActionCommand;
import by.epam.javatraining.beseda.webproject.controller.command.implementation.parts.CarsDataProcessor;
import by.epam.javatraining.beseda.webproject.controller.command.implementation.parts.CustomerProcessor;
import by.epam.javatraining.beseda.webproject.controller.command.implementation.parts.DriverProcessor;
import by.epam.javatraining.beseda.webproject.controller.command.util.srcontent.SessionRequestContent;
import by.epam.javatraining.beseda.webproject.entity.user.User;
import by.epam.javatraining.beseda.webproject.service.entityservice.CustomerService;
import by.epam.javatraining.beseda.webproject.service.entityservice.DriverService;
import by.epam.javatraining.beseda.webproject.service.entityservice.UserService;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLogicException;
import by.epam.javatraining.beseda.webproject.service.entityservice.ServiceEntityFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;

import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPAttribute.ERROR_MESSAGE;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPAttribute.STATUS_TRUE;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPParameter.LOGIN;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPParameter.PASSWORD;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPPath.*;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPSessionAttribute.USER_DATA;
import static by.epam.javatraining.beseda.webproject.dao.util.database.DBEnumTable.*;
import static by.epam.javatraining.beseda.webproject.util.LoggerName.AUTHORIZATION_LOGGER;
import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;

public class LoginCommand implements ActionCommand {

	private static Logger log;
	private static Logger authLog;
	private ServiceEntityFactory serviceEntityFactory;
	private DriverService driverService;
	private CustomerService customerService;

	{
		log = Logger.getLogger(ERROR_LOGGER);
		authLog = Logger.getLogger(AUTHORIZATION_LOGGER);
		serviceEntityFactory = ServiceEntityFactory.getFactory();
		driverService = serviceEntityFactory.getDriverService();
		customerService = serviceEntityFactory.getCustomerService();
	}

	@Override
	public String execute(SessionRequestContent content) {
		HttpSession httpSession = content.getSession();

		String page = LOGIN_PAGE;
		String login = content.requestParameters().get(LOGIN)[0];
		String password = content.requestParameters().get(PASSWORD)[0];

		try {
			UserService userService = serviceEntityFactory.getUserService();
			User user = userService.getUserByLoginAndPassword(login, password);
			if (user != null) {
				String userRole = user.getRole();
				httpSession.setAttribute(USER_ROLE, userRole);

				bindUserData(user, httpSession);

				page = definePageByUserRole(userRole);
			} else {
				content.requestAttributes().put(ERROR_MESSAGE, STATUS_TRUE);
			}
		} catch (ServiceLayerException e) {
			log.error(e);
		}
		return page;
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
				CarsDataProcessor.processCarsData(session);
				break;
			case USER_CUSTOMER:
				fullUserData = customerService.getEntityById(userId);
				session.setAttribute(USER_DATA, fullUserData);
				CustomerProcessor.processCustomerData(session);
				break;
			case USER_DRIVER:
				fullUserData = driverService.getEntityById(userId);
				session.setAttribute(USER_DATA, fullUserData);
				DriverProcessor.processDriverData(session);
				break;
			default:
				throw new ServiceLogicException("Unknown user type");

			}
			authLog.trace(fullUserData);
		}
	}

}