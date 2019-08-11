package by.epam.javatraining.beseda.webproject.model.service.entitybuilder;

import by.epam.javatraining.beseda.webproject.model.entity.Request;
import by.epam.javatraining.beseda.webproject.model.entity.route.Route;
import by.epam.javatraining.beseda.webproject.model.exception.entityexception.request.IllegalRouteException;
import by.epam.javatraining.beseda.webproject.model.service.dependence.RequestRouteService;
import by.epam.javatraining.beseda.webproject.model.service.entity.RequestService;
import by.epam.javatraining.beseda.webproject.model.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.model.service.exception.ServiceLogicException;

public class RequestBuilder extends EntityBuider<Request> {

    private static RequestService requestService = serviceEntityFactory.getRequestService();

    private static RequestRouteService requestRouteService = serviceDependenceFactory.getRequestRouteService();

    private RouteBuilder routeBuilder = new RouteBuilder();

    public Request getEntity(int requestId) throws ServiceLayerException {
        Request request = null;
        if (requestId > 0) {
            request = requestService.getEntityById(requestId);
            int routeId = requestRouteService.getEntity02Id(request);

            Route route = routeBuilder.getEntity(routeId);
            routeBuilder.addCarList(route);
            try {
                if (route != null) {
                    request.setRoute(route);
                }
            } catch (IllegalRouteException e) {
                throw new ServiceLogicException(e);
            }

        }
        return request;
    }

}
