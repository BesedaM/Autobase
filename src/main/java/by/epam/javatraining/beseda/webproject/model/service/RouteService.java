package by.epam.javatraining.beseda.webproject.model.service;

import by.epam.javatraining.beseda.webproject.model.dao.dependencydao.Dependency;
import by.epam.javatraining.beseda.webproject.model.dao.dependencydao.RouteTaskDependencyDAO;
import by.epam.javatraining.beseda.webproject.model.dao.entitydao.RouteDAO;
import by.epam.javatraining.beseda.webproject.model.entity.route.Route;
import by.epam.javatraining.beseda.webproject.model.entity.route.Task;
import by.epam.javatraining.beseda.webproject.model.exception.daoexception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.model.exception.serviceexception.ServiceTechnicalException;

import static by.epam.javatraining.beseda.webproject.util.resourceloader.DatabaseEnumLoader.ROUTE_STATUS_MAP;

public class RouteService extends AbstractService<Route> {

    private Dependency<Route, Task> taskDependencyDAO = RouteTaskDependencyDAO.getDAO();

    public RouteService() {
        entityDAO = RouteDAO.getDAO();
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


    public boolean addTask(Route route, Task task) throws ServiceTechnicalException {
        boolean succeed = false;
        if (task != null) {
            try {
                route.addTask(task);
                taskDependencyDAO.setDependency(route, task);
                succeed = true;
            } catch (DAOTechnicalException e) {
                throw new ServiceTechnicalException(e);
            }
        }
        return succeed;
    }


    public boolean removeTask(Route route, Task task) throws ServiceTechnicalException {
        boolean succeed = false;
        if (task != null && route != null) {
            try {
                route.deleteTask(task);
                taskDependencyDAO.deleteDependency(route, task);
                succeed = true;
            } catch (DAOTechnicalException e) {
                throw new ServiceTechnicalException(e);
            }
        }
        return succeed;
    }
}
