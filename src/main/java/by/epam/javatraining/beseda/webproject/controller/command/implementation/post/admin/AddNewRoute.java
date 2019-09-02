package by.epam.javatraining.beseda.webproject.controller.command.implementation.post.admin;

import by.epam.javatraining.beseda.webproject.controller.command.ActionCommand;
import by.epam.javatraining.beseda.webproject.controller.command.util.Decoder;
import by.epam.javatraining.beseda.webproject.controller.command.util.srcontent.SessionRequestContent;
import by.epam.javatraining.beseda.webproject.entity.route.Route;
import by.epam.javatraining.beseda.webproject.service.dependenceservice.CarRouteService;
import by.epam.javatraining.beseda.webproject.service.dependenceservice.ServiceDependenceFactory;
import by.epam.javatraining.beseda.webproject.service.entityservice.RouteService;
import by.epam.javatraining.beseda.webproject.service.entityservice.ServiceEntityFactory;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceTechnicalException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Map;

import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPParameter.*;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPSessionAttribute.CURRENT_ROUTE;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPSessionAttribute.TASK_LIST;
import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;

public class AddNewRoute implements ActionCommand {

    private static Logger log = Logger.getLogger(ERROR_LOGGER);
    private ServiceEntityFactory serviceEntityFactory = ServiceEntityFactory.getFactory();
    private ServiceDependenceFactory serviceDependenceFactory = ServiceDependenceFactory.getFactory();
    private RouteService routeService = serviceEntityFactory.getRouteService();
    private CarRouteService carRouteService = serviceDependenceFactory.getCarRouteService();

    @Override
    public String execute(SessionRequestContent content) {
        Map<String, String[]> requestParam = content.requestParameters();
        HttpSession session = content.getSession();
        Route route=null;
        int routeId = -1;

        try {
            String routeName = Decoder.decode(requestParam.get(ROUTE_NAME)[0]);
            routeId = Integer.parseInt(requestParam.get(ID)[0]);
            route = routeService.createRoute(routeId, routeName);
            routeService.add(route);
        } catch (ServiceLayerException e) {
            log.error(e);
        }
        String[] carsId = requestParam.get(CARS_ID);
        for (int i = 0; i < carsId.length; i++) {
            int carId = Integer.parseInt(carsId[i]);
            try {
                carRouteService.addDependence(carId, routeId);
            } catch (ServiceTechnicalException e) {
                log.error(e);
            }
        }
        session.setAttribute(CURRENT_ROUTE, route);
        session.setAttribute(TASK_LIST,new ArrayList());
        return requestParam.get(CURRENT_PAGE)[0];
    }
}
