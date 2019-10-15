package by.epam.javatraining.beseda.webproject.dao.mapper;

import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.LOGIN;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.PASSWORD;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.ID;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.USER_ROLE;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import by.epam.javatraining.beseda.webproject.entity.user.User;

public class UserMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet result, int rowNum) throws SQLException {
		User user = null;
		if (result != null) {
			user = new User();
			user.setRole(result.getString(USER_ROLE));
			user.setId(result.getInt(ID));
			user.setLogin(result.getString(LOGIN));
			user.setPassword(result.getBytes(PASSWORD));
		}
		return user;
	}

}
