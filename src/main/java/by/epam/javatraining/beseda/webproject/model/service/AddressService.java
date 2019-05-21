package by.epam.javatraining.beseda.webproject.model.service;

import by.epam.javatraining.beseda.webproject.model.dao.dependencydao.Dependency;
import by.epam.javatraining.beseda.webproject.model.dao.dependencydao.TaskAddressDependencyDAO;
import by.epam.javatraining.beseda.webproject.model.dao.entitydao.AddressDAO;
import by.epam.javatraining.beseda.webproject.model.entity.route.Address;
import by.epam.javatraining.beseda.webproject.model.entity.route.Task;
import by.epam.javatraining.beseda.webproject.model.exception.daoexception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.model.exception.serviceexception.ServiceLayerException;

public class AddressService extends AbstractService<Address> {

    private Dependency<Address, Task> dependencyDAO = TaskAddressDependencyDAO.getDAO();

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

    public Address getAddressByTaskId(Task task) throws ServiceLayerException {
        Address addr = null;
        if (task != null) {
            try {
                int[] addrId = dependencyDAO.getDependencyId(null,task);
                addr = getEntityById(addrId[0]);
            } catch (DAOLayerException e) {
                throw new ServiceLayerException();
            }
        }
        return addr;
    }
}
