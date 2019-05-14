package by.epam.javatraining.beseda.webproject.model.dao.entitydao;

import by.epam.javatraining.beseda.webproject.model.entity.user.Driver;
import by.epam.javatraining.beseda.webproject.model.exception.DAOexception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.model.exception.DAOexception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.util.database.DBEntityTableName;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static by.epam.javatraining.beseda.webproject.util.database.DBEntityTableName.*;
import static by.epam.javatraining.beseda.webproject.util.database.DBEnumTable.USER_DRIVER;
import static by.epam.javatraining.beseda.webproject.util.database.SQLQuery.*;

public class DriverDAO extends AbstractDAO<Driver> {

    private Logger log = Logger.getLogger(DriverDAO.class.getSimpleName());
    private static DriverDAO instance = null;


    private DriverDAO() {
        super();
        this.dbTableName = DBEntityTableName.T_DRIVERS;
    }

    public static DriverDAO getDAO() {
        if (instance == null) {
            instance = new DriverDAO();
        }
        return instance;
    }

    @Override
    public List<Driver> getAll() throws DAOLayerException {
        List<Driver> list = new ArrayList<>();
        Statement st = null;
        try {
            st = connector.createStatement();
            ResultSet result = st.executeQuery(SELECT_ALL_DRIVERS + END_OF_STATEMENT);
            while (result.next()) {
                Driver driver = createDriver(result);
                list.add(driver);
            }
        } catch (SQLException e) {
            throw new DAOTechnicalException("Error retrieving data from database", e);
        } finally {
            closeStatement(st);
        }
        return list;
    }

    @Override
    public Driver findEntityById(int id) throws DAOTechnicalException {
        PreparedStatement st = null;
        Driver driver;
        try {
            st = connector.prepareStatement(SELECT_DRIVER_BY_ID);
            st.setInt(1, id);
            ResultSet res = st.executeQuery();
            res.first();
            driver = createDriver(res);
        } catch (SQLException e) {
            throw new DAOTechnicalException("Error retrieving data from database", e);
        } finally {
            closeStatement(st);
        }
        return driver;
    }

    private Driver createDriver(ResultSet res) throws SQLException {
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
    protected String setDeleteStetement(){
        return DELETE_DRIVER_BY_ID;
    }

    @Override
    protected String setAddStetement() {
        return ADD_NEW_DRIVER;
    }

    @Override
    public void update(Driver driver) throws DAOLayerException {
        PreparedStatement st = null;
        try {
            st = connector.prepareStatement(UPDATE_DRIVER);
            setDataOnPreparedStatement(st, driver);
            st.setInt(4, driver.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOTechnicalException("Error updating database", e);
        } finally {
            closeStatement(st);
        }
    }

    @Override
    protected void setDataOnPreparedStatement(PreparedStatement st, Driver driver) throws SQLException {
        st.setString(1, driver.getName());
        st.setString(2, driver.getSurname());
        st.setString(3, driver.getPhone());
    }
}
