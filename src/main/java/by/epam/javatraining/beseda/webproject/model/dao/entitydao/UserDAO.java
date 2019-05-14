package by.epam.javatraining.beseda.webproject.model.dao.entitydao;

import by.epam.javatraining.beseda.webproject.model.entity.user.User;
import by.epam.javatraining.beseda.webproject.model.exception.DAOexception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.model.exception.DAOexception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.util.database.DBEntityTableName;
import by.epam.javatraining.beseda.webproject.util.resourceloader.DatabaseEnumLoader;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static by.epam.javatraining.beseda.webproject.util.database.DBEntityTableName.*;
import static by.epam.javatraining.beseda.webproject.util.database.DBEnumTable.USER_ROLE;
import static by.epam.javatraining.beseda.webproject.util.database.SQLQuery.*;

public class UserDAO extends AbstractDAO<User> {

    private Logger log = Logger.getLogger(UserDAO.class.getSimpleName());
    private static UserDAO instance = null;


    private UserDAO() {
        super();
        this.dbTableName = DBEntityTableName.T_USERS;
    }

    public static UserDAO getDAO() {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }

    @Override
    public List<User> getAll() throws DAOLayerException {
        List<User> list = new ArrayList<>();
        Statement st = null;
        try {
            st = connector.createStatement();
            ResultSet result = st.executeQuery(SELECT_ALL_USERS + END_OF_STATEMENT);
            while (result.next()) {
                User user = createUser(result);
                list.add(user);
            }
        } catch (SQLException e) {
            throw new DAOTechnicalException("Error retrieving data from database", e);
        } finally {
            closeStatement(st);
        }
        return list;
    }

    @Override
    public User findEntityById(int id) throws DAOTechnicalException {
        PreparedStatement st = null;
        User user;
        try {
            st = connector.prepareStatement(SELECT_USER_BY_ID);
            st.setInt(1, id);
            ResultSet res = st.executeQuery();
            res.first();
            user = createUser(res);
        } catch (SQLException e) {
            throw new DAOTechnicalException("Error retrieving data from database", e);
        } finally {
            closeStatement(st);
        }
        return user;
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
            user = createUser(res);
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
            user = createUser(res);
        } catch (SQLException e) {
            throw new DAOTechnicalException("Error retrieving data from database", e);
        } finally {
            closeStatement(st);
        }
        return user;
    }


    protected User createUser(ResultSet result) throws SQLException {
        User user = new User();
        user.setRole(result.getString(USER_ROLE));
        user.setId(result.getInt(USER_ID));
        user.setLogin(result.getString(LOGIN));
        user.setPassword(result.getBytes(PASSWORD));
        return user;
    }

    @Override
    protected String setDeleteStetement(){
        return DELETE_USER_BY_ID;
    }

    @Override
    protected String setAddStetement() {
        return ADD_NEW_USER;
    }

    @Override
    public void update(User user) throws DAOTechnicalException {
        PreparedStatement st = null;
        try {
            st = connector.prepareStatement(UPDATE_USER);
            setDataOnPreparedStatement(st, user);
            st.setInt(4,user.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOTechnicalException("Error updating database", e);
        } finally {
            closeStatement(st);
        }
    }

    @Override
    protected void setDataOnPreparedStatement(PreparedStatement st, User user) throws SQLException {
        int roleIndex = DatabaseEnumLoader.USER_ROLE_MAP.getKey(user.getRole());
        st.setString(1, user.getLogin());
        st.setBytes(2, user.getPassword());
        st.setInt(3, roleIndex);
    }

}
