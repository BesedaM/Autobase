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
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.ADD_NEW_BUS;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.ADD_NEW_TRUCK;
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
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.ID;
import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;

import static by.epam.javatraining.beseda.webproject.service.ServiceConstants.CAR_STATE;
import static by.epam.javatraining.beseda.webproject.service.ServiceConstants.CAR_STATUS;
import static by.epam.javatraining.beseda.webproject.service.ServiceConstants.CAR_TYPE;
import static by.epam.javatraining.beseda.webproject.service.ServiceConstants.TRUCK_CAPACITY;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import by.epam.javatraining.beseda.webproject.dao.exception.CarTypeNotPresentException;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.CarInterface;
import by.epam.javatraining.beseda.webproject.entity.car.Bus;
import by.epam.javatraining.beseda.webproject.entity.car.Car;
import by.epam.javatraining.beseda.webproject.entity.car.Truck;
import by.epam.javatraining.beseda.webproject.entity.route.Address;
import by.epam.javatraining.beseda.webproject.service.EnumMap;
import by.epam.javatraining.beseda.webproject.util.ReversalHashMap;

@Repository
public class CarDAO extends AbstractDAO<Car> implements CarInterface {

	private Logger log = Logger.getLogger(ERROR_LOGGER);

	@Autowired
	@Qualifier("carTypeMap")
	private ReversalHashMap<Integer, String> carTypeMap;

	@Autowired
	@Qualifier("truckCapacityMap")
	private ReversalHashMap<Integer, String> truckCapacityMap;

	@Autowired
	@Qualifier("carStatusMap")
	private ReversalHashMap<Integer, String> carStatusMap;

	@Autowired
	@Qualifier("carStateMap")
	private ReversalHashMap<Integer, String> carStateMap;

	private String addStatement;
	private String updateStatement;

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

	@Autowired
	@Qualifier("carExtractor")
	@Override
	protected void setResultSetExtractor(ResultSetExtractor<Car> rsExtractor) {
		this.rsExtractor = rsExtractor;
	}

	@Override
	public int add(Car car) throws DAOLayerException {
		if (car instanceof Bus) {
			addStatement = ADD_NEW_BUS;
		} else if (car instanceof Truck) {
			addStatement = ADD_NEW_TRUCK;
		} else {
			throw new DAOLayerException(new CarTypeNotPresentException());
		}
		return super.add(car);
	}

	@Override
	public void update(Car car) throws DAOLayerException {
		if (car instanceof Bus) {
			updateStatement = UPDATE_BUS;
		} else if (car instanceof Truck) {
			updateStatement = UPDATE_TRUCK;
		} else {
			throw new DAOLayerException(new CarTypeNotPresentException());
		}
		super.update(car);
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
	public List<Car> getCarsByType(String carType) {
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
		return addStatement;
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
		return updateStatement;
	}

	@Override
	public void updateCarState(int id, String state) {
		if (id > 0 && state != null) {
			int carStateIndex = carStateMap.getKey(state);
			jdbcTemplate.update(UPDATE_CAR_STATE, carStateIndex, id);
		}
	}

	@Override
	protected MapSqlParameterSource createMapSqlParameterSource(Car entity) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		int carTypeIndex = 0;
		if (entity instanceof Truck) {
			carTypeIndex = carTypeMap.getKey(TRUCK);
			int truckCapacity = ((Truck) entity).getCapacity();
			int capacityID = truckCapacityMap.getKey(truckCapacity + "");
			parameters.addValue(TRUCK_CAPACITY_ID_CARS, capacityID);
		} else if (entity instanceof Bus) {
			carTypeIndex = carTypeMap.getKey(BUS);
			int seatsNumber = ((Bus) entity).getSeats();
			parameters.addValue(SEATS_NUMBER, seatsNumber);
		} else {
			log.fatal(new CarTypeNotPresentException());
		}

		parameters.addValue(CAR_TYPE_ID_CARS, carTypeIndex);
		int carStateIndex = carStateMap.getKey(entity.getState());
		int carStatusIndex = carStatusMap.getKey(entity.getStatus());
		parameters.addValue(CAR_NUMBER, entity.getNumber());
		parameters.addValue(MODEL, entity.getModel());
		parameters.addValue(CAR_STATUS_ID_CARS, carStatusIndex);
		parameters.addValue(CAR_STATE_ID_CARS, carStateIndex);
		parameters.addValue(ID, entity.getId());
		return parameters;
	}

}
