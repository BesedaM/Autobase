package by.epam.javatraining.beseda.webproject.dao.resultsetextractor;

import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.BUILDING;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.CITY;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.COUNTRY;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.DISTRICT;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.HOUSE_NUMBER;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.STREET;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.ID;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import by.epam.javatraining.beseda.webproject.entity.route.Address;

public class AddressExtractor implements ResultSetExtractor<Address> {

	@Override
	public Address extractData(ResultSet result) throws SQLException, DataAccessException {
		Address addr = null;
		if (result.next()) {
			addr = new Address();
			addr.setId(result.getInt(ID));
			addr.setCountry(result.getString(COUNTRY));
			addr.setDistrict(result.getString(DISTRICT));
			addr.setCity(result.getString(CITY));
			addr.setStreet(result.getString(STREET));
			addr.setHouse(result.getInt(HOUSE_NUMBER));
			addr.setBuilding(result.getString(BUILDING));
		}
		return addr;
	}

}