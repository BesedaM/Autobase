package by.epam.javatraining.beseda.webproject.model.dao.entitydao;

import by.epam.javatraining.beseda.webproject.model.dao.util.database.SQLQuery;
import by.epam.javatraining.beseda.webproject.model.entity.user.Driver;
import by.epam.javatraining.beseda.webproject.model.dao.exception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.model.dao.exception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.model.dao.exception.NotEnoughArgumentsException;
import by.epam.javatraining.beseda.webproject.model.exception.entityexception.EntityLogicException;
import by.epam.javatraining.beseda.webproject.model.dao.util.database.DBEntityTable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.epam.javatraining.beseda.webproject.model.dao.util.database.DBEntityTable.*;
import static by.epam.javatraining.beseda.webproject.model.dao.util.database.DBEnumTable.USER_DRIVER;
import static by.epam.javatraining.beseda.webproject.model.dao.util.database.SQLQuery.*;

public class DriverDAO extends AbstractDAO<Driver> {

    public DriverDAO() {
        super();
    }

//    private static class SingletonHolder {
//        public static final DriverDAO instance = new DriverDAO();
//    }
//
//    public static DriverDAO getDAO() {
//        return SingletonHolder.instance;
//    }

    @Override
    public synchronized int add(Driver driver) throws DAOLayerException {
        int id = -1;
        if (driver != null) {
            PreparedStatement st = null;
            try {
                st = connector.prepareStatement(addStatement());
                setDataOnPreparedStatement(st, driver);
                id = driver.getId();
                st.setInt(4, id);
                st.executeUpdate();
            } catch (SQLException e) {
                throw new DAOTechnicalException("Error updating database", e);
            } finally {
                closeStatement(st);
            }
        }
        return id;
    }

    @Override
    protected Driver createEntity(ResultSet res) throws SQLException, EntityLogicException {
        Driver driver = null;
        if (res != null) {
            driver = new Driver();
            driver.setId(res.getInt(SQLQuery.DRIVER_ID));
            driver.setName(res.getString(NAME));
            driver.setSurname(res.getString(SURNAME));
            driver.setPhone(res.getString(PHONE));
            driver.setLogin(res.getString(LOGIN));
            driver.setPassword(res.getBytes(PASSWORD));
            driver.setRole(USER_DRIVER);
        }
        return driver;
    }

    @Override
    protected String getAllStatement() {
        return SELECT_ALL_DRIVERS;
    }

    @Override
    protected String getEntityByIdStatement() {
        return SELECT_DRIVER_BY_ID;
    }

    @Override
    protected String deleteStatement() {
        return DELETE_DRIVER_BY_ID;
    }

    @Override
    protected String addStatement() {
        return ADD_NEW_DRIVER;
    }

    @Override
    protected String updateStatement() {
        return UPDATE_DRIVER;
    }

    @Override
    protected int updateIdParameterNumber() {
        return 4;
    }

    @Override
    protected void setDataOnPreparedStatement(PreparedStatement st, Driver driver) throws SQLException, NotEnoughArgumentsException {
        if (st != null && driver != null) {
            st.setString(1, driver.getName());
            st.setString(2, driver.getSurname());
            st.setString(3, driver.getPhone());
        } else {
            throw new NotEnoughArgumentsException();
        }
    }
}
