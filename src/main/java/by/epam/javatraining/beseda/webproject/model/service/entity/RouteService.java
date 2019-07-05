package by.epam.javatraining.beseda.webproject.model.service.entity;

import by.epam.javatraining.beseda.webproject.model.entity.route.Route;

import static by.epam.javatraining.beseda.webproject.model.dao.util.dataloader.DatabaseEnumLoader.ROUTE_STATUS_MAP;

public class RouteService extends AbstractEntityService<Route> {

    public RouteService() {
        super();
        entityDAO = daoEntityFactory.getRouteDAO();
    }

//    private static class SingletonHolder {
//        public static final RouteService instance = new RouteService();
//    }
//
//    public static RouteService getService() {
//        return SingletonHolder.instance;
//    }

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
}
