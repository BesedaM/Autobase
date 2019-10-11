package by.epam.javatraining.beseda.webproject.dao.util.dataloader;

import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.T_CAR_STATE;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.T_CAR_STATUS;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.T_CAR_TYPE;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.T_CUSTOMER_TYPE;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.T_REQUEST_STATUS;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.T_ROUTE_STATUS;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.T_TRUCK_CAPACITY;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.T_USER_ROLE;
import static by.epam.javatraining.beseda.webproject.connectionpool.DatabaseProperties.DATABASE_PASSWORD;
import static by.epam.javatraining.beseda.webproject.connectionpool.DatabaseProperties.DATABASE_URL;
import static by.epam.javatraining.beseda.webproject.connectionpool.DatabaseProperties.DATABASE_USER;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import by.epam.javatraining.beseda.webproject.connectionpool.ConnectionPool;
import by.epam.javatraining.beseda.webproject.connectionpool.DBConnector;
import by.epam.javatraining.beseda.webproject.dao.EnumDAO;
import by.epam.javatraining.beseda.webproject.util.ReversalHashMap;

public class DatabaseEnumLoader {
	
	public static ReversalHashMap<Integer, String> USER_ROLE_MAP;
	public static ReversalHashMap<Integer, String> CUSTOMER_TYPE_MAP;
	public static ReversalHashMap<Integer, String> TRUCK_CAPACITY_MAP;
	public static ReversalHashMap<Integer, String> CAR_STATUS_MAP;
	public static ReversalHashMap<Integer, String> CAR_STATE_MAP;
	public static ReversalHashMap<Integer, String> CAR_TYPE_MAP;
	public static ReversalHashMap<Integer, String> ROUTE_STATUS_MAP;
	public static ReversalHashMap<Integer, String> REQUEST_STATUS_MAP;

	static {
		USER_ROLE_MAP = EnumDAO.getEnumMap(T_USER_ROLE);
		CUSTOMER_TYPE_MAP = EnumDAO.getEnumMap(T_CUSTOMER_TYPE);
		TRUCK_CAPACITY_MAP = EnumDAO.getEnumMap(T_TRUCK_CAPACITY);
		CAR_STATUS_MAP = EnumDAO.getEnumMap(T_CAR_STATUS);
		CAR_STATE_MAP = EnumDAO.getEnumMap(T_CAR_STATE);
		CAR_TYPE_MAP = EnumDAO.getEnumMap(T_CAR_TYPE);
		ROUTE_STATUS_MAP = EnumDAO.getEnumMap(T_ROUTE_STATUS);
		REQUEST_STATUS_MAP = EnumDAO.getEnumMap(T_REQUEST_STATUS);
	}

}
