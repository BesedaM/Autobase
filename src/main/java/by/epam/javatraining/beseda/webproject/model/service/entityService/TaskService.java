package by.epam.javatraining.beseda.webproject.model.service.entityService;

import by.epam.javatraining.beseda.webproject.model.dao.dependencedao.TaskAddressDependenceDAO;
import by.epam.javatraining.beseda.webproject.model.dao.dependencedao.TaskRouteDependenceDAO;
import by.epam.javatraining.beseda.webproject.model.dao.entitydao.RouteDAO;
import by.epam.javatraining.beseda.webproject.model.dao.entitydao.TaskDAO;
import by.epam.javatraining.beseda.webproject.model.entity.route.Address;
import by.epam.javatraining.beseda.webproject.model.entity.route.Route;
import by.epam.javatraining.beseda.webproject.model.entity.route.Task;
import by.epam.javatraining.beseda.webproject.model.exception.daoexception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.model.exception.daoexception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.model.exception.entityexception.task.IllegalAddressException;
import by.epam.javatraining.beseda.webproject.model.exception.serviceexception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.model.exception.serviceexception.ServiceLogicException;
import by.epam.javatraining.beseda.webproject.model.exception.serviceexception.ServiceTechnicalException;

import java.util.GregorianCalendar;

public class TaskService extends AbstractService<Task> {

    private TaskAddressDependenceDAO taskAddressDependenceDAO = TaskAddressDependenceDAO.getDAO();
    private TaskRouteDependenceDAO taskRouteDependenceDAO = TaskRouteDependenceDAO.getDAO();
    private RouteDAO routeDAO = RouteDAO.getDAO();

    public TaskService() {
        entityDAO = TaskDAO.getDAO();
    }

    /**
     * Creates entity WITHOUT ID with the given data
     */
    public Task createTask(GregorianCalendar time, String details) {
        Task task = null;
        if (time != null && details != null) {
            task = new Task(time, details);
        }
        return task;
    }

//    public boolean setAddress(Task task, Address addr) throws ServiceLayerException {
//        boolean succeed = false;
//        if (task != null && addr != null) {
//            try {
//                task.setAddress(addr);
//                taskAddressDependenceDAO.setDependence(task, addr);
//                succeed = true;
//            } catch (IllegalAddressException e) {
//                throw new ServiceLogicException(e);
//            } catch (DAOTechnicalException e) {
//                throw new ServiceTechnicalException(e);
//            }
//        }
//        return succeed;
//    }
//
//    public boolean removeAddress(Task task, Address address) throws ServiceTechnicalException {
//        boolean succeed = false;
//        if (task != null && address != null) {
//            try {
//                taskAddressDependenceDAO.deleteDependence(task);
//                task.removeAddress();
//                succeed = true;
//            } catch (DAOTechnicalException e) {
//                throw new ServiceTechnicalException(e);
//            }
//        }
//        return succeed;
//    }
//
//    public Route getRouteByTaskId(Task task) throws ServiceTechnicalException {
//        Route route = null;
//        if (task != null) {
//            try {
//                int idRoute = taskRouteDependenceDAO.getDependenceId(task);
//                route = routeDAO.getEntityById(idRoute);
//            } catch (DAOLayerException e) {
//                throw new ServiceTechnicalException(e);
//            }
//        }
//        return route;
//    }
}
