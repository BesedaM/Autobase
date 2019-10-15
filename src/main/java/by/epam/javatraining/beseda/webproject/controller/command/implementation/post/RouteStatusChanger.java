package by.epam.javatraining.beseda.webproject.controller.command.implementation.post;

import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPParameter.CURRENT_PAGE;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPParameter.ROUTE_ID;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPParameter.ROUTE_STATUS_CHANGER;
import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import by.epam.javatraining.beseda.webproject.controller.command.ActionCommand;
import by.epam.javatraining.beseda.webproject.controller.command.util.Decoder;
import by.epam.javatraining.beseda.webproject.controller.command.util.srcontent.SessionRequestContent;
import by.epam.javatraining.beseda.webproject.service.entityservice.RouteService;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.service.processors.DriverProcessor;

public class RouteStatusChanger implements ActionCommand {

    private static Logger log = Logger.getLogger(ERROR_LOGGER);
    
    @Autowired
    private DriverProcessor driverProcessor;
    
    @Autowired
    private RouteService routeService;


    @Override
    public String execute(SessionRequestContent content) {
        Map<String, String[]> parameters = content.requestParameters();
        try {
            String routeStatus = Decoder.decode(parameters.get(ROUTE_STATUS_CHANGER)[0]);
            int route_id = Integer.parseInt(parameters.get(ROUTE_ID)[0]);
            routeService.updateRouteStatus(route_id, routeStatus);

            driverProcessor.processDriverData(content.getSession());
        } catch (ServiceLayerException e) {
            log.error(e);
        }
        return parameters.get(CURRENT_PAGE)[0];
    }


//    private void updateData(HttpSession session) {
//        try {
//            if (session.getAttribute(USER_DATA) != null) {
//                DriverProcessor.processDriverData(session);
//            }
//        } catch (ServiceLayerException e) {
//            log.error(e + " Impossible to update user data");
//        }
//    }

}
