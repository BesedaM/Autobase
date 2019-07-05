package by.epam.javatraining.beseda.webproject.model.service.entity;

import by.epam.javatraining.beseda.webproject.model.entity.user.Driver;
import by.epam.javatraining.beseda.webproject.model.entity.user.User;

public class DriverService extends AbstractEntityService<Driver> {

    public DriverService() {
        super();
        entityDAO = daoEntityFactory.getDriverDAO();
    }

//    private static class SingletonHolder {
//        public static final DriverService instance = new DriverService();
//    }
//
//    public static DriverService getService() {
//        return SingletonHolder.instance;
//    }

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

}
