package by.epam.javatraining.beseda.webproject.model.dao.entitydao;

import by.epam.javatraining.beseda.webproject.model.entity.user.User;
import by.epam.javatraining.beseda.webproject.model.exception.daoexception.DAOLogicException;
import by.epam.javatraining.beseda.webproject.model.exception.daoexception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.model.exception.daoexception.NotEnoughArgumentsException;
import by.epam.javatraining.beseda.webproject.model.exception.entityexception.EntityLogicException;
import by.epam.javatraining.beseda.webproject.util.resourceloader.DatabaseEnumLoader;
import by.epam.javatraining.beseda.webproject.util.wrapperconnector.WrapperConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.epam.javatraining.beseda.webproject.util.database.DBEntityTableName.LOGIN;
import static by.epam.javatraining.beseda.webproject.util.database.DBEntityTableName.PASSWORD;
import static by.epam.javatraining.beseda.webproject.util.database.DBEnumTable.USER_ROLE;
import static by.epam.javatraining.beseda.webproject.util.database.SQLQuery.*;

public class UserDAO extends AbstractDAO<User> {

//    private static UserDAO instance = null;
//
//    private UserDAO() {
//        super();
//    }
//
//    public static UserDAO getDAO() {
//        if (instance == null) {
//            instance = new UserDAO();
//        }
//        return instance;
//    }


    public UserDAO() {
        super();
    }

    public UserDAO(WrapperConnector connector) {
        super(connector);
    }

    public User getUserByLoginAndPassword(String login, byte[] password) throws DAOTechnicalException {

        User user = null;
        if (login != null && password != null) {
            PreparedStatement st = null;
            try {
                st = connector.prepareStatement(SELECT_USER_BY_LOGIN_AND_PASSWORD);
                st.setString(1, login);
                st.setBytes(2, password);
                ResultSet res = st.executeQuery();
                if (res.next()) {
                    user = createEntity(res);
                }
            } catch (SQLException e) {
                throw new DAOTechnicalException("Error retrieving data from database", e);
            } catch (EntityLogicException e) {
                throw new DAOTechnicalException("Error creating entity " + user.getClass().getSimpleName(), e);
            } finally {
                closeStatement(st);
            }
        }
        return user;
    }

    public User getUserByLogin(String login) throws DAOTechnicalException {
        User user = null;
        if (login != null) {
            PreparedStatement st = null;
            try {
                st = connector.prepareStatement(SELECT_USER_BY_LOGIN);
                st.setString(1, login);
                ResultSet res = st.executeQuery();
                if (res.next()) {
                    user = createEntity(res);
                }
            } catch (SQLException e) {
                throw new DAOTechnicalException("Error retrieving data from database", e);
            } catch (EntityLogicException e) {
                throw new DAOTechnicalException("Error creating entity " + user.getClass().getSimpleName(), e);
            } finally {
                closeStatement(st);
            }
        }
        return user;
    }

    public boolean updatePassword(String login, byte[] password) throws DAOTechnicalException {
        boolean succeed=false;
        if (login != null && password != null) {
            PreparedStatement st = null;
            try {
                st = connector.prepareStatement(UPDATE_USER_PASSWORD);
                st.setString(1, login);
                st.setBytes(2, password);
                st.executeUpdate();
                succeed=true;
            } catch (SQLException e) {
                throw new DAOTechnicalException("Error retrieving data from database", e);
            } finally {
                closeStatement(st);
            }
        }
        return succeed;
    }

    @Override
    protected User createEntity(ResultSet result) throws SQLException, EntityLogicException {
        User user = null;
        if (result != null) {
            user = new User();
            user.setRole(result.getString(USER_ROLE));
            user.setId(result.getInt(USER_ID));
            user.setLogin(result.getString(LOGIN));
            user.setPassword(result.getBytes(PASSWORD));
        }
        return user;
    }

    @Override
    protected String getAllStatement() {
        return SELECT_ALL_USERS;
    }

    @Override
    protected String getEntityByIdStatement() {
        return SELECT_USER_BY_ID;
    }

    @Override
    protected String deleteStatement() {
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
    protected void setDataOnPreparedStatement(PreparedStatement st, User user) throws SQLException, DAOLogicException {
        if (st != null && user != null) {
            int roleIndex = DatabaseEnumLoader.USER_ROLE_MAP.getKey(user.getRole());
            st.setString(1, user.getLogin());
            st.setBytes(2, user.getPassword());
            st.setInt(3, roleIndex);
        } else {
            throw new NotEnoughArgumentsException();
        }
    }

}
