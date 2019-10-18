package by.epam.javatraining.beseda.webproject.config;

import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.T_CAR_STATE;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.T_CAR_STATUS;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.T_CAR_TYPE;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.T_CUSTOMER_TYPE;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.T_REQUEST_STATUS;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.T_ROUTE_STATUS;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.T_TRUCK_CAPACITY;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.T_USER_ROLE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import by.epam.javatraining.beseda.webproject.dao.entitydao.EnumDAO;
import by.epam.javatraining.beseda.webproject.util.ReversalHashMap;

@Configuration
@ComponentScan({ "by.epam.javatraining.beseda.webproject.dao" })
public class EnumConfig {

	@Autowired
	private EnumDAO enumDAO;
	
	@Bean(name="userRoleMap")
	public ReversalHashMap<Integer, String> getUserRoleMap() {
		return enumDAO.getEnumMap(T_USER_ROLE);
	}

	@Bean(name="customerTypeMap")
	public ReversalHashMap<Integer, String> getCustomerTypeMap() {
		return enumDAO.getEnumMap(T_CUSTOMER_TYPE);
	}

	@Bean(name="truckCapacityMap")
	public ReversalHashMap<Integer, String> getTruckCapacityMap() {
		return enumDAO.getEnumMap(T_TRUCK_CAPACITY);
	}

	@Bean(name="carStatusMap")
	public ReversalHashMap<Integer, String> getCarStatusMap() {
		return enumDAO.getEnumMap(T_CAR_STATUS);
	}

	@Bean(name="carStateMap")
	public ReversalHashMap<Integer, String> getCarStateMap() {
		return enumDAO.getEnumMap(T_CAR_STATE);
	}

	@Bean(name="carTypeMap")
	public ReversalHashMap<Integer, String> getCarTypeMap() {
		return enumDAO.getEnumMap(T_CAR_TYPE);
	}

	@Bean(name="routeStatusMap")
	public ReversalHashMap<Integer, String> getRouteStatusMap() {
		return enumDAO.getEnumMap(T_ROUTE_STATUS);
	}

	@Bean(name="requestStatusMap")
	public ReversalHashMap<Integer, String> getRequestStatusMap() {
		return enumDAO.getEnumMap(T_REQUEST_STATUS);
	}

}
