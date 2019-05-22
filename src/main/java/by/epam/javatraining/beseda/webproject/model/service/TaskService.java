package by.epam.javatraining.beseda.webproject.model.service;

import by.epam.javatraining.beseda.webproject.model.dao.dependencedao.Dependency;
import by.epam.javatraining.beseda.webproject.model.dao.dependencedao.TaskAddressToOneDependencyDAO;
import by.epam.javatraining.beseda.webproject.model.dao.dependencedao.TaskRouteToOneDependencyDAO;
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
import java.util.List;

public class TaskService extends AbstractService<Task> {

    private Dependency<Address, Task> addressDependencyDAO = TaskAddressToOneDependencyDAO.getDAO();
    private Dependency<Route,Task> routeDependencyDAO = TaskRouteToOneDependencyDAO.getDAO();

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

    public boolean addAddress(Task task, Address addr) throws ServiceLayerException {
        boolean succeed = false;
        if (task != null && addr != null) {
            try {
                task.setAddress(addr);
                addressDependencyDAO.setDependency(addr, task);
                succeed = true;
            } catch (IllegalAddressException e) {
                throw new ServiceLogicException(e);
            } catch (DAOTechnicalException e) {
                throw new ServiceTechnicalException(e);
            }
        }
        return succeed;
    }

    public boolean removeAddress(Task task, Address address) throws ServiceTechnicalException {
        boolean succeed = false;
        if (task != null && address != null) {
            try {
                task.deleteAddress();
                addressDependencyDAO.deleteDependency(address, task);
                succeed = true;
            } catch (DAOTechnicalException e) {
                throw new ServiceTechnicalException(e);
            }
        }
        return succeed;
    }

    public List<Task> getTasksByRouteId(Route route) throws ServiceTechnicalException {
        List<Task> list = null;
        if (route != null) {
            try {
                int[] taskIdArr = routeDependencyDAO.getDependencyId(route);
                list = entityDAO.getEntitiesByIdList(taskIdArr);
            } catch (DAOLayerException e) {
                throw new ServiceTechnicalException(e);
            }
        }
        return list;
    }

}
