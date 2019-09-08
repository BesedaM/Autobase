package by.epam.javatraining.beseda.webproject.service.entityservice;

import by.epam.javatraining.beseda.webproject.dao.entitydao.CarInterface;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.entity.car.Bus;
import by.epam.javatraining.beseda.webproject.entity.car.Car;
import by.epam.javatraining.beseda.webproject.entity.car.Truck;
import by.epam.javatraining.beseda.webproject.dao.exception.CarTypeNotPresentException;
import by.epam.javatraining.beseda.webproject.entity.exception.EntityLogicException;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLogicException;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceTechnicalException;

import java.util.List;

import static by.epam.javatraining.beseda.webproject.dao.util.database.DBEnumTable.BUS;
import static by.epam.javatraining.beseda.webproject.dao.util.database.DBEnumTable.TRUCK;

public class CarService extends AbstractEntityService<Car> {

    CarService() {
        super();
        entityDAO = mySQLDAOEntityFactory.getCarDAO();
    }

    /**
     * Creates entityservice WITHOUT ID with the given data
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
        List<Car> carList = null;
        if (carType != null) {
            try {
                carList = ((CarInterface)entityDAO).getCarsByType(carType);
            } catch (DAOLayerException e) {
                throw new ServiceLayerException(e);
            }
        }
        return carList;
    }

    public void updateCarState(int carId, String state) throws ServiceTechnicalException {
        if (carId > 0 && state != null) {
            try {
                ((CarInterface) entityDAO).updateCarState(carId, state);
            } catch (DAOTechnicalException e) {
                throw new ServiceTechnicalException(e);
            }
        }
    }
}
