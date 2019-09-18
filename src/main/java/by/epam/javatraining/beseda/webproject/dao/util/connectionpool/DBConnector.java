package by.epam.javatraining.beseda.webproject.dao.util.connectionpool;

import org.apache.log4j.Logger;

import by.epam.javatraining.beseda.webproject.dao.exception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.util.resourceloader.GeneralProperties;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import static by.epam.javatraining.beseda.webproject.dao.util.dataloader.DatabaseProperties.DATABASE_PASSWORD;
import static by.epam.javatraining.beseda.webproject.dao.util.dataloader.DatabaseProperties.DATABASE_URL;
import static by.epam.javatraining.beseda.webproject.dao.util.dataloader.DatabaseProperties.DATABASE_USER;
import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;

public class DBConnector implements ConnectionPool {

	private static Logger log = Logger.getLogger(ERROR_LOGGER);

	private static Driver driver;

	private String url;
	private String user;
	private String password;
	private Queue<Connection> connectionPool;
	private Queue<Connection> connectionInUse;

	private static class SingletonHolder {
		public static DBConnector pool = new DBConnector(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
	}

	private DBConnector(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
		connectionPool = new ConcurrentLinkedQueue<>();
		connectionInUse = new ConcurrentLinkedQueue<>();
		for (int i = 0; i < GeneralProperties.INITIAL_POOL_SIZE; i++) {
			try {
				connectionPool.add(createConnection(url, user, password));
			} catch (SQLException e) {
				log.error(new DAOTechnicalException(e));
			}
		}
	}

	private static void registerDriver() throws SQLException {
		driver = new org.postgresql.Driver();
		DriverManager.registerDriver(driver);
	}

	private static void deregisterDriver() throws SQLException {
		DriverManager.deregisterDriver(driver);
	}

	/**
	 * Method for creating a ConnectionPool.
	 *
	 * @param url      a database url of the form jdbc:subprotocol:subname
	 * @param user     the database user on whose behalf the connection is being
	 *                 made
	 * @param password database access password
	 * @return DBConnector instance
	 */
	public static DBConnector createConnectionPool(String url, String user, String password) {
		try {
			registerDriver();
		} catch (SQLException e) {
			log.fatal(e);
		}
		return SingletonHolder.pool;
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
		connectionInUse.add(connection);
		return connection;
	}

	@Override
	public void releaseConnection(Connection connection) {
		connectionPool.add(connection);
		connectionInUse.remove(connection);
	}

	@Override
	public int size() {
		return connectionPool.size() + connectionInUse.size();
	}

	@Override
	public int connectionsInUse() {
		return connectionInUse.size();
	}

	@Override
	public void closePool() throws SQLException {
		synchronized (this) {
			for (Connection c : connectionInUse) {
				releaseConnection(c);
			}

			for (Connection c : connectionPool) {
				c.close();
			}
		}
		deregisterDriver();
	}

}