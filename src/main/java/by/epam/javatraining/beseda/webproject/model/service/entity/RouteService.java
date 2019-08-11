package by.epam.javatraining.beseda.webproject.model.service.entity;

import by.epam.javatraining.beseda.webproject.model.dao.entitydao.RouteDAO;
import by.epam.javatraining.beseda.webproject.model.dao.exception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.model.entity.route.Route;
import by.epam.javatraining.beseda.webproject.model.service.exception.ServiceTechnicalException;

import static by.epam.javatraining.beseda.webproject.model.dao.util.dataloader.DatabaseEnumLoader.ROUTE_STATUS_MAP;

public class RouteService extends AbstractEntityService<Route> {

    public RouteService() {
        super();
        entityDAO = daoEntityFactory.getRouteDAO();
    }

    /**
     * Creates entity WITHOUT ID with the given data
     */
    public Route createRoute(String routeName) {
        Route route = null;
        if (routeName != null) {
            String routeStatus = ROUTE_STATUS_MAP.get(1);
            route = new Route(routeName, routeStatus);
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
