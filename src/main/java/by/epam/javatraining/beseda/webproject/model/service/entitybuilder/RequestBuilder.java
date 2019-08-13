package by.epam.javatraining.beseda.webproject.model.service.entitybuilder;

import by.epam.javatraining.beseda.webproject.model.entity.Request;
import by.epam.javatraining.beseda.webproject.model.entity.route.Route;
import by.epam.javatraining.beseda.webproject.model.exception.entityexception.request.IllegalRouteException;
import by.epam.javatraining.beseda.webproject.model.service.entityservice.RequestService;
import by.epam.javatraining.beseda.webproject.model.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.model.service.exception.ServiceLogicException;

public class RequestBuilder extends EntityBuider<Request> {

    private static RequestService requestService = serviceEntityFactory.getRequestService();

    private RouteBuilder routeBuilder = new RouteBuilder();

    public Request getEntity(int requestId) throws ServiceLayerException {

        Request request = null;
        if (requestId > 0) {
            request = requestService.getEntityById(requestId);
            Route route = routeBuilder.getEntity(requestId);
            try {
                if (route != null) {
                    routeBuilder.addCarList(route);
                    request.setRoute(route);
                }
            } catch (IllegalRouteException e) {
                throw new ServiceLogicException(e);
            }
        }
        return request;
    }

}
