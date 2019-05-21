package by.epam.javatraining.beseda.webproject.model.service;

import by.epam.javatraining.beseda.webproject.model.dao.entitydao.CarDAO;
import by.epam.javatraining.beseda.webproject.model.entity.car.Bus;
import by.epam.javatraining.beseda.webproject.model.entity.car.Car;
import by.epam.javatraining.beseda.webproject.model.entity.car.Truck;
import by.epam.javatraining.beseda.webproject.model.exception.daoexception.CarTypeNotPresentException;
import by.epam.javatraining.beseda.webproject.model.exception.entityexception.EntityLogicException;
import by.epam.javatraining.beseda.webproject.model.exception.serviceexception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.model.exception.serviceexception.ServiceLogicException;

import java.util.ArrayList;
import java.util.List;

import static by.epam.javatraining.beseda.webproject.util.database.DBEnumTable.BUS;
import static by.epam.javatraining.beseda.webproject.util.database.DBEnumTable.TRUCK;

public class CarService extends AbstractService<Car> {

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
            } catch (CarTypeNotPresentException e) {
                throw new ServiceLogicException(e);
            } catch (EntityLogicException e) {
                throw new ServiceLogicException(e);
            }
        }
        return car;
    }

    public List<Car> getCarsByType(String carType) throws ServiceLayerException {
        List<Car> newList = new ArrayList<>();
        if (carType != null) {
            List<Car> all = getAll();
            for (int i = 0; i < all.size(); i++) {
                Car car = all.get(i);
                if (car.getClass().getSimpleName().toUpperCase().equals(carType.toUpperCase())) {
                    newList.add(car);
                }
            }
        }
        return newList;
    }
}
