package by.epam.javatraining.beseda.webproject.model.service.dependenceservice;

import by.epam.javatraining.beseda.webproject.model.dao.dependencedao.TaskRouteDependenceDAO;
import by.epam.javatraining.beseda.webproject.model.dao.entitydao.RouteDAO;
import by.epam.javatraining.beseda.webproject.model.dao.entitydao.TaskDAO;
import by.epam.javatraining.beseda.webproject.model.entity.route.Route;
import by.epam.javatraining.beseda.webproject.model.entity.route.Task;
import by.epam.javatraining.beseda.webproject.model.exception.daoexception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.model.exception.daoexception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.model.exception.serviceexception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.model.exception.serviceexception.ServiceTechnicalException;

import java.util.List;

public class TaskRouteService {

    private TaskRouteDependenceDAO taskRouteDependenceDAO;

    private TaskRouteService(){
        taskRouteDependenceDAO = TaskRouteDependenceDAO.getDAO();
    }

    private static class SingletonHolder {
        public static final TaskRouteService instance = new TaskRouteService();
    }

    public static TaskRouteService getService() {
        return SingletonHolder.instance;
    }

    public boolean addDependence(Task task, Route route) throws ServiceLayerException {
        boolean succeed = false;
        if (task != null && route != null) {
            try {
                taskRouteDependenceDAO.setDependence(task, route);
                route.addTask(task);
                succeed = true;
            } catch (DAOTechnicalException e) {
                throw new ServiceTechnicalException(e);
            }
        }
        return succeed;
    }

    public boolean deleteDependence(Task task, Route route) throws ServiceLayerException {
        boolean succeed = false;
        if (task != null && route != null) {
            try {
                taskRouteDependenceDAO.deleteDependence(task);
                route.removeTask(task);
                succeed = true;
            } catch (DAOTechnicalException e) {
                throw new ServiceTechnicalException(e);
            }
        }
        return succeed;
    }

    public Route getRoute(Task task) throws ServiceLayerException {
        Route route = null;
        if (task != null) {
            try {
                int routeId = taskRouteDependenceDAO.getDependenceId(task);
                route = RouteDAO.getDAO().getEntityById(routeId);
                route.addTask(task);
            } catch (DAOLayerException e) {
                throw new ServiceLayerException(e);
            }
        }
        return route;
    }

    public List<Task> getTaskList(Route route) throws ServiceLayerException {
        List<Task> taskList = null;
        if (route != null) {
            try {
                int[] tasksId = taskRouteDependenceDAO.getEntitiesIdByDependenceId(route);
                taskList = TaskDAO.getDAO().getEntitiesByIdList(tasksId);
                route.setTasksList(taskList);
            } catch (DAOLayerException e) {
                throw new ServiceLayerException(e);
            }
        }
        return taskList;
    }
}