package by.epam.javatraining.beseda.webproject.dao.entitydao;

import by.epam.javatraining.beseda.webproject.dao.exception.*;
import by.epam.javatraining.beseda.webproject.entity.car.Bus;
import by.epam.javatraining.beseda.webproject.entity.car.Car;
import by.epam.javatraining.beseda.webproject.entity.car.Truck;
import by.epam.javatraining.beseda.webproject.entity.exception.EntityLogicException;
import by.epam.javatraining.beseda.webproject.dao.util.dataloader.DatabaseEnumLoader;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import static by.epam.javatraining.beseda.webproject.dao.util.database.DBEntityTable.*;
import static by.epam.javatraining.beseda.webproject.dao.util.database.DBEnumTable.*;
import static by.epam.javatraining.beseda.webproject.dao.util.database.SQLQuery.*;
import static by.epam.javatraining.beseda.webproject.dao.util.database.DBEnumTable.ID;
import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;

public class CarDAO extends AbstractDAO<Car>
		implements by.epam.javatraining.beseda.webproject.dao.interfacedao.CarInterface {

	Logger log = Logger.getLogger(ERROR_LOGGER);

	CarDAO() {
		super();
	}

	@Override
	protected Car buildEntity(ResultSet result) throws CarTypeNotPresentException, SQLException, EntityLogicException {
		Car car = null;
		if (result != null) {
			String carType = result.getString(CAR_TYPE);				
			switch (carType) {
			case BUS:
				car = new Bus();
				((Bus) car).setSeats(result.getInt(SEATS_NUMBER));
				break;
			case TRUCK:
				car = new Truck();
				((Truck) car).setCapacity(result.getInt(TRUCK_CAPACITY));
				break;
			default:
				throw new CarTypeNotPresentException();
			}
			car.setId(result.getInt(ID));
			car.setModel(result.getString(MODEL));
			car.setNumber(result.getString(CAR_NUMBER));
			car.setState(result.getString(CAR_STATE));
			car.setStatus(result.getString(CAR_STATUS));
		}
		return car;
	}

	@Override
	public int add(Car car) throws DAOLayerException {
		int id = -1;
		int carTypeIndex;
		if (car != null) {
			PreparedStatement st = null;
			try {
				lock.lock();			
				if (car instanceof Truck) {
					st = connector.prepareStatementWithAutoGeneratedKeys(ADD_NEW_TRUCK);
					carTypeIndex = DatabaseEnumLoader.CAR_TYPE_MAP.getKey(TRUCK);
					int truckCapacity = ((Truck) car).getCapacity();
					int capacityID = DatabaseEnumLoader.TRUCK_CAPACITY_MAP.getKey(truckCapacity + "");
					st.setInt(2, capacityID);
				} else if (car instanceof Bus) {
					st = connector.prepareStatementWithAutoGeneratedKeys(ADD_NEW_BUS);
					carTypeIndex = DatabaseEnumLoader.CAR_TYPE_MAP.getKey(BUS);
					int seatsNumber = ((Bus) car).getSeats();
					st.setInt(2, seatsNumber);
				} else {
					throw new DAOLogicException(new CarTypeNotPresentException());
				}
				setDataOnPreparedStatement(st, car);
				st.setInt(1, carTypeIndex);
				st.executeUpdate();
				ResultSet res = st.getGeneratedKeys();
				res.first();
				id = res.getInt(1);
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
	public synchronized void update(Car car) throws DAOLayerException {
		if (car != null) {
			PreparedStatement st = null;
			try {
				lock.lock();
				if (car instanceof Truck) {
					st = connector.prepareStatement(UPDATE_TRUCK);
					String truckCapacity = ((Truck) car).getCapacity() + "";
					int truckCapacityIndex = DatabaseEnumLoader.TRUCK_CAPACITY_MAP.getKey(truckCapacity);
					st.setInt(1, truckCapacityIndex);
				} else if (car instanceof Bus) {
					st = connector.prepareStatement(UPDATE_BUS);
					st.setInt(1, ((Bus) car).getSeats());
				} else {
					lock.unlock();
					throw new CarTypeNotPresentException();
				}
				setDataOnPreparedStatement(st, car);
				st.setInt(updateIdParameterNumber(), car.getId());
				st.executeUpdate();
			} catch (SQLException e) {
				throw new DAOTechnicalException(e);
			} finally {
				connector.closeStatement(st);
				lock.unlock();
			}
		}
	}

	@Override
	public List<Car> getCarsByType(String carType) throws DAOLayerException {
		List<Car> list = new ArrayList<>();
		PreparedStatement st = null;
		Car entity = null;
		try {
			lock.lock();
			st = connector.prepareStatement(SELECT_CARS_BY_TYPE + END_OF_STATEMENT);
			st.setString(1, carType);
			ResultSet result = st.executeQuery();
			while (result.next()) {
				entity = buildEntity(result);
				list.add(entity);
			}
		} catch (SQLException e) {
			throw new DAOTechnicalException(e);
		} catch (EntityLogicException e) {
			throw new DAOTechnicalException(e);
		} finally {
			lock.unlock();
			connector.closeStatement(st);
		}
		return list;
	}

	@Override
	protected String getAllStatement() {
		return SELECT_ALL_CARS;
	}

	@Override
	protected String getEntityByIdStatement() {
		return SELECT_CAR_BY_ID;
	}

	@Override
	protected String getEntityListByIdStatement() {
		return SELECT_CARS_BY_ID_LIST;
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
		return 6;
	}

	@Override
	protected void setDataOnPreparedStatement(PreparedStatement st, Car car)
			throws SQLException, NotEnoughArgumentsException {
		if (st != null && car != null) {
			int carStateIndex = DatabaseEnumLoader.CAR_STATE_MAP.getKey(car.getState());
			int carStatusIndex = DatabaseEnumLoader.CAR_STATUS_MAP.getKey(car.getStatus());
			st.setString(2, car.getNumber());
			st.setString(3, car.getModel());
			st.setInt(4, carStatusIndex);
			st.setInt(5, carStateIndex);
		} else {
			throw new NotEnoughArgumentsException();
		}
	}

	@Override
	public void updateCarState(int id, String state) throws DAOTechnicalException {
		if (id > 0 && state != null) {
			int carStateIndex = DatabaseEnumLoader.CAR_STATE_MAP.getKey(state);
			PreparedStatement st = null;
			try {
				lock.lock();
				st = connector.prepareStatement(UPDATE_CAR_STATE);
				st.setInt(1, carStateIndex);
				st.setInt(2, id);
				st.executeUpdate();
			} catch (SQLException e) {
				throw new DAOTechnicalException(e);
			} finally {
				connector.closeStatement(st);
				lock.unlock();
			}
		}
	}

}
