package by.epam.javatraining.beseda.webproject.dao.entitybuilder;

import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.BUILDING;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.CITY;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.COUNTRY;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.DISTRICT;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.HOUSE_NUMBER;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.ID;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.STREET;

import java.sql.ResultSet;
import java.sql.SQLException;

import by.epam.javatraining.beseda.webproject.entity.exception.EntityLogicException;
import by.epam.javatraining.beseda.webproject.entity.route.Address;

public class AddressBuilder implements EntityBuilder<Address> {

	protected AddressBuilder(){}
	
	@Override
	public Address buildEntity(ResultSet result)  throws SQLException, EntityLogicException{
		Address addr = null;
		if (result != null) {
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
