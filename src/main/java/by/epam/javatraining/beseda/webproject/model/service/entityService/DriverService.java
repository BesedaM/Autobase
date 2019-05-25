package by.epam.javatraining.beseda.webproject.model.service.entityService;

import by.epam.javatraining.beseda.webproject.model.dao.dependencedao.CarDriverDependenceDAO;
import by.epam.javatraining.beseda.webproject.model.dao.entitydao.DriverDAO;
import by.epam.javatraining.beseda.webproject.model.entity.car.Car;
import by.epam.javatraining.beseda.webproject.model.entity.user.Driver;
import by.epam.javatraining.beseda.webproject.model.entity.user.User;
import by.epam.javatraining.beseda.webproject.model.exception.daoexception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.model.exception.entityexception.IllegalEntityIdException;
import by.epam.javatraining.beseda.webproject.model.exception.serviceexception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.model.exception.serviceexception.ServiceLogicException;
import by.epam.javatraining.beseda.webproject.model.exception.serviceexception.ServiceTechnicalException;

public class DriverService extends AbstractService<Driver> {

    private CarDriverDependenceDAO carDriverDependenceDAO = CarDriverDependenceDAO.getDAO();

    public DriverService() {
        entityDAO = DriverDAO.getDAO();
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
