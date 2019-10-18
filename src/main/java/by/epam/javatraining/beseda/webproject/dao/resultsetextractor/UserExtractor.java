package by.epam.javatraining.beseda.webproject.dao.resultsetextractor;

import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.LOGIN;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.PASSWORD;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.ID;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.USER_ROLE;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import by.epam.javatraining.beseda.webproject.entity.user.User;

public class UserExtractor implements ResultSetExtractor<User>{

	@Override
	public User extractData(ResultSet result) throws SQLException, DataAccessException {
		User user = null;
		if (result.next()) {
			user = new User();
			user.setRole(result.getString(USER_ROLE));
			user.setId(result.getInt(ID));
			user.setLogin(result.getString(LOGIN));
			user.setPassword(result.getBytes(PASSWORD));
		}
		return user;
	}

	
	
}
