package by.epam.javatraining.beseda.webproject.model.service.dependence;

import by.epam.javatraining.beseda.webproject.model.dao.dependencedao.TaskAddressDependenceDAO;
import by.epam.javatraining.beseda.webproject.model.dao.entitydao.AddressDAO;
import by.epam.javatraining.beseda.webproject.model.dao.entitydao.TaskDAO;
import by.epam.javatraining.beseda.webproject.model.entity.route.Address;
import by.epam.javatraining.beseda.webproject.model.entity.route.Task;
import by.epam.javatraining.beseda.webproject.model.exception.daoexception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.model.exception.daoexception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.model.exception.entityexception.task.IllegalAddressException;
import by.epam.javatraining.beseda.webproject.model.exception.serviceexception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.model.exception.serviceexception.ServiceLogicException;

import java.util.List;

public class TaskAddressService {

    private TaskAddressDependenceDAO taskAddressDependenceDAO;

    private TaskAddressService(){
        taskAddressDependenceDAO = TaskAddressDependenceDAO.getDAO();
    }

    private static class SingletonHolder {
        public static final TaskAddressService instance = new TaskAddressService();
    }

    public static TaskAddressService getService() {
        return SingletonHolder.instance;
    }

    public boolean setDependence(Task task, Address address) throws ServiceLayerException {
        boolean succeed = false;
        if (task != null && address != null) {
            try {
                taskAddressDependenceDAO.setDependence(task, address);
                task.setAddress(address);
                succeed = true;
            } catch (DAOTechnicalException e) {
                throw new ServiceLayerException(e);
            } catch (IllegalAddressException e) {
                throw new ServiceLogicException(e);
            }
        }
        return succeed;
    }

    public boolean deleteDependence(Task task, Address address) throws ServiceLayerException {
        boolean succeed = false;
        if (task != null && address != null) {
            try {
                taskAddressDependenceDAO.deleteDependence(task);
                task.removeAddress();
                succeed = true;
            } catch (DAOTechnicalException e) {
                throw new ServiceLayerException(e);
            }
        }
        return succeed;
    }

    public Address getAddress(Task task) throws ServiceLayerException {
        Address address = null;
        if (task != null) {
            try {
                int addressId = taskAddressDependenceDAO.getDependenceId(task);
                address = AddressDAO.getDAO().getEntityById(addressId);
                task.setAddress(address);
            } catch (DAOLayerException e) {
                throw new ServiceLayerException(e);
            } catch (IllegalAddressException e) {
                throw new ServiceLogicException(e);
            }
        }
        return address;
    }

    public List<Task> getTaskList(Address address) throws ServiceLayerException {
        List<Task> list = null;
        if (address != null) {
            try {
                int[] tasksId = taskAddressDependenceDAO.getEntitiesIdByDependenceId(address);
                list = TaskDAO.getDAO().getEntitiesByIdList(tasksId);
            } catch (DAOLayerException e) {
                throw new ServiceLayerException(e);
            }
        }
        return list;
    }
}
