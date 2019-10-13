package by.epam.javatraining.beseda.webproject.dao.interfacedao;


import by.epam.javatraining.beseda.webproject.entity.user.Driver;

public interface DriverInterface extends EntityDAO<Driver> {
	
	int getCarId(int driverId);
}