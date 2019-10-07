package by.epam.javatraining.beseda.webproject.controller.servlet;

import static by.epam.javatraining.beseda.webproject.connectionpool.DatabaseProperties.DATABASE_PASSWORD;
import static by.epam.javatraining.beseda.webproject.connectionpool.DatabaseProperties.DATABASE_URL;
import static by.epam.javatraining.beseda.webproject.connectionpool.DatabaseProperties.DATABASE_USER;
import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.epam.javatraining.beseda.webproject.connectionpool.ConnectionPool;
import by.epam.javatraining.beseda.webproject.connectionpool.DBConnector;
import by.epam.javatraining.beseda.webproject.controller.command.ActionCommand;
import by.epam.javatraining.beseda.webproject.controller.command.ActionCommandFactory;
import by.epam.javatraining.beseda.webproject.controller.command.util.srcontent.SessionRequestContent;

@SuppressWarnings("serial")
@WebServlet(name = "TruckingCompany", urlPatterns = "/controller")
public class Controller extends HttpServlet {

	private static Logger log;
	private static ConnectionPool pool;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		log = Logger.getLogger(ERROR_LOGGER);
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

		ActionCommand command = ActionCommandFactory.defineCommand(requestContent);

		String page = command.execute(requestContent);

		requestContent.insertAttributes(req);

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