package by.epam.javatraining.beseda.webproject.controller.command.implementation.post;

import by.epam.javatraining.beseda.webproject.controller.command.ActionCommand;
import by.epam.javatraining.beseda.webproject.controller.command.util.Decoder;
import by.epam.javatraining.beseda.webproject.controller.command.util.srcontent.SessionRequestContent;
import by.epam.javatraining.beseda.webproject.entity.Request;
import by.epam.javatraining.beseda.webproject.entity.user.Customer;
import by.epam.javatraining.beseda.webproject.service.entityservice.RequestService;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.service.entityservice.ServiceEntityFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import java.util.Map;

import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPParameter.CURRENT_PAGE;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPParameter.NEW_REQUEST;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPParameter.NEW_REQUEST_TEXT;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPSessionAttribute.USER_DATA;
import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;

public class AddRequestCommand implements ActionCommand {

    private static ServiceEntityFactory serviceEntityFactory = ServiceEntityFactory.getFactory();
    private static Logger log = Logger.getLogger(ERROR_LOGGER);

    @Override
    public String execute(SessionRequestContent content) {
        Map<String, String[]> parameters = content.requestParameters();
        HttpSession session = content.getSession();
        Customer customer = (Customer) session.getAttribute(USER_DATA);

        try {
            RequestService requestService = serviceEntityFactory.getRequestService();
            String requestText = Decoder.decode(parameters.get(NEW_REQUEST_TEXT)[0]);
            Request request = requestService.createRequest(customer, requestText);
            requestService.add(request);
            session.setAttribute(NEW_REQUEST, request);
        } catch (ServiceLayerException e) {
            log.error(this.getClass().getSimpleName() + " " + e);
        }
        return parameters.get(CURRENT_PAGE)[0].replace("/Trucking_company", "");
    }

}