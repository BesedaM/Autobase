package by.epam.javatraining.beseda.webproject.controller.command.implementation.post.admin;

import by.epam.javatraining.beseda.webproject.controller.command.ActionCommand;
import by.epam.javatraining.beseda.webproject.controller.command.util.srcontent.SessionRequestContent;
import by.epam.javatraining.beseda.webproject.entity.Request;
import by.epam.javatraining.beseda.webproject.entity.user.Customer;
import by.epam.javatraining.beseda.webproject.service.dependenceservice.RequestCustomerService;
import by.epam.javatraining.beseda.webproject.service.dependenceservice.ServiceDependenceFactory;
import by.epam.javatraining.beseda.webproject.service.entitybuilder.EntityBuilderFactory;
import by.epam.javatraining.beseda.webproject.service.entitybuilder.RequestBuilder;
import by.epam.javatraining.beseda.webproject.service.entityservice.CustomerService;
import by.epam.javatraining.beseda.webproject.service.entityservice.RequestService;
import by.epam.javatraining.beseda.webproject.service.entityservice.ServiceEntityFactory;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPPath.FULFILLED_REQUESTS_PAGE;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPSessionAttribute.REQUEST_CUSTOMER_MAP;
import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;

public class AdminGetFulfilledRequests implements ActionCommand {

	private static Logger log = Logger.getLogger(ERROR_LOGGER);
	private ServiceEntityFactory serviceEntityFactory = ServiceEntityFactory.getFactory();
	private ServiceDependenceFactory serviceDependenceFactory = ServiceDependenceFactory.getFactory();
	private EntityBuilderFactory entityBuilderFactory = EntityBuilderFactory.getFactory();

	@Override
	public String execute(SessionRequestContent content) {
		Map<Request, Customer> requestCustomerMap = new TreeMap<>();
		HttpSession httpSession = content.getSession();
		RequestService requestService = serviceEntityFactory.getRequestService();
		CustomerService customerService = serviceEntityFactory.getCustomerService();
		RequestCustomerService requestCustomerService = serviceDependenceFactory.getRequestCustomerService();
		RequestBuilder requestBuilder = entityBuilderFactory.getRequestBuilder();

		try {
			int[] requestsId = requestService.getFulfilledRequestsId();

			List<Request> requestList = new ArrayList<>();
			for (int i = 0; i < requestsId.length; i++) {
				Request request = requestBuilder.getEntity(requestsId[i]);
				requestList.add(request);
			}

			for (int i = 0; i < requestList.size(); i++) {
				int customerId = requestCustomerService.getEntity02Id(requestList.get(i));
				Customer customer = customerService.getEntityById(customerId);
				requestCustomerMap.put(requestList.get(i), customer);
			}
			httpSession.setAttribute(REQUEST_CUSTOMER_MAP, requestCustomerMap);

		} catch (ServiceLayerException e) {
			log.error(e);
		}

		return FULFILLED_REQUESTS_PAGE;
	}
}