package by.epam.javatraining.beseda.webproject.dao.entitydao;

import by.epam.javatraining.beseda.webproject.dao.interfacedao.UserInterface;
import by.epam.javatraining.beseda.webproject.entity.user.User;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOLogicException;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.dao.exception.NotEnoughArgumentsException;
import by.epam.javatraining.beseda.webproject.entity.exception.EntityLogicException;
import by.epam.javatraining.beseda.webproject.dao.util.dataloader.DatabaseEnumLoader;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.epam.javatraining.beseda.webproject.dao.util.database.DBEntityTable.LOGIN;
import static by.epam.javatraining.beseda.webproject.dao.util.database.DBEntityTable.PASSWORD;
import static by.epam.javatraining.beseda.webproject.dao.util.database.DBEnumTable.USER_ROLE;
import static by.epam.javatraining.beseda.webproject.dao.util.database.DBEnumTable.ID;
import static by.epam.javatraining.beseda.webproject.dao.util.database.SQLQuery.*;

public class UserDAO extends AbstractDAO<User> implements UserInterface {

    UserDAO() {
        super();
    }

    @Override
    public User getUserByLoginAndPassword(String login, byte[] password) throws DAOTechnicalException {
        User user = null;
        if (login != null && password != null) {
            PreparedStatement st = null;
            try {
                lock.lock();
                st = connector.prepareStatement(SELECT_USER_BY_LOGIN_AND_PASSWORD);
                st.setString(1, login);
                st.setBytes(2, password);
                ResultSet res = st.executeQuery();
                if (res.next()) {
                    user = buildEntity(res);
                }
            } catch (SQLException e) {
                throw new DAOTechnicalException(e);
            } catch (EntityLogicException e) {
                throw new DAOTechnicalException(e);
            } finally {
                connector.closeStatement(st);
                lock.unlock();
            }
        }
        return user;
    }

    @Override
    public User getUserByLogin(String login) throws DAOTechnicalException {
        User user = null;
        if (login != null) {
            PreparedStatement st = null;
            try {
                lock.lock();
                st = connector.prepareStatement(SELECT_USER_BY_LOGIN);
                st.setString(1, login);
                ResultSet res = st.executeQuery();
                if (res.next()) {
                    user = buildEntity(res);
                }
            } catch (SQLException e) {
                throw new DAOTechnicalException(e);
            } catch (EntityLogicException e) {
                throw new DAOTechnicalException(e);
            } finally {
                connector.closeStatement(st);
                lock.unlock();
            }
        }
        return user;
    }

    @Override
    public boolean updatePassword(int id, byte[] password) throws DAOTechnicalException {
        boolean succeed = false;
        if (id > 0 && password != null) {
            PreparedStatement st = null;
            try {
                lock.lock();
                st = connector.prepareStatement(UPDATE_USER_PASSWORD);
                st.setBytes(1, password);
                st.setInt(2, id);
                st.executeUpdate();
                succeed = true;
            } catch (SQLException e) {
                throw new DAOTechnicalException(e);
            } finally {
                connector.closeStatement(st);
                lock.unlock();
            }
        }
        return succeed;
    }

    @Override
    protected User buildEntity(ResultSet result) throws SQLException, EntityLogicException {
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

    @Override
    protected String getAllStatement() {
        return SELECT_ALL_USERS;
    }

    @Override
    protected String getEntityByIdStatement() {
        return SELECT_USER_BY_ID;
    }

    @Override
    protected String getEntityListByIdStatement() {
        return SELECT_USERS_BY_ID_LIST;
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
