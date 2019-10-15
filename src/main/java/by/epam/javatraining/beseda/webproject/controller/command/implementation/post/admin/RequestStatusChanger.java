package by.epam.javatraining.beseda.webproject.controller.command.implementation.post.admin;

import by.epam.javatraining.beseda.webproject.controller.command.ActionCommand;
import by.epam.javatraining.beseda.webproject.controller.command.util.Decoder;
import by.epam.javatraining.beseda.webproject.controller.command.util.srcontent.SessionRequestContent;
import by.epam.javatraining.beseda.webproject.entity.Request;
import by.epam.javatraining.beseda.webproject.entity.user.Customer;
import by.epam.javatraining.beseda.webproject.entity.exception.RequestException;
import by.epam.javatraining.beseda.webproject.service.entityservice.RequestService;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.service.entityservice.ServiceEntityFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Set;

import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPParameter.*;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPSessionAttribute.REQUEST_CUSTOMER_MAP;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.ADOPTED_REQUEST;
import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.CommandConstant.CONTEXT_TO_REPLACE;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.CommandConstant.EMPTY_STRING;


public class RequestStatusChanger implements ActionCommand {

	private static Logger log = Logger.getLogger(ERROR_LOGGER);
	private ServiceEntityFactory serviceEntityFactory = ServiceEntityFactory.getFactory();
	private RequestService requestService = serviceEntityFactory.getRequestService();

	@SuppressWarnings("unchecked")
	@Override
	public String execute(SessionRequestContent content) {
		HttpSession httpSession = content.getSession();
		Map<String, String[]> requestParam = content.requestParameters();

		try {
			String newRequestStatus = Decoder.decode(requestParam.get(STATUS)[0]);
			Map<String, Object> requestAttr = content.requestAttributes();
			requestAttr.put(CAR_ID, null);
			int requestId = Integer.parseInt(requestParam.get(ID)[0]);

			Map<Request, Customer> requestCustomerMap = (Map<Request, Customer>) httpSession
					.getAttribute(REQUEST_CUSTOMER_MAP);
			Set<Request> requests = requestCustomerMap.keySet();
			for (Request req : requests) {
				Request request = req;
				if (request.getId() == requestId) {
					Customer customer = requestCustomerMap.get(request);
					requestCustomerMap.remove(request);
					request.setStatus(newRequestStatus);
					requestService.update(request);
					if (newRequestStatus.equals(ADOPTED_REQUEST)) {
						requestCustomerMap.put(request, customer);
					}
				}
			}
		} catch (ServiceLayerException e) {
			log.error(e);
		}
		return requestParam.get(CURRENT_PAGE)[0].replace(CONTEXT_TO_REPLACE, EMPTY_STRING);
	}
}