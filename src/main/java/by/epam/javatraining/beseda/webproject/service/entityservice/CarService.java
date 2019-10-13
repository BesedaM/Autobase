package by.epam.javatraining.beseda.webproject.service.entityservice;

import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.BUS;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.TRUCK;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.epam.javatraining.beseda.webproject.dao.entitydao.CarDAO;
import by.epam.javatraining.beseda.webproject.dao.exception.CarTypeNotPresentException;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.CarInterface;
import by.epam.javatraining.beseda.webproject.entity.car.Bus;
import by.epam.javatraining.beseda.webproject.entity.car.Car;
import by.epam.javatraining.beseda.webproject.entity.car.Truck;
import by.epam.javatraining.beseda.webproject.entity.exception.EntityLogicException;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLogicException;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceTechnicalException;

@Service
public class CarService extends AbstractEntityService<Car> {

	public CarService() {
		super();
	}

	/**
	 * Creates entity WITHOUT ID with the given data.
	 * 
	 * @param carType
	 * @param carNumber
	 * @param model
	 * @param status
	 * @param state
	 * @param seatsNumber
	 * @param capacity
	 * @return
	 * @throws ServiceLogicException
	 */
	public Car createCar(String carType, String carNumber, String model, String status, String state, int seatsNumber,
			int capacity) throws ServiceLogicException {
		Car car = null;
		if (carType != null && carNumber != null && model != null && status != null && state != null
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

	@Autowired
	public void setDAO(CarDAO carDAO) {
		this.entityDAO = carDAO;
	}

	/**
	 * Returns the list of cars of given type
	 * 
	 * @param carType the type of car
	 * @return list of entities
	 * @throws ServiceLayerException
	 */
	public final List<Car> getCarsByType(String carType) throws ServiceLayerException {
		List<Car> carList = null;
		if (carType != null) {
			try {
				carList = ((CarInterface) entityDAO).getCarsByType(carType);
			} catch (DAOLayerException e) {
				throw new ServiceLayerException(e);
			}
		}
		return carList;
	}

	public final void updateCarState(int carId, String state) throws ServiceTechnicalException {
		if (carId > 0 && state != null) {
			try {
				((CarInterface) entityDAO).updateCarState(carId, state);
			} catch (DAOTechnicalException e) {
				throw new ServiceTechnicalException(e);
			}
		}
	}

	public List<Integer> getRoutesId(int carId) {
		return ((CarInterface) entityDAO).getRoutesId(carId);
	}

	public void setDriver(int driverId, int carId) {
		((CarInterface) entityDAO).setDriver(driverId, carId);
	}

	public int getDriverId(int carId) {
		return ((CarInterface) entityDAO).getDriverId(carId);
	}

	public void deleteDriver(int carId) {
		((CarInterface) entityDAO).deleteDriver(carId);
	}

	public List<Integer> getActiveRoutesId(int carId) {
		return ((CarInterface) entityDAO).getActiveRoutesId(carId);
	}

	public List<Integer> getActivePlannedRoutesId(int carId) {
		return ((CarInterface) entityDAO).getActivePlannedRoutesId(carId);
	}
}