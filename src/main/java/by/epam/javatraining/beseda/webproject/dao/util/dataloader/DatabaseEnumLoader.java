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

import by.epam.javatraining.beseda.webproject.connectionpool.ConnectionPool;
import by.epam.javatraining.beseda.webproject.connectionpool.DBConnector;
import by.epam.javatraining.beseda.webproject.dao.EnumDAO;
import by.epam.javatraining.beseda.webproject.util.ReversalHashMap;

public class DatabaseEnumLoader {

	private static Logger log = Logger.getLogger(DatabaseEnumLoader.class.getSimpleName());

	private static ConnectionPool connectionPool;
	private static Connection conn;
	private static Statement st;

	public static ReversalHashMap<Integer, String> USER_ROLE_MAP;
	public static ReversalHashMap<Integer, String> CUSTOMER_TYPE_MAP;
	public static ReversalHashMap<Integer, String> TRUCK_CAPACITY_MAP;
	public static ReversalHashMap<Integer, String> CAR_STATUS_MAP;
	public static ReversalHashMap<Integer, String> CAR_STATE_MAP;
	public static ReversalHashMap<Integer, String> CAR_TYPE_MAP;
	public static ReversalHashMap<Integer, String> ROUTE_STATUS_MAP;
	public static ReversalHashMap<Integer, String> REQUEST_STATUS_MAP;

	static {
		establishConnection();
		USER_ROLE_MAP = EnumDAO.getEnumMap(st, T_USER_ROLE);
		CUSTOMER_TYPE_MAP = EnumDAO.getEnumMap(st, T_CUSTOMER_TYPE);
		TRUCK_CAPACITY_MAP = EnumDAO.getEnumMap(st, T_TRUCK_CAPACITY);
		CAR_STATUS_MAP = EnumDAO.getEnumMap(st, T_CAR_STATUS);
		CAR_STATE_MAP = EnumDAO.getEnumMap(st, T_CAR_STATE);
		CAR_TYPE_MAP = EnumDAO.getEnumMap(st, T_CAR_TYPE);
		ROUTE_STATUS_MAP = EnumDAO.getEnumMap(st, T_ROUTE_STATUS);
		REQUEST_STATUS_MAP = EnumDAO.getEnumMap(st, T_REQUEST_STATUS);
		closeConnection();
	}

	private static void establishConnection() {
		connectionPool = DBConnector.createConnectionPool(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
		try {
			conn = connectionPool.getConnection();
			st = conn.createStatement();
		} catch (SQLException e) {
			log.error("Exception thrown while connecting the database: " + e);
		}
	}

	public static void loadWithConnectionPool(ConnectionPool pool) {
		Connection conn = null;
		try {
			conn = pool.getConnection();
			st = conn.createStatement();

			USER_ROLE_MAP = EnumDAO.getEnumMap(st, T_USER_ROLE);
			CUSTOMER_TYPE_MAP = EnumDAO.getEnumMap(st, T_CUSTOMER_TYPE);
			TRUCK_CAPACITY_MAP = EnumDAO.getEnumMap(st, T_TRUCK_CAPACITY);
			CAR_STATUS_MAP = EnumDAO.getEnumMap(st, T_CAR_STATUS);
			CAR_STATE_MAP = EnumDAO.getEnumMap(st, T_CAR_STATE);
			CAR_TYPE_MAP = EnumDAO.getEnumMap(st, T_CAR_TYPE);
			ROUTE_STATUS_MAP = EnumDAO.getEnumMap(st, T_ROUTE_STATUS);
			REQUEST_STATUS_MAP = EnumDAO.getEnumMap(st, T_REQUEST_STATUS);
		} catch (SQLException e) {
			log.error(e);
		} finally {
			if (conn != null) {
				pool.releaseConnection(conn);
			}
		}
	}

	private static void closeConnection() {
		connectionPool.releaseConnection(conn);
	}

}
