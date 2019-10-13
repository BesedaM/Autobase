package by.epam.javatraining.beseda.webproject.controller.command.implementation.post.admin;

import by.epam.javatraining.beseda.webproject.controller.command.ActionCommand;
import by.epam.javatraining.beseda.webproject.controller.command.util.srcontent.SessionRequestContent;
import by.epam.javatraining.beseda.webproject.entity.Request;
import by.epam.javatraining.beseda.webproject.entity.user.Customer;
import by.epam.javatraining.beseda.webproject.service.entityservice.CustomerService;
import by.epam.javatraining.beseda.webproject.service.entityservice.RequestService;
import by.epam.javatraining.beseda.webproject.service.entityservice.ServiceEntityFactory;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPPath.REJECTED_REQUESTS_PAGE;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPSessionAttribute.REQUEST_CUSTOMER_MAP;
import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;

public class AdminGetRejectedRequests implements ActionCommand {

	private static Logger log = Logger.getLogger(ERROR_LOGGER);
	private ServiceEntityFactory serviceEntityFactory = ServiceEntityFactory.getFactory();

	@Override
	public String execute(SessionRequestContent content) {
		Map<Request, Customer> requestCustomerMap = new TreeMap<>();
		HttpSession httpSession = content.getSession();
		RequestService requestService = serviceEntityFactory.getRequestService();
		CustomerService customerService = serviceEntityFactory.getCustomerService();
		
		try {
			List<Request> requests = requestService.getRejectedRequests();
			Collections.sort(requests);
			for (int i = 0; i < requests.size(); i++) {
				int customerId = requestService.getCustomerId(requests.get(i).getId());
				Customer customer = customerService.getEntityById(customerId);
				requestCustomerMap.put(requests.get(i), customer);
			}
			httpSession.setAttribute(REQUEST_CUSTOMER_MAP, requestCustomerMap);
		} catch (ServiceLayerException e) {
			log.error(e);
		}
		return REJECTED_REQUESTS_PAGE;
	}
}