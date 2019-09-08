package by.epam.javatraining.beseda.webproject.service.entityservice;

import by.epam.javatraining.beseda.webproject.dao.entitydao.RouteDAO;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.entity.route.Route;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceTechnicalException;

import static by.epam.javatraining.beseda.webproject.dao.util.dataloader.DatabaseEnumLoader.ROUTE_STATUS_MAP;

public class RouteService extends AbstractEntityService<Route> {

    RouteService() {
        super();
        entityDAO = mySQLDAOEntityFactory.getRouteDAO();
    }

    /**
     * Creates entityservice WITHOUT ID with the given data
     */
    public Route createRoute(int id,String routeName) {
        Route route = null;
        if (routeName != null) {
            String routeStatus = ROUTE_STATUS_MAP.get(1);
            route = new Route(id, routeName, routeStatus);
        }
        return route;
    }

    public void updateRouteStatus(int id, String status) throws ServiceTechnicalException {
        if (id > 0 && status != null) {
            try {
                ((RouteDAO)entityDAO).updateRouteStatus(id,status);
            } catch (DAOTechnicalException e) {
                throw new ServiceTechnicalException(e);
            }
        }
    }
}
