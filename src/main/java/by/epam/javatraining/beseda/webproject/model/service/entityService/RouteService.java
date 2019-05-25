package by.epam.javatraining.beseda.webproject.model.service.entityService;

import by.epam.javatraining.beseda.webproject.model.dao.dependencedao.TaskRouteDependenceDAO;
import by.epam.javatraining.beseda.webproject.model.dao.entitydao.RouteDAO;
import by.epam.javatraining.beseda.webproject.model.dao.entitydao.TaskDAO;
import by.epam.javatraining.beseda.webproject.model.entity.route.Route;
import by.epam.javatraining.beseda.webproject.model.entity.route.Task;
import by.epam.javatraining.beseda.webproject.model.exception.daoexception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.model.exception.daoexception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.model.exception.serviceexception.ServiceTechnicalException;

import java.util.List;

import static by.epam.javatraining.beseda.webproject.util.resourceloader.DatabaseEnumLoader.ROUTE_STATUS_MAP;

public class RouteService extends AbstractService<Route> {

    private TaskRouteDependenceDAO routeTaskDependenceDAO = TaskRouteDependenceDAO.getDAO();
    private TaskDAO taskDAO = TaskDAO.getDAO();

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


//    public boolean addTask(Route route, Task task) throws ServiceTechnicalException {
//        boolean succeed = false;
//        if (task != null) {
//            try {
//                routeTaskDependenceDAO.setDependence(task, route);
//                route.addTask(task);
//                succeed = true;
//            } catch (DAOTechnicalException e) {
//                throw new ServiceTechnicalException(e);
//            }
//        }
//        return succeed;
//    }
//
//    public boolean removeTask(Route route, Task task) throws ServiceTechnicalException {
//        boolean succeed = false;
//        if (task != null && route != null) {
//            try {
//                route.deleteTask(task);
//                routeTaskDependenceDAO.deleteDependence(task);
//                taskDAO.delete(task);
//                succeed = true;
//            } catch (DAOTechnicalException e) {
//                throw new ServiceTechnicalException(e);
//            }
//        }
//        return succeed;
//    }
//
//    public List<Task> getTasksByRoute(Route route) throws ServiceTechnicalException {
//        List<Task> list = null;
//        if (route != null) {
//            try {
//                int[] taskIdArr = routeTaskDependenceDAO.getEntitiesIdByDependenceId(route);
//                list = taskDAO.getEntitiesByIdList(taskIdArr);
//                route.setTasksList(list);
//            } catch (DAOLayerException e) {
//                throw new ServiceTechnicalException(e);
//            }
//        }
//        return list;
//    }
}
