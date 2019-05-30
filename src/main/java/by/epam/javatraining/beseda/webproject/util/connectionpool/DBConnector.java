package by.epam.javatraining.beseda.webproject.util.connectionpool;

import org.apache.log4j.Logger;
import by.epam.javatraining.beseda.webproject.util.resourceloader.GeneralProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class DBConnector implements ConnectionPool {

    private Logger log = Logger.getLogger(DBConnector.class);

    private String url;
    private String user;
    private String password;
    private Queue<Connection> connectionPool;
    private Queue<Connection> connectionsInUse;

    private static volatile DBConnector pool = null;

    private DBConnector(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
        connectionPool = new ConcurrentLinkedQueue<>();
        connectionsInUse = new ConcurrentLinkedQueue<>();
        for (int i = 0; i < GeneralProperties.INITIAL_POOL_SIZE; i++) {
            try {
                connectionPool.add(createConnection(url, user, password));
            } catch (SQLException e) {
                log.error("Impossible to create a connection to database: " + e);
            }
        }
    }

    /**
     * Method for creating a ConnectionPool
     *
     * @param url      a database url of the form jdbc:subprotocol:subname
     * @param user     the database user on whose behalf the connection is being made
     * @param password database access password
     * @return DBConnector instance
     */
    public static DBConnector createConnectionPool(String url, String user, String password) {
        if (pool == null) {
            pool = new DBConnector(url, user, password);
        }
        return pool;
    }


    private static Connection createConnection(String url, String user, String password) throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    @Override
    public Connection getConnection() throws SQLException {
        return getConnection(user, password);
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        Connection connection = null;

        while (!connectionPool.isEmpty()) {
            connection = connectionPool.remove();
            if (connection.isValid(GeneralProperties.WAIT_FOR_DB_RESPONSE)) {
                break;
            } else {
                connection = null;
            }
        }

        if (connection == null) {
            connection = createConnection(url, username, password);
        }
        connectionsInUse.add(connection);
        return connection;
    }

    @Override
    public void releaseConnection(Connection connection) {
        connectionPool.add(connection);
        connectionsInUse.remove(connection);
    }

    @Override
    public int size() {
        return connectionPool.size() + connectionsInUse.size();
    }

    @Override
    public int usedConnections() {
        return connectionsInUse.size();
    }

    @Override
    public void closePool() throws SQLException {
        synchronized (connectionsInUse) {
            for (Connection c : connectionsInUse) {
                releaseConnection(c);
            }

            for (Connection c : connectionPool) {
                c.close();
            }
        }
    }

}
