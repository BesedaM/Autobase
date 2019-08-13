package by.epam.javatraining.beseda.webproject.model.command.implementation.post;

import by.epam.javatraining.beseda.webproject.model.command.ActionCommand;
import by.epam.javatraining.beseda.webproject.model.command.implementation.parts.DriverProcessor;
import by.epam.javatraining.beseda.webproject.model.command.util.Decoder;
import by.epam.javatraining.beseda.webproject.model.command.util.srcontent.SessionRequestContent;
import by.epam.javatraining.beseda.webproject.model.service.entityservice.RouteService;
import by.epam.javatraining.beseda.webproject.model.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.model.service.factory.ServiceEntityFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import java.util.Map;

import static by.epam.javatraining.beseda.webproject.model.command.util.constant.JSPParameter.*;
import static by.epam.javatraining.beseda.webproject.model.command.util.constant.JSPSessionAttribute.USER_DATA;
import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;

public class RouteStatusChanger implements ActionCommand {

    private static Logger log = Logger.getLogger(ERROR_LOGGER);
    private static ServiceEntityFactory serviceEntityFactory = ServiceEntityFactory.getFactory();


    @Override
    public String execute(SessionRequestContent content) {
        Map<String, String[]> parameters = content.requestParameters();
        try {
            String routeStatus = Decoder.decode(parameters.get(ROUTE_STATUS_CHANGER)[0]);
            int route_id = Integer.parseInt(parameters.get(ROUTE_ID)[0]);
            RouteService routeService = serviceEntityFactory.getRouteService();
            routeService.updateRouteStatus(route_id, routeStatus);

            updateData(content.getSession());
        } catch (ServiceLayerException e) {
            log.error(e);
        }
        return parameters.get(CURRENT_PAGE)[0];
    }


    private void updateData(HttpSession session) {
        try {
            if (session.getAttribute(USER_DATA) != null) {
                DriverProcessor.processDriverData(session);
            }
        } catch (ServiceLayerException e) {
            log.error(e + " Impossible to update user data");
        }
    }

}
