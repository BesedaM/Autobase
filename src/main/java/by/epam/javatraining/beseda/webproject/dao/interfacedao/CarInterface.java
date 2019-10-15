package by.epam.javatraining.beseda.webproject.dao.interfacedao;

import java.util.List;

import by.epam.javatraining.beseda.webproject.entity.car.Car;

public interface CarInterface extends EntityDAO<Car> {

	List<Car> getCarsByType(String carType);

	void updateCarState(int id, String state);
	
	List<Integer> getRoutesId(int carId);
	
	void setDriver(int driverId, int carId);

	int getDriverId(int carId);

	void deleteDriver(int carId);
	
	List<Integer> getActiveRoutesId(int carId);
	
	List<Integer> getActivePlannedRoutesId(int carId);
}