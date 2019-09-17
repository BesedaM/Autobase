package by.epam.javatraining.beseda.webproject.controller.command.implementation.post;

import by.epam.javatraining.beseda.webproject.controller.command.ActionCommand;
import by.epam.javatraining.beseda.webproject.controller.command.util.Decoder;
import by.epam.javatraining.beseda.webproject.controller.command.util.srcontent.SessionRequestContent;
import by.epam.javatraining.beseda.webproject.entity.user.Customer;
import by.epam.javatraining.beseda.webproject.entity.user.User;
import by.epam.javatraining.beseda.webproject.logic.RegisterLogic;
import by.epam.javatraining.beseda.webproject.service.entityservice.CustomerService;
import by.epam.javatraining.beseda.webproject.service.entityservice.UserService;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceTechnicalException;
import by.epam.javatraining.beseda.webproject.service.entityservice.ServiceEntityFactory;
import org.apache.log4j.Logger;

import java.util.Map;

import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPAttribute.*;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPParameter.*;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPPath.CUSTOMER_MAIN_PAGE;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPPath.CUSTOMER_REGISTER_PAGE;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPSessionAttribute.USER_DATA;
import static by.epam.javatraining.beseda.webproject.dao.util.database.DBEnumTable.*;
import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.CommandConstant.MINIMUM_COMPANY_NAME_LENGTH;

public class CustomerRegisterCommand implements ActionCommand {

	private static Logger log = Logger.getLogger(ERROR_LOGGER);
	private UserService userService = ServiceEntityFactory.getFactory().getUserService();
	private CustomerService customerService = ServiceEntityFactory.getFactory().getCustomerService();

	@Override
	public String execute(SessionRequestContent content) {
		String page = CUSTOMER_REGISTER_PAGE;
		Map<String, String[]> data = content.requestParameters();
		Map<String, Object> attributes = content.requestAttributes();
		String login = data.get(LOGIN)[0];
		String password = data.get(PASSWORD)[0];
		String passwordConfirm = data.get(PASSWORD_CONFIRM)[0];

		try {
			if (isValidUserData(attributes, login, password, passwordConfirm)) {
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
				content.getSession().setAttribute(USER_DATA, user);
				content.getSession().setAttribute(USER_ROLE, USER_CUSTOMER);
				page = CUSTOMER_MAIN_PAGE;
			}
		} catch (ServiceLayerException e) {
			log.error(e);
		}
		return page;
	}

	private boolean isValidUserData(Map<String, Object> attributes, String login, String password,
			String passwordConfirm) {
		boolean correctData = true;
		try {
			if (userService.loginExists(login)) {
				attributes.put(ERROR_REGISTER_LOGIN, STATUS_TRUE);
				correctData = false;
			}
			if (!password.equals(passwordConfirm)) {
				attributes.put(ERROR_REGISTER_PASSWORD, STATUS_TRUE);
				correctData = false;
			}
			if (!RegisterLogic.legalPassword(password)) {
				attributes.put(UNSAFE_PASSWORD, STATUS_TRUE);
				correctData = false;
			}
		} catch (ServiceTechnicalException e) {
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