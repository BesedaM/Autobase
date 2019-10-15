package by.epam.javatraining.beseda.webproject.dao.mapper;

import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.CAR_NUMBER;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.ID;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.MODEL;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.SEATS_NUMBER;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.BUS;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.CAR_STATE;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.CAR_STATUS;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.CAR_TYPE;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.TRUCK;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.TRUCK_CAPACITY;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import by.epam.javatraining.beseda.webproject.entity.car.Bus;
import by.epam.javatraining.beseda.webproject.entity.car.Car;
import by.epam.javatraining.beseda.webproject.entity.car.Truck;

public class CarMapper implements RowMapper<Car> {

	@Override
	public Car mapRow(ResultSet result, int rowNum) throws SQLException {
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
