package by.epam.javatraining.beseda.webproject.dao.entitydao;

import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.ADD_NEW_DRIVER;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.DELETE_DRIVER_BY_ID;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.SELECT_ALL_DRIVERS;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.SELECT_DRIVERS_BY_ID_LIST;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.SELECT_DRIVER_BY_ID;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.UPDATE_DRIVER;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import by.epam.javatraining.beseda.webproject.connectionpool.ConnectionPool;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.dao.exception.NotEnoughArgumentsException;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.DriverInterface;
import by.epam.javatraining.beseda.webproject.entity.user.Driver;


public class DriverDAO extends AbstractDAO<Driver> implements DriverInterface {

	{
		builder=entityBuilderFactory.getDriverBuilder();
	}
	
    DriverDAO() {
        super();
    }
    
    DriverDAO(ConnectionPool pool) {
        super(pool);
    }

    @Override
    public int add(Driver driver) throws DAOLayerException {
        int id = -1;
        if (driver != null) {
            PreparedStatement st = null;
            try {
                lock.lock();
                st = connector.prepareStatement(addStatement());
                setDataOnPreparedStatement(st, driver);
                id = driver.getId();
                st.setInt(4, id);
                st.executeUpdate();
            } catch (SQLException e) {
                throw new DAOTechnicalException(e);
            } finally {
                connector.closeStatement(st);
                lock.unlock();
            }
        }
        return id;
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
    protected String getEntityListByIdStatement() {
        return SELECT_DRIVERS_BY_ID_LIST;
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
            st.setString(1, driver.getSurname());
        	st.setString(2, driver.getName());
            st.setString(3, driver.getPhone());
        } else {
            throw new NotEnoughArgumentsException();
        }
    }
}
