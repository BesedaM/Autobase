package by.epam.javatraining.beseda.webproject.model.service.dependenceservice;

import by.epam.javatraining.beseda.webproject.model.dao.dependencedao.RequestRouteDependenceDAO;
import by.epam.javatraining.beseda.webproject.model.dao.entitydao.RequestDAO;
import by.epam.javatraining.beseda.webproject.model.dao.entitydao.RouteDAO;
import by.epam.javatraining.beseda.webproject.model.entity.Request;
import by.epam.javatraining.beseda.webproject.model.entity.route.Route;
import by.epam.javatraining.beseda.webproject.model.exception.daoexception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.model.exception.daoexception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.model.exception.entityexception.request.IllegalRequestRouteException;
import by.epam.javatraining.beseda.webproject.model.exception.serviceexception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.model.exception.serviceexception.ServiceLogicException;
import by.epam.javatraining.beseda.webproject.model.exception.serviceexception.ServiceTechnicalException;

public class RequestRouteService {

    private RequestRouteDependenceDAO requestRouteDependenceDAO;

    private RequestRouteService(){
        requestRouteDependenceDAO = RequestRouteDependenceDAO.getDAO();
    }

    private static class SingletonHolder {
        public static final RequestRouteService instance = new RequestRouteService();
    }

    public static RequestRouteService getService() {
        return SingletonHolder.instance;
    }

    public boolean addDependence(Request request, Route route) throws ServiceLayerException {
        boolean succeed = false;
        if (request != null && route != null) {
            try {
                requestRouteDependenceDAO.setDependence(request, route);
                request.setRoute(route);
                succeed = true;
            } catch (DAOTechnicalException e) {
                throw new ServiceTechnicalException(e);
            } catch (IllegalRequestRouteException e) {
                throw new ServiceLogicException(e);
            }
        }
        return succeed;
    }

    public boolean deleteDependence(Request request, Route route) throws ServiceLayerException {
        boolean succeed = false;
        if (request != null && route != null) {
            try {
                requestRouteDependenceDAO.deleteDependence(request);
                request.removeRoute();
                succeed = true;
            } catch (DAOTechnicalException e) {
                throw new ServiceTechnicalException();
            }
        }
        return succeed;
    }

    public Request getRequest(Route route) throws ServiceLayerException {
        Request request = null;
        if (route != null) {
            try {
                int requestId = requestRouteDependenceDAO.getEntityIdByDependence(route);
                request = RequestDAO.getDAO().getEntityById(requestId);
                request.setRoute(route);
            } catch (DAOLayerException e) {
                throw new ServiceLayerException(e);
            } catch (IllegalRequestRouteException e) {
                throw new ServiceLogicException(e);
            }
        }
        return request;
    }

    public Route getRoute(Request request) throws ServiceLayerException {
        Route route = null;
        if (request != null) {
            try {
                int routeId = requestRouteDependenceDAO.getDependenceId(request);
                route = RouteDAO.getDAO().getEntityById(routeId);
                request.setRoute(route);
            } catch (DAOLayerException e) {
                throw new ServiceLayerException(e);
            } catch (IllegalRequestRouteException e) {
                throw new ServiceLogicException(e);
            }
        }
        return route;
    }
}
