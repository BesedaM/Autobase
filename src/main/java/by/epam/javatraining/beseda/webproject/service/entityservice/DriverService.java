package by.epam.javatraining.beseda.webproject.service.entityservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.epam.javatraining.beseda.webproject.dao.entitydao.DriverDAO;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.DriverInterface;
import by.epam.javatraining.beseda.webproject.entity.user.Driver;
import by.epam.javatraining.beseda.webproject.entity.user.User;

@Service
public class DriverService extends AbstractEntityService<Driver> {

	public DriverService() {
		super();
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
		if (userData != null && name != null && surname != null && phone != null) {
			driver = new Driver(userData, name, surname, phone);
		}
		return driver;
	}

	@Autowired
	public void setDAO(DriverDAO driverDAO) {
		this.entityDAO = driverDAO;
	}

	public int getCarId(int driverId) {
		return ((DriverInterface) entityDAO).getCarId(driverId);
	}
}