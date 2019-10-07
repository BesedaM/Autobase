package by.epam.javatraining.beseda.webproject.connectionpool;

import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import by.epam.javatraining.beseda.webproject.dao.exception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.connectionpool.ConnectionPool;
import by.epam.javatraining.beseda.webproject.util.GeneralProperties;

public class TestPool implements ConnectionPool {

	private static Logger log = Logger.getLogger(ERROR_LOGGER);

	private static DataSource testConnectionpoolSource;
	private Queue<Connection> testConnectionPool;
	private Queue<Connection> testConnectionInUse;
	private Lock testLock = new ReentrantLock();

	private static ConnectionPool pool = null;

	private TestPool(DataSource connectionSource) {
		testConnectionpoolSource = connectionSource;
		testConnectionPool = new ConcurrentLinkedQueue<>();
		testConnectionInUse = new ConcurrentLinkedQueue<>();
		for (int i = 0; i < GeneralProperties.INITIAL_POOL_SIZE; i++) {
			try {
				testConnectionPool.add(connectionSource.getConnection());
			} catch (SQLException e) {
				log.error(new DAOTechnicalException(e));
			}
		}
	}

	public static ConnectionPool createConnectionPool(DataSource connectionSource) {
		if (pool == null) {
			pool = new TestPool(connectionSource);
		}
		return pool;
	}

	public static ConnectionPool getConnectionPool() {
		return pool;
	}

	@Override
	public Connection getConnection() throws SQLException {
		testLock.lock();
		Connection connection = null;
		while (!testConnectionPool.isEmpty()) {
			connection = testConnectionPool.remove();
			if (connection.isValid(GeneralProperties.WAIT_FOR_DB_RESPONSE)) {
				break;
			} else {
				connection = null;
			}
		}

		if (connection == null) {
			connection = testConnectionpoolSource.getConnection();
		}
		testConnectionInUse.add(connection);
		testLock.unlock();
		return connection;
	}

	@Override
	public void releaseConnection(Connection connection) {
		testConnectionPool.add(connection);
		testConnectionInUse.remove(connection);
	}

	@Override
	public int size() {
		return testConnectionPool.size() + testConnectionInUse.size();
	}

	@Override
	public int connectionsInUse() {
		return testConnectionInUse.size();
	}

	@Override
	public void closePool() throws SQLException {
		testLock.lock();
		for (Connection c : testConnectionInUse) {
			releaseConnection(c);
		}

		for (Connection c : testConnectionPool) {
			c.close();
		}
		testLock.unlock();
	}
}
