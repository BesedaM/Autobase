package by.epam.javatraining.beseda.webproject.controller.command.implementation.post;

import by.epam.javatraining.beseda.webproject.controller.command.ActionCommand;
import by.epam.javatraining.beseda.webproject.controller.command.util.srcontent.SessionRequestContent;
import by.epam.javatraining.beseda.webproject.service.entityservice.RequestService;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.service.processors.CustomerProcessor;
import by.epam.javatraining.beseda.webproject.service.entityservice.ServiceEntityFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import java.util.Map;

import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPParameter.CURRENT_PAGE;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPParameter.ID;
import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.CommandConstant.CONTEXT_TO_REPLACE;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.CommandConstant.EMPTY_STRING;

public class RequestDeleteCommand implements ActionCommand {

	private static ServiceEntityFactory serviceEntityFactory = ServiceEntityFactory.getFactory();
	private static Logger log = Logger.getLogger(ERROR_LOGGER);

	@Override
	public String execute(SessionRequestContent content) {
		Map<String, String[]> parameters = content.requestParameters();
		HttpSession session = content.getSession();

		try {
			RequestService requestService = serviceEntityFactory.getRequestService();
			int requestId = Integer.parseInt(parameters.get(ID)[0]);
			requestService.delete(requestId);
			CustomerProcessor.processCustomerData(session);
		} catch (ServiceLayerException e) {
			log.error(this.getClass().getSimpleName() + e);
		}
		return parameters.get(CURRENT_PAGE)[0].replace(CONTEXT_TO_REPLACE, EMPTY_STRING);
	}

}
