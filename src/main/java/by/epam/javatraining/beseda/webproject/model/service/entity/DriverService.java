package by.epam.javatraining.beseda.webproject.model.service.entity;

import by.epam.javatraining.beseda.webproject.model.dao.entitydao.DriverDAO;
import by.epam.javatraining.beseda.webproject.model.entity.user.Driver;
import by.epam.javatraining.beseda.webproject.model.entity.user.User;
import by.epam.javatraining.beseda.webproject.model.exception.daoexception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.model.exception.serviceexception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.model.exception.serviceexception.ServiceLogicException;

public class DriverService extends AbstractService<Driver> {

    private DriverService() {
        entityDAO = DriverDAO.getDAO();
    }

    private static class SingletonHolder {
        public static final DriverService instance = new DriverService();
    }

    public static DriverService getService() {
        return SingletonHolder.instance;
    }

    /**
     * Creates entity with the given data
     */
    public Driver createDriver(User userData, String name, String surname, String phone) {
        Driver driver = null;
        if (userData != null
                && name != null && surname != null
                && phone != null) {
            driver = new Driver(userData, name, surname, phone);
        }
        return driver;
    }

    @Override
    public void add(Driver entity) throws ServiceLayerException {
        if (entity != null) {
            try {
                entityDAO.add(entity);
            } catch (DAOLayerException e) {
                throw new ServiceLogicException(e);
            }
        }
    }

}
