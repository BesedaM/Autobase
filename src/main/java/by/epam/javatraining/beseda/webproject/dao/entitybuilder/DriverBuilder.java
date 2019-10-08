package by.epam.javatraining.beseda.webproject.dao.entitybuilder;

import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.ID;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.LOGIN;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.NAME;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.PASSWORD;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.PHONE;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.SURNAME;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.USER_DRIVER;

import java.sql.ResultSet;
import java.sql.SQLException;

import by.epam.javatraining.beseda.webproject.entity.exception.EntityLogicException;
import by.epam.javatraining.beseda.webproject.entity.user.Driver;

public class DriverBuilder implements EntityBuilder<Driver>{

	protected DriverBuilder() {}
	
	@Override
	public Driver buildEntity(ResultSet result) throws SQLException, EntityLogicException{
        Driver driver = null;
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
        return driver;
	}

}
