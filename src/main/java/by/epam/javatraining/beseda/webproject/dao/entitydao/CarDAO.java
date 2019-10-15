package by.epam.javatraining.beseda.webproject.dao.entitydao;

import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.CAR_DRIVER_GET_DRIVER_ID;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.CAR_DRIVER_UPDATE_DEPENDENCE;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.CAR_ROUTE_GET_ACTIVE_PLANNED_ROUTE_ID;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.CAR_ROUTE_GET_ACTIVE_ROUTE_ID;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.CAR_ROUTE_GET_ROUTES_ID;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.DELETE_CAR_BY_ID;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.SELECT_ALL_CARS;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.SELECT_CARS_BY_ID_LIST;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.SELECT_CARS_BY_TYPE;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.SELECT_CAR_BY_ID;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.UPDATE_BUS;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.UPDATE_CAR_STATE;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.UPDATE_TRUCK;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBConstants.QUESTION_MARK;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBConstants.ZERO_VALUE;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.CAR_NUMBER;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.CAR_STATE_ID_CARS;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.CAR_STATUS_ID_CARS;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.CAR_TYPE_ID_CARS;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.MODEL;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.SEATS_NUMBER;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.TRUCK_CAPACITY_ID_CARS;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.BUS;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.TRUCK;
import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import by.epam.javatraining.beseda.webproject.dao.exception.CarTypeNotPresentException;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.CarInterface;
import by.epam.javatraining.beseda.webproject.dao.util.dataloader.DatabaseEnumLoader;
import by.epam.javatraining.beseda.webproject.entity.car.Bus;
import by.epam.javatraining.beseda.webproject.entity.car.Car;
import by.epam.javatraining.beseda.webproject.entity.car.Truck;

@Repository
public class CarDAO extends AbstractDAO<Car> implements CarInterface {

	private Logger log = Logger.getLogger(ERROR_LOGGER);

	CarDAO() {
		super();
	}

	public CarDAO(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
	}

	@Autowired
	@Qualifier("carMapper")
	@Override
	protected void setRowMapper(RowMapper<Car> rowMapper) {
		this.rowMapper = rowMapper;
	}

	@Override
	public List<Integer> getRoutesId(int carId) {
		return jdbcTemplate.queryForList(CAR_ROUTE_GET_ROUTES_ID, new Object[] { carId }, Integer.class);
	}

	@Override
	public void setDriver(int driverId, int carId) {
		jdbcTemplate.update(CAR_DRIVER_UPDATE_DEPENDENCE, driverId, carId);
	}

	@Override
	public int getDriverId(int carId) {
		return jdbcTemplate.queryForObject(CAR_DRIVER_GET_DRIVER_ID, new Object[] { carId }, Integer.class);
	}

	@Override
	public void deleteDriver(int carId) {
		jdbcTemplate.update(CAR_DRIVER_UPDATE_DEPENDENCE, ZERO_VALUE, carId);
	}

	@Override
	public List<Integer> getActiveRoutesId(int carId) {
		return jdbcTemplate.queryForList(CAR_ROUTE_GET_ACTIVE_ROUTE_ID, new Object[] { carId }, Integer.class);
	}

	@Override
	public List<Integer> getActivePlannedRoutesId(int carId) {
		return jdbcTemplate.queryForList(CAR_ROUTE_GET_ACTIVE_PLANNED_ROUTE_ID, new Object[] { carId }, Integer.class);
	}

	@Override
	public int add(Car car) throws DAOLayerException {
		if(!(car instanceof Bus||car instanceof Truck)) {
			throw new DAOLayerException(new CarTypeNotPresentException());
		}
		return super.add(car);
	}
	
	@Override
	public void update(Car entity) throws DAOLayerException {
		String sql = null;
		if (entity instanceof Bus) {
			sql = UPDATE_BUS;
		} else if (entity instanceof Truck) {
			sql = UPDATE_TRUCK;
		} else {
			throw new DAOLayerException(new CarTypeNotPresentException());
		}
		jdbcTemplate.update(sql, createEntityParamArray(entity), entity.getId());
	}

	@Override
	public List<Car> getCarsByType(String carType){
		String sql = SELECT_CARS_BY_TYPE.replace(QUESTION_MARK, carType);
		return jdbcTemplate.query(sql, rowMapper);
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
	public void updateCarState(int id, String state){
		if (id > 0 && state != null) {
			int carStateIndex = DatabaseEnumLoader.CAR_STATE_MAP.getKey(state);
			jdbcTemplate.update(UPDATE_CAR_STATE, carStateIndex, id);
		}
	}

	@Override
	protected MapSqlParameterSource createMapSqlParameterSource(Car entity) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		int carTypeIndex = 0;
		if (entity instanceof Truck) {
			carTypeIndex = DatabaseEnumLoader.CAR_TYPE_MAP.getKey(TRUCK);
			int truckCapacity = ((Truck) entity).getCapacity();
			int capacityID = DatabaseEnumLoader.TRUCK_CAPACITY_MAP.getKey(truckCapacity + "");
			parameters.addValue(TRUCK_CAPACITY_ID_CARS, capacityID);
		} else if (entity instanceof Bus) {
			carTypeIndex = DatabaseEnumLoader.CAR_TYPE_MAP.getKey(BUS);
			int seatsNumber = ((Bus) entity).getSeats();
			parameters.addValue(SEATS_NUMBER, seatsNumber);
		} else {
			log.fatal(new CarTypeNotPresentException());
		}

		parameters.addValue(CAR_TYPE_ID_CARS, carTypeIndex);
		int carStateIndex = DatabaseEnumLoader.CAR_STATE_MAP.getKey(entity.getState());
		int carStatusIndex = DatabaseEnumLoader.CAR_STATUS_MAP.getKey(entity.getStatus());
		parameters.addValue(CAR_NUMBER, entity.getNumber());
		parameters.addValue(MODEL, entity.getModel());
		parameters.addValue(CAR_STATUS_ID_CARS, carStatusIndex);
		parameters.addValue(CAR_STATE_ID_CARS, carStateIndex);
		return parameters;
	}

	@Override
	protected Object[] createEntityParamArray(Car entity) {
		Object[] array = new Object[6];
		int carTypeIndex = 0;
		if (entity instanceof Truck) {
			carTypeIndex = DatabaseEnumLoader.CAR_TYPE_MAP.getKey(TRUCK);
			int truckCapacity = ((Truck) entity).getCapacity();
			int capacityID = DatabaseEnumLoader.TRUCK_CAPACITY_MAP.getKey(truckCapacity + "");
			array[1] = capacityID;
		} else if (entity instanceof Bus) {
			carTypeIndex = DatabaseEnumLoader.CAR_TYPE_MAP.getKey(BUS);
			int seatsNumber = ((Bus) entity).getSeats();
			array[1] = seatsNumber;
		}
		int carStateIndex = DatabaseEnumLoader.CAR_STATE_MAP.getKey(entity.getState());
		int carStatusIndex = DatabaseEnumLoader.CAR_STATUS_MAP.getKey(entity.getStatus());
		array[0] = carTypeIndex;

		array[2] = entity.getNumber();
		array[3] = entity.getModel();
		array[4] = carStatusIndex;
		array[5] = carStateIndex;
		return array;
	}

}
