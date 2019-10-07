package by.epam.javatraining.beseda.webproject.dao.entitybuilder;

import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.CAR_NUMBER;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.MODEL;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.SEATS_NUMBER;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.BUS;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.CAR_STATE;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.CAR_STATUS;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.CAR_TYPE;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.ID;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.TRUCK;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.TRUCK_CAPACITY;

import java.sql.ResultSet;
import java.sql.SQLException;

import by.epam.javatraining.beseda.webproject.dao.exception.CarTypeNotPresentException;
import by.epam.javatraining.beseda.webproject.entity.car.Bus;
import by.epam.javatraining.beseda.webproject.entity.car.Car;
import by.epam.javatraining.beseda.webproject.entity.car.Truck;
import by.epam.javatraining.beseda.webproject.entity.exception.EntityLogicException;

public class CarBuilder implements EntityBuilder<Car> {

	protected CarBuilder() {}
	
	@Override
	public Car buildEntity(ResultSet result) throws SQLException, EntityLogicException, CarTypeNotPresentException {
		Car car = null;
		if (result != null) {
			String carType = result.getString(CAR_TYPE);				
			switch (carType) {
			case BUS:
				car = new Bus();
				((Bus) car).setSeats(result.getInt(SEATS_NUMBER));
				break;
			case TRUCK:
				car = new Truck();
				((Truck) car).setCapacity(result.getInt(TRUCK_CAPACITY));
				break;
			default:
				throw new CarTypeNotPresentException();
			}
			car.setId(result.getInt(ID));
			car.setModel(result.getString(MODEL));
			car.setNumber(result.getString(CAR_NUMBER));
			car.setState(result.getString(CAR_STATE));
			car.setStatus(result.getString(CAR_STATUS));
		}
		return car;
	}

}
