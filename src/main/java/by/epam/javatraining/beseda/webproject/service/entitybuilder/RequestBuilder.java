package by.epam.javatraining.beseda.webproject.service.entitybuilder;

import by.epam.javatraining.beseda.webproject.entity.Request;
import by.epam.javatraining.beseda.webproject.entity.route.Route;
import by.epam.javatraining.beseda.webproject.entity.exception.request.IllegalRouteException;
import by.epam.javatraining.beseda.webproject.service.entityservice.RequestService;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLogicException;

public class RequestBuilder extends EntityBuilder<Request> {

    private static RequestService requestService = serviceEntityFactory.getRequestService();
    private static RouteBuilder routeBuilder = new RouteBuilder();

    RequestBuilder() {
        super();
    }

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
