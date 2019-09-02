package by.epam.javatraining.beseda.webproject.dao.util.dataloader;

import by.epam.javatraining.beseda.webproject.dao.EnumDAO;
import by.epam.javatraining.beseda.webproject.util.ReversalHashMap;
import by.epam.javatraining.beseda.webproject.dao.util.connectionpool.ConnectionPool;
import by.epam.javatraining.beseda.webproject.dao.util.connectionpool.DBConnector;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static by.epam.javatraining.beseda.webproject.dao.util.database.DBEnumTable.*;
import static by.epam.javatraining.beseda.webproject.dao.util.dataloader.DatabaseProperties.*;

public class DatabaseEnumLoader {

    private static Logger log = Logger.getLogger(DatabaseEnumLoader.class.getSimpleName());

    private static Connection conn;
    private static Statement st;


    public static final ReversalHashMap<Integer, String> USER_ROLE_MAP;
    public static final ReversalHashMap<Integer, String> CUSTOMER_TYPE_MAP;
    public static final ReversalHashMap<Integer, String> TRUCK_CAPACITY_MAP;
    public static final ReversalHashMap<Integer, String> CAR_STATUS_MAP;
    public static final ReversalHashMap<Integer, String> CAR_STATE_MAP;
    public static final ReversalHashMap<Integer, String> CAR_TYPE_MAP;
    public static final ReversalHashMap<Integer, String> ROUTE_STATUS_MAP;
    public static final ReversalHashMap<Integer, String> REQUEST_STATUS_MAP;

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
        ConnectionPool connectionPool = DBConnector.createConnectionPool(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        try {
            conn = connectionPool.getConnection();
            st = conn.createStatement();
        } catch (SQLException e) {
            log.error("Exception thrown while connecting the database: " + e);
        }
    }

    private static void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            log.error("Exception thrown while closing connection: " + e);
        }
    }


}
