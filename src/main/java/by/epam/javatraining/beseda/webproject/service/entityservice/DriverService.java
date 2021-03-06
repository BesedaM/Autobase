package by.epam.javatraining.beseda.webproject.service.entityservice;

import by.epam.javatraining.beseda.webproject.entity.user.Driver;
import by.epam.javatraining.beseda.webproject.entity.user.User;

public class DriverService extends AbstractEntityService<Driver> {

    DriverService() {
        super();
        entityDAO = mySQLDAOEntityFactory.getDriverDAO();
    }

    /**
     * Creates entity with the given data.
     * 
     * @param userData
     * @param name
     * @param surname
     * @param phone
     * @return
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

}