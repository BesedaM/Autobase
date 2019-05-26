package by.epam.javatraining.beseda.webproject.model.service.dependenceservice;

import by.epam.javatraining.beseda.webproject.model.dao.dependencedao.CarDriverDependenceDAO;
import by.epam.javatraining.beseda.webproject.model.dao.entitydao.CarDAO;
import by.epam.javatraining.beseda.webproject.model.dao.entitydao.DriverDAO;
import by.epam.javatraining.beseda.webproject.model.entity.car.Car;
import by.epam.javatraining.beseda.webproject.model.entity.user.Driver;
import by.epam.javatraining.beseda.webproject.model.exception.daoexception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.model.exception.daoexception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.model.exception.entityexception.user.IllegalCarException;
import by.epam.javatraining.beseda.webproject.model.exception.serviceexception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.model.exception.serviceexception.ServiceLogicException;
import by.epam.javatraining.beseda.webproject.model.exception.serviceexception.ServiceTechnicalException;

public class CarDriverService {

    private CarDriverDependenceDAO carDriverDependenceDAO;

    private CarDriverService() {
        carDriverDependenceDAO = CarDriverDependenceDAO.getDAO();
    }

    private static class SingletonHolder {
        public static final CarDriverService instance = new CarDriverService();
    }

    public static CarDriverService getService() {
        return SingletonHolder.instance;
    }


    public boolean addDependence(Car car, Driver driver) throws ServiceLayerException {
        boolean succeed = false;
        if (car != null && driver != null) {
            try {
                carDriverDependenceDAO.setDependence(car, driver);
                driver.setCar(car);
                succeed = true;
            } catch (DAOTechnicalException e) {
                throw new ServiceTechnicalException();
            } catch (IllegalCarException e) {
                throw new ServiceLogicException(e);
            }
        }
        return succeed;
    }

    public boolean deleteDependence(Car car, Driver driver) throws ServiceLayerException {
        boolean succeed = false;
        if (car != null && driver != null) {
            try {
                carDriverDependenceDAO.deleteDependence(car);
                driver.removeCar();
                succeed = true;
            } catch (DAOTechnicalException e) {
                throw new ServiceTechnicalException();
            }
        }
        return succeed;
    }

    public Car getCar(Driver driver) throws ServiceLayerException {
        Car car = null;
        if (driver != null) {
            try {
                int carId = carDriverDependenceDAO.getEntityIdByDependence(driver);
                car = CarDAO.getDAO().getEntityById(carId);
                driver.setCar(car);
            } catch (DAOLayerException e) {
                throw new ServiceLayerException(e);
            } catch (IllegalCarException e) {
                throw new ServiceLogicException(e);
            }
        }
        return car;
    }

    public Driver getDriver(Car car) throws ServiceLayerException {
        Driver driver = null;
        if (car != null) {
            try {
                int driverId = carDriverDependenceDAO.getDependenceId(car);
                driver = DriverDAO.getDAO().getEntityById(driverId);
                driver.setCar(car);
            } catch (DAOLayerException e) {
                throw new ServiceLayerException(e);
            } catch (IllegalCarException e) {
                throw new ServiceLogicException(e);
            }
        }
        return driver;
    }
}
