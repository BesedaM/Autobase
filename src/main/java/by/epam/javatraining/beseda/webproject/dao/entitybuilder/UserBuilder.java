package by.epam.javatraining.beseda.webproject.dao.entitybuilder;

import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.LOGIN;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.PASSWORD;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.ID;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.USER_ROLE;

import java.sql.ResultSet;
import java.sql.SQLException;

import by.epam.javatraining.beseda.webproject.entity.exception.EntityLogicException;
import by.epam.javatraining.beseda.webproject.entity.user.User;

public class UserBuilder implements EntityBuilder<User>{

	protected UserBuilder() {}
	
	@Override
	public User buildEntity(ResultSet result) throws SQLException, EntityLogicException{
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
