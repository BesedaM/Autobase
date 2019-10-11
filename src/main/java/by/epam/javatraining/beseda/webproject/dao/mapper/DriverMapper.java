package by.epam.javatraining.beseda.webproject.dao.mapper;

import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.ID;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.LOGIN;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.NAME;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.PASSWORD;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.PHONE;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.SURNAME;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.USER_DRIVER;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import by.epam.javatraining.beseda.webproject.entity.exception.EntityLogicException;
import by.epam.javatraining.beseda.webproject.entity.user.Driver;

public class DriverMapper implements RowMapper<Driver> {

//	private Logger log = Logger.getLogger(ERROR_LOGGER);

	@Override
	public Driver mapRow(ResultSet result, int rowNum) throws SQLException {
		Driver driver = null;
		try {
			if (result != null) {
				driver = new Driver();
				driver.setId(result.getInt(ID));
				driver.setName(result.getString(NAME));
				driver.setSurname(result.getString(SURNAME));
				driver.setPhone(result.getString(PHONE));
				driver.setLogin(result.getString(LOGIN));
				driver.setPassword(result.getBytes(PASSWORD));
				driver.setRole(USER_DRIVER);
			}
		} catch (EntityLogicException e) {
//			log.error(e);
		}
		return driver;
	}

}
