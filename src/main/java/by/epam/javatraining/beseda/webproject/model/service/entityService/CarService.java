package by.epam.javatraining.beseda.webproject.model.service.entityService;

import by.epam.javatraining.beseda.webproject.model.dao.dependencedao.CarDriverDependenceDAO;
import by.epam.javatraining.beseda.webproject.model.dao.entitydao.CarDAO;
import by.epam.javatraining.beseda.webproject.model.dao.entitydao.DriverDAO;
import by.epam.javatraining.beseda.webproject.model.entity.car.Bus;
import by.epam.javatraining.beseda.webproject.model.entity.car.Car;
import by.epam.javatraining.beseda.webproject.model.entity.car.Truck;
import by.epam.javatraining.beseda.webproject.model.entity.user.Driver;
import by.epam.javatraining.beseda.webproject.model.exception.daoexception.CarTypeNotPresentException;
import by.epam.javatraining.beseda.webproject.model.exception.daoexception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.model.exception.daoexception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.model.exception.entityexception.EntityLogicException;
import by.epam.javatraining.beseda.webproject.model.exception.entityexception.user.IllegalCarException;
import by.epam.javatraining.beseda.webproject.model.exception.serviceexception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.model.exception.serviceexception.ServiceLogicException;
import by.epam.javatraining.beseda.webproject.model.exception.serviceexception.ServiceTechnicalException;

import java.util.ArrayList;
import java.util.List;

import static by.epam.javatraining.beseda.webproject.util.database.DBEnumTable.BUS;
import static by.epam.javatraining.beseda.webproject.util.database.DBEnumTable.TRUCK;

public class CarService extends AbstractService<Car> {

    private CarDriverDependenceDAO carDriverDependenceDAO = CarDriverDependenceDAO.getDAO();
    private DriverDAO driverDAO = DriverDAO.getDAO();

    public CarService() {
        entityDAO = CarDAO.getDAO();
    }

    /**
     * Creates entity WITHOUT ID with the given data
     */
    public Car createCar(String carType, String carNumber, String model, String status, String state,
                         int seatsNumber, int capacity) throws ServiceLogicException {
        Car car = null;
        if (carType != null
                && carNumber != null && model != null
                && status != null && state != null
                && (seatsNumber > 0 || capacity >= 0)) {
            try {
                switch (carType) {
                    case BUS:
                        car = new Bus();
                        ((Bus) car).setSeats(seatsNumber);
                        break;
                    case TRUCK:
                        car = new Truck();
                        ((Truck) car).setCapacity(capacity);
                        break;
                    default:
                        throw new CarTypeNotPresentException();
                }
                car.setNumber(carNumber);
                car.setModel(model);
                car.setStatus(status);
                car.setState(state);
            } catch (CarTypeNotPresentException | EntityLogicException e) {
                throw new ServiceLogicException(e);
            }
        }
        return car;
    }

    public List<Car> getCarsByType(String carType) throws ServiceLayerException {
        List<Car> newList = new ArrayList<>();
        if (carType != null) {
            List<Car> carList = getAll();
            for (int i = 0; i < carList.size(); i++) {
                Car car = carList.get(i);
                if (car.getClass().getSimpleName().toUpperCase().equals(carType.toUpperCase())) {
                    newList.add(car);
                }
            }
        }
        return newList;
    }

//    public Car getCarByDriver(Driver driver) throws ServiceLayerException {
//        Car car = null;
//        if(driver!=null) {
//            try {
//                int carId=carDriverDependenceDAO.getEntityIdByDependence(driver);
//                car=entityDAO.getEntityById(carId);
//                driver.setCar(car);
//            } catch (DAOLayerException e) {
//                throw new ServiceLayerException(e);
//            } catch (IllegalCarException e) {
//                throw new ServiceLogicException(e);
//            }
//        }
//        return car;
//    }
//
//    public boolean setDriver(Car car, Driver driver) throws ServiceLayerException {
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
}
