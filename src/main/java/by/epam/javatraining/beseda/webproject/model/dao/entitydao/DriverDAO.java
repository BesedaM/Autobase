package by.epam.javatraining.beseda.webproject.model.dao.entitydao;

import by.epam.javatraining.beseda.webproject.model.entity.user.Driver;
import by.epam.javatraining.beseda.webproject.model.exception.DAOexception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.model.exception.DAOexception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.util.database.DBEntityTableName;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.epam.javatraining.beseda.webproject.util.database.DBEntityTableName.*;
import static by.epam.javatraining.beseda.webproject.util.database.DBEnumTable.USER_DRIVER;
import static by.epam.javatraining.beseda.webproject.util.database.SQLQuery.*;

public class DriverDAO extends AbstractDAO<Driver> {

    private static DriverDAO instance = null;

    private DriverDAO() {
        super();
    }

    public static DriverDAO getDAO() {
        if (instance == null) {
            instance = new DriverDAO();
        }
        return instance;
    }

    @Override
    public int add(Driver user) throws DAOLayerException {
        PreparedStatement st = null;
        int id = -1;
        try {
            st = connector.prepareStatement(addStatement());
            setDataOnPreparedStatement(st, user);
            st.setInt(4,user.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOTechnicalException("Error updating database", e);
        } finally {
            closeStatement(st);
        }
        return id;
    }

    @Override
    protected Driver createEntity(ResultSet res) throws SQLException {
        Driver driver = new Driver();
        driver.setId(res.getInt(DBEntityTableName.DRIVER_ID));
        driver.setName(res.getString(NAME));
        driver.setSurname(res.getString(SURNAME));
        driver.setPhone(res.getString(PHONE));
        driver.setLogin(res.getString(LOGIN));
        driver.setPassword(res.getBytes(PASSWORD));
        driver.setRole(USER_DRIVER);
        return driver;
    }

    @Override
    protected String getAllStatement() {
        return SELECT_ALL_DRIVERS;
    }

    @Override
    protected String findEntityByIdStatement() {
        return SELECT_DRIVER_BY_ID;
    }

    @Override
    protected String deleteStatement(){
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
    protected void setDataOnPreparedStatement(PreparedStatement st, Driver driver) throws SQLException {
        st.setString(1, driver.getName());
        st.setString(2, driver.getSurname());
        st.setString(3, driver.getPhone());
    }
}
