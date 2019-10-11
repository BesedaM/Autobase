package by.epam.javatraining.beseda.webproject.controller.command.implementation.post.admin;

import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPPath.CURRENT_REQUESTS_PAGE;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPSessionAttribute.REQUEST_CUSTOMER_MAP;
import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.epam.javatraining.beseda.webproject.controller.command.ActionCommand;
import by.epam.javatraining.beseda.webproject.controller.command.util.srcontent.SessionRequestContent;
import by.epam.javatraining.beseda.webproject.entity.Request;
import by.epam.javatraining.beseda.webproject.entity.exception.RequestException;
import by.epam.javatraining.beseda.webproject.entity.route.Route;
import by.epam.javatraining.beseda.webproject.entity.user.Customer;
import by.epam.javatraining.beseda.webproject.service.dependenceservice.RequestCustomerService;
import by.epam.javatraining.beseda.webproject.service.dependenceservice.ServiceDependenceFactory;
import by.epam.javatraining.beseda.webproject.service.entitybuilder.EntityBuilderFactory;
import by.epam.javatraining.beseda.webproject.service.entitybuilder.RouteBuilder;
import by.epam.javatraining.beseda.webproject.service.entityservice.CustomerService;
import by.epam.javatraining.beseda.webproject.service.entityservice.RequestService;
import by.epam.javatraining.beseda.webproject.service.entityservice.ServiceEntityFactory;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLogicException;

public class AdminGetCurrentRequests implements ActionCommand {

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
		RouteBuilder routeBuilder = entityBuilderFactory.getRouteBuilder();
		try {
			List<Request> requests = requestService.getCurrentRequests();
			for (int i = 0; i < requests.size(); i++) {
				Request request = requests.get(i);
				Route route = routeBuilder.getEntity(request.getId());
				if (route != null) {
					routeBuilder.addCarList(route);
					request.setRoute(route);
				}

				int customerId = requestCustomerService.getEntity02Id(requests.get(i));
				Customer customer = customerService.getEntityById(customerId);
				requestCustomerMap.put(requests.get(i), customer);
			}
			httpSession.setAttribute(REQUEST_CUSTOMER_MAP, requestCustomerMap);
		} catch (ServiceLayerException e) {
			log.error(e);
		} catch (RequestException e) {
			log.error(new ServiceLogicException(e));
		}
		return CURRENT_REQUESTS_PAGE;
	}
}