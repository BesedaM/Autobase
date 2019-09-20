package by.epam.javatraining.beseda.webproject.controller.command.implementation.post.admin;

import by.epam.javatraining.beseda.webproject.controller.command.ActionCommand;
import by.epam.javatraining.beseda.webproject.controller.command.util.srcontent.SessionRequestContent;
import by.epam.javatraining.beseda.webproject.entity.user.Customer;
import by.epam.javatraining.beseda.webproject.service.entityservice.CustomerService;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.service.entityservice.ServiceEntityFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import java.util.Map;

import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPAttribute.CUSTOMER;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPParameter.*;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPPath.ADD_NEW_ROUTE_PAGE;
import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;

public class GoToAddNewRoutePage implements ActionCommand {

	private static Logger log = Logger.getLogger(ERROR_LOGGER);
	private ServiceEntityFactory serviceEntityFactory = ServiceEntityFactory.getFactory();
	private CustomerService customerService = serviceEntityFactory.getCustomerService();

	@Override
	public String execute(SessionRequestContent content) {

		Map<String, String[]> requestParam = content.requestParameters();
		Map<String, Object> requestAttributes = content.requestAttributes();
		HttpSession session = content.getSession();
		Integer requestId = Integer.parseInt(requestParam.get(ID)[0]);
		int customerId = Integer.parseInt(requestParam.get(CUSTOMER_ID)[0]);
		try {
			Customer customer = customerService.getEntityById(customerId);
			requestAttributes.put(CUSTOMER, customer);
		} catch (ServiceLayerException e) {
			log.error(e);
		}

		String text = requestParam.get(REQUEST_TEXT)[0];

		session.setAttribute(NEW_REQUEST_ID, requestId);
		session.setAttribute(NEW_REQUEST_TEXT, text);
		return ADD_NEW_ROUTE_PAGE;
	}
}