package by.epam.javatraining.beseda.webproject.servlet;

import by.epam.javatraining.beseda.webproject.model.service.actioncommand.ActionCommandFactory;
import by.epam.javatraining.beseda.webproject.model.service.commands.ActionCommand;
import by.epam.javatraining.beseda.webproject.util.connectionpool.ConnectionPool;
import by.epam.javatraining.beseda.webproject.util.connectionpool.DBConnector;
import by.epam.javatraining.beseda.webproject.util.srcontent.SessionRequestContent;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

import static by.epam.javatraining.beseda.webproject.util.jspproperties.JSPPath.LOGIN_PAGE;
import static by.epam.javatraining.beseda.webproject.util.resourceloader.DatabaseProperties.DATABASE_PASSWORD;
import static by.epam.javatraining.beseda.webproject.util.resourceloader.DatabaseProperties.DATABASE_URL;
import static by.epam.javatraining.beseda.webproject.util.resourceloader.DatabaseProperties.DATABASE_USER;

@WebServlet("/controller")
public class Controller extends HttpServlet {

    private Logger log = Logger.getLogger(Controller.class.getSimpleName());
    private ConnectionPool pool;

    @Override
    public void init() {
        pool = DBConnector.createConnectionPool(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionRequestContent requestContent = new SessionRequestContent(req);

        ActionCommandFactory client = new ActionCommandFactory();
        ActionCommand command = client.defineCommand(requestContent);

        String page = command.execute(requestContent);

        if (!LOGIN_PAGE.equals(page)) {
            requestContent.insertAttributes(req);
            requestContent.insertSessionAttributes(req);
        } else {
            req.getSession().invalidate();
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(req, resp);
    }

    @Override
    public void destroy() {
        try {
            pool.closePool();
        } catch (SQLException e) {
            log.error("Error closing connection pool: " + e);
        }
    }
}
