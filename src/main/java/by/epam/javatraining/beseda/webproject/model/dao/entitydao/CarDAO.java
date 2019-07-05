package by.epam.javatraining.beseda.webproject.model.dao.entitydao;

import by.epam.javatraining.beseda.webproject.model.entity.car.Bus;
import by.epam.javatraining.beseda.webproject.model.entity.car.Car;
import by.epam.javatraining.beseda.webproject.model.entity.car.Truck;
import by.epam.javatraining.beseda.webproject.model.dao.exception.CarTypeNotPresentException;
import by.epam.javatraining.beseda.webproject.model.dao.exception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.model.dao.exception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.model.dao.exception.NotEnoughArgumentsException;
import by.epam.javatraining.beseda.webproject.model.exception.entityexception.EntityLogicException;
import by.epam.javatraining.beseda.webproject.model.dao.util.dataloader.DatabaseEnumLoader;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.epam.javatraining.beseda.webproject.model.dao.util.database.DBEntityTable.*;
import static by.epam.javatraining.beseda.webproject.model.dao.util.database.DBEnumTable.*;
import static by.epam.javatraining.beseda.webproject.model.dao.util.database.SQLQuery.*;

public class CarDAO extends AbstractDAO<Car> {

    public CarDAO() {
        super();
    }

//    private static class SingletonHolder {
//        public static final CarDAO instance = new CarDAO();
//    }
//
//    public static CarDAO getDAO() {
//        return SingletonHolder.instance;
//    }

    @Override
    protected Car createEntity(ResultSet result) throws CarTypeNotPresentException, SQLException, EntityLogicException {
        Car car = null;
        if (result != null) {
            String carType = result.getString(CAR_TYPE);
            if (carType == BUS) {
                car = new Bus();
                ((Bus) car).setSeats(result.getInt(SEATS_NUMBER));
            } else if (carType == TRUCK) {
                car = new Truck();
                ((Truck) car).setCapacity(result.getInt(TRUCK_CAPACITY));
            } else {
                throw new CarTypeNotPresentException();
            }
            car.setId(result.getInt(CAR_ID));
            car.setModel(result.getString(MODEL));
            car.setNumber(result.getString(CAR_NUMBER));
            car.setState(result.getString(CAR_STATE));
            car.setStatus(result.getString(CAR_STATUS));
        }
        return car;
    }

    @Override
    public synchronized int add(Car car) throws DAOLayerException {
        int id = -1;
        if (car != null) {
            PreparedStatement st = null;
            try {
                if (car instanceof Truck) {
                    addTruck(st, (Truck) car);
                } else if (car instanceof Bus) {
                    addBus(st, (Bus) car);
                } else {
                    throw new CarTypeNotPresentException();
                }
                st.executeUpdate();
                ResultSet res = st.getGeneratedKeys();
                id = res.getInt(1);
            } catch (SQLException e) {
                throw new DAOTechnicalException("Error updating database", e);
            } finally {
                closeStatement(st);
            }
        }
        return id;
    }

    private void addBus(PreparedStatement st, Bus car) throws SQLException, NotEnoughArgumentsException {
        if (st != null && car != null) {
            st = connector.prepareStatementWithAutoGeneratedKeys(ADD_NEW_BUS);
            int carTypeIndex = DatabaseEnumLoader.CAR_TYPE_MAP.getKey(BUS);
            st.setInt(1, carTypeIndex);
            setDataOnPreparedStatement(st, car);
        } else {
            throw new NotEnoughArgumentsException();
        }
    }

    private void addTruck(PreparedStatement st, Truck car) throws SQLException, NotEnoughArgumentsException {
        if (st != null && car != null) {
            st = connector.prepareStatementWithAutoGeneratedKeys(ADD_NEW_TRUCK);
            int carTypeIndex = DatabaseEnumLoader.CAR_TYPE_MAP.getKey(TRUCK);
            st.setInt(1, carTypeIndex);
        } else {
            throw new NotEnoughArgumentsException();
        }

    }

    @Override
    public synchronized void update(Car car) throws DAOLayerException {
        if (car != null) {
            PreparedStatement st = null;
            try {
                if (car instanceof Truck) {
                    updateTruck(st, (Truck) car);
                } else if (car instanceof Bus) {
                    updateBus(st, (Bus) car);
                } else {
                    throw new CarTypeNotPresentException();
                }
                st.setInt(updateIdParameterNumber(), car.getId());
                st.executeUpdate();
            } catch (SQLException e) {
                throw new DAOTechnicalException("Error updating database", e);
            } finally {
                closeStatement(st);
            }
        }
    }

    private void updateTruck(PreparedStatement st, Truck car) throws SQLException, NotEnoughArgumentsException {
        if (st != null && car != null) {
            st = connector.prepareStatement(UPDATE_TRUCK);
            String truckCapacity = car.getCapacity() + "";
            int truckCapacityIndex = DatabaseEnumLoader.TRUCK_CAPACITY_MAP.getKey(truckCapacity);
            st.setInt(2, truckCapacityIndex);
            setDataOnPreparedStatement(st, car);
        } else {
            throw new NotEnoughArgumentsException();
        }
    }

    private void updateBus(PreparedStatement st, Bus car) throws SQLException, NotEnoughArgumentsException {
        if (st != null && car != null) {
            st = connector.prepareStatement(UPDATE_BUS);
            st.setInt(2, car.getSeats());
            setDataOnPreparedStatement(st, car);
        } else {
            throw new NotEnoughArgumentsException();
        }
    }

    @Override
    protected String getAllStatement() {
        return SELECT_ALL_CARS;
    }

    @Override
    protected String getEntityByIdStatement() {
        return SELECT_CAR_BY_ID;
    }

    /**
     * Undefined
     *
     * @return null
     */
    @Override
    protected String addStatement() {
        return null;
    }

    @Override
    protected String deleteStatement() {
        return DELETE_CAR_BY_ID;
    }


    /**
     * Undefined
     *
     * @return null
     */
    @Override
    protected String updateStatement() {
        return null;
    }

    @Override
    protected int updateIdParameterNumber() {
        return 7;
    }

    @Override
    protected void setDataOnPreparedStatement(PreparedStatement st, Car car) throws SQLException, NotEnoughArgumentsException {
        if (st != null && car != null) {
            int carStateIndex = DatabaseEnumLoader.CAR_STATE_MAP.getKey(car.getState());
            int carStatusIndex = DatabaseEnumLoader.CAR_STATUS_MAP.getKey(car.getStatus());
            st.setString(3, car.getNumber());
            st.setString(4, car.getModel());
            st.setInt(5, carStatusIndex);
            st.setInt(6, carStateIndex);
        } else {
            throw new NotEnoughArgumentsException();
        }
    }

}
