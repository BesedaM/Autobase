package by.epam.javatraining.beseda.webproject.dao.util.connectionpool;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionPool {
    /**
     * Method for getting a valid Connection object from ConnectionPool.
     *
     * @return Connection object
     * @throws SQLException
     */
    Connection getConnection() throws SQLException;


    /**
     * Method for getting a valid Connection object from ConnectionPool.
     *
     * @param user     the database user on whose behalf the connection is being made
     * @param password user's password
     * @return Connection object
     * @throws SQLException
     */
    Connection getConnection(String user, String password) throws SQLException;


    /**
     * Returns used connection back to the pool.
     *
     * @param с used connection
     */
    void releaseConnection(Connection conn);

    /**
     * @return current pool size
     */
    int size();

    /**
     *
     * @return number of currently used Connections
     */
    int connectionsInUse();

    /**
     * Closes the pool.
     *
     * @throws SQLException
     */
    void closePool() throws SQLException;
}