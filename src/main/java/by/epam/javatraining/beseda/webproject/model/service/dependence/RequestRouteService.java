package by.epam.javatraining.beseda.webproject.model.service.dependence;

import by.epam.javatraining.beseda.webproject.model.entity.Request;
import by.epam.javatraining.beseda.webproject.model.entity.route.Route;

public class RequestRouteService extends OneToOneDependenceService<Request, Route>{

//    private RequestRouteDependenceDAO requestRouteDependenceDAO;

    public RequestRouteService(){
        super();
        dependenceDAO = DAODependenceFactory.getRequestRouteDAO();
    }

//    private static class SingletonHolder {
//        public static final RequestRouteService instance = new RequestRouteService();
//    }
//
//    public static RequestRouteService getService() {
//        return SingletonHolder.instance;
//    }

//    public void addDependence(Request request, Route route) throws ServiceTechnicalException {
////        boolean succeed = false;
//        if (request != null && route != null) {
//            try {
//                requestRouteDependenceDAO.setDependence(request, route);
////                request.setRoute(route);
////                succeed = true;
//            } catch (DAOTechnicalException e) {
//                throw new ServiceTechnicalException(e);
////            } catch (IllegalRequestRouteException e) {
////                throw new ServiceLogicException(e);
//            }
//        }
////        return succeed;
//    }

//    public boolean deleteDependence(Request request, Route route) throws ServiceTechnicalException {
//        boolean succeed = false;
//        if (request != null && route != null) {
//            try {
//                requestRouteDependenceDAO.deleteDependence(request);
//                request.removeRoute();
//                succeed = true;
//            } catch (DAOTechnicalException e) {
//                throw new ServiceTechnicalException();
//            }
//        }
//        return succeed;
//    }

//    public Request getRequest(Route route) throws ServiceLayerException {
//        Request request = null;
//        if (route != null) {
//            try {
//                int requestId = requestRouteDependenceDAO.getEntityIdByDependence(route);
//                request = RequestDAO.getDAO().getEntityById(requestId);
////                request.setRoute(route);
//            } catch (DAOLayerException e) {
//                throw new ServiceLayerException(e);
//            } catch (IllegalRequestRouteException e) {
//                throw new ServiceLogicException(e);
//            }
//        }
//        return request;
//    }

//    public Route getRoute(Request request) throws ServiceLayerException {
//        Route route = null;
//        if (request != null) {
//            try {
//                int routeId = requestRouteDependenceDAO.getDependenceId(request);
//                route = RouteDAO.getDAO().getEntityById(routeId);
////                request.setRoute(route);
//            } catch (DAOLayerException e) {
//                throw new ServiceLayerException(e);
//            } catch (IllegalRequestRouteException e) {
//                throw new ServiceLogicException(e);
//            }
//        }
//        return route;
//    }
}
