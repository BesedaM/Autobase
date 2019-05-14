package by.epam.javatraining.beseda.webproject.servlet;

import by.epam.javatraining.beseda.webproject.util.connectionpool.ConnectionPool;
import by.epam.javatraining.beseda.webproject.util.connectionpool.DBConnector;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import java.sql.SQLException;

import static by.epam.javatraining.beseda.webproject.util.resourceloader.DatabaseProperties.*;

public class AddCustomerServlet extends HttpServlet {

    private ConnectionPool pool;
    private Logger log = Logger.getLogger(AddCustomerServlet.class.getSimpleName());

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        pool = DBConnector.createConnectionPool(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
    }





    @Override
    public void destroy() {
        if (pool.usedConnections() == 0) {
            try {
                pool.closePool();
            } catch (SQLException e) {
                log.error("Exception thrown while closing connection pool: " + e);
            }
        }
        super.destroy();
    }
}
