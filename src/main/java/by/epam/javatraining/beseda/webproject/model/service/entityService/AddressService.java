package by.epam.javatraining.beseda.webproject.model.service.entityService;

import by.epam.javatraining.beseda.webproject.model.dao.dependencedao.TaskAddressDependenceDAO;
import by.epam.javatraining.beseda.webproject.model.dao.entitydao.AddressDAO;
import by.epam.javatraining.beseda.webproject.model.dao.entitydao.TaskDAO;
import by.epam.javatraining.beseda.webproject.model.entity.route.Address;
import by.epam.javatraining.beseda.webproject.model.entity.route.Task;
import by.epam.javatraining.beseda.webproject.model.exception.daoexception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.model.exception.serviceexception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.model.exception.serviceexception.ServiceTechnicalException;

import java.util.List;

public class AddressService extends AbstractService<Address> {

    private TaskAddressDependenceDAO taskAddressDependenceDAO = TaskAddressDependenceDAO.getDAO();
    private TaskDAO taskDAO = TaskDAO.getDAO();

    public AddressService() {
        entityDAO = AddressDAO.getDAO();
    }

    /**
     * Creates entity WITHOUT ID with the given data
     */
    public Address createAddress(String country, String district, String city, String street, int house, String building) {
        Address addr = null;
        if (country != null && district != null && city != null
                && street != null && house >= 0 && building != null) {
            addr = new Address(country, district, city, street, house, building);
        }
        return addr;
    }

//    public Address getAddressByTask(Task task) throws ServiceLayerException {
//        Address addr = null;
//        if (task != null) {
//            try {
//                int addrId = taskAddressDependenceDAO.getDependenceId(task);
//                addr = getEntityById(addrId);
//            } catch (DAOLayerException e) {
//                throw new ServiceLayerException();
//            }
//        }
//        return addr;
//    }
//
//    public List<Task> getTasksByAddress(Address address) throws ServiceTechnicalException {
//        List<Task> list = null;
//        if (address != null) {
//            try {
//                int[] taskIdArr = taskAddressDependenceDAO.getEntitiesIdByDependenceId(address);
//                list = taskDAO.getEntitiesByIdList(taskIdArr);
//            } catch (DAOLayerException e) {
//                throw new ServiceTechnicalException(e);
//            }
//        }
//        return list;
//    }
}
