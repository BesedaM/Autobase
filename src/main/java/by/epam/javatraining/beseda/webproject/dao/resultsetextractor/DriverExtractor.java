package by.epam.javatraining.beseda.webproject.dao.resultsetextractor;

import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.ID;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.LOGIN;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.NAME;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.PASSWORD;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.PHONE;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.SURNAME;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.USER_DRIVER;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import by.epam.javatraining.beseda.webproject.entity.user.Driver;

public class DriverExtractor implements ResultSetExtractor<Driver>{

	@Override
	public Driver extractData(ResultSet result) throws SQLException, DataAccessException {
		Driver driver = null;
		if (result.next()) {
			driver = new Driver();
			driver.setId(result.getInt(ID));
			driver.setName(result.getString(NAME));
			driver.setSurname(result.getString(SURNAME));
			driver.setPhone(result.getString(PHONE));
			driver.setLogin(result.getString(LOGIN));
			driver.setPassword(result.getBytes(PASSWORD));
			driver.setRole(USER_DRIVER);
		}
	return driver;
	}

	
}
