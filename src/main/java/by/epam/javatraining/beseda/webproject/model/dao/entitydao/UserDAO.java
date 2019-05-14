package by.epam.javatraining.beseda.webproject.model.dao.entitydao;

import by.epam.javatraining.beseda.webproject.model.entity.user.User;
import by.epam.javatraining.beseda.webproject.model.exception.DAOexception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.util.resourceloader.DatabaseEnumLoader;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.epam.javatraining.beseda.webproject.util.database.DBEntityTableName.LOGIN;
import static by.epam.javatraining.beseda.webproject.util.database.DBEntityTableName.PASSWORD;
import static by.epam.javatraining.beseda.webproject.util.database.DBEnumTable.USER_ROLE;
import static by.epam.javatraining.beseda.webproject.util.database.SQLQuery.*;

public class UserDAO extends AbstractDAO<User> {

    private static UserDAO instance = null;


    private UserDAO() {
        super();
    }

    public static UserDAO getDAO() {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }

    public User getUserByLoginAndPassword(String login, byte[] password) throws DAOTechnicalException {
        PreparedStatement st = null;
        User user;
        try {
            st = connector.prepareStatement(SELECT_USER_BY_LOGIN_AND_PASSWORD);
            st.setString(1, login);
            st.setBytes(2, password);
            ResultSet res = st.executeQuery();
            res.first();
            user = createEntity(res);
        } catch (SQLException e) {
            throw new DAOTechnicalException("Error retrieving data from database", e);
        } finally {
            closeStatement(st);
        }
        return user;
    }

    public User getUserByLogin(String login) throws DAOTechnicalException {
        PreparedStatement st = null;
        User user;
        try {
            st = connector.prepareStatement(SELECT_USER_BY_LOGIN);
            st.setString(1, login);
            ResultSet res = st.executeQuery();
            res.first();
            user = createEntity(res);
        } catch (SQLException e) {
            throw new DAOTechnicalException("Error retrieving data from database", e);
        } finally {
            closeStatement(st);
        }
        return user;
    }

    @Override
    protected User createEntity(ResultSet result) throws SQLException {
        User user = new User();
        user.setRole(result.getString(USER_ROLE));
        user.setId(result.getInt(USER_ID));
        user.setLogin(result.getString(LOGIN));
        user.setPassword(result.getBytes(PASSWORD));
        return user;
    }

    @Override
    protected String getAllStatement() {
        return SELECT_ALL_USERS;
    }

    @Override
    protected String findEntityByIdStatement() {
        return SELECT_USER_BY_ID;
    }

    @Override
    protected String deleteStatement(){
        return DELETE_USER_BY_ID;
    }

    @Override
    protected String addStatement() {
        return ADD_NEW_USER;
    }

    @Override
    protected String updateStatement() {
        return UPDATE_USER;
    }

    @Override
    protected int updateIdParameterNumber() {
        return 4;
    }

    @Override
    protected void setDataOnPreparedStatement(PreparedStatement st, User user) throws SQLException {
        int roleIndex = DatabaseEnumLoader.USER_ROLE_MAP.getKey(user.getRole());
        st.setString(1, user.getLogin());
        st.setBytes(2, user.getPassword());
        st.setInt(3, roleIndex);
    }

}
