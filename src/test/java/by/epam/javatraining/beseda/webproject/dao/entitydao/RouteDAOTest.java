package by.epam.javatraining.beseda.webproject.dao.entitydao;

import static by.epam.javatraining.beseda.webproject.util.LoggerName.TEST_LOGGER;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import by.epam.javatraining.beseda.webproject.connectionpool.ConnectionPool;
import by.epam.javatraining.beseda.webproject.connectionpool.TestPool;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.CarInterface;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.CustomerInterface;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.RequestInterface;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.RouteInterface;
import by.epam.javatraining.beseda.webproject.dao.util.dataloader.DatabaseEnumLoader;
import by.epam.javatraining.beseda.webproject.entity.Request;
import by.epam.javatraining.beseda.webproject.entity.exception.EntityIdException;
import by.epam.javatraining.beseda.webproject.entity.route.Route;
import by.epam.javatraining.beseda.webproject.entity.user.Customer;
import by.epam.javatraining.beseda.webproject.integrationtests.databasecreator.DatabaseCreator;

class RouteDAOTest {

	static Logger log = Logger.getLogger(TEST_LOGGER);
	static ConnectionPool pool;
	static RouteInterface routeDAO;
	static RequestInterface requestDAO;
	static CustomerInterface customerDAO;
	static CarInterface carDAO;
	static TestDAOEntityFactory entityFactory;

	@BeforeClass
	public static void init() {
		pool = TestPool.createConnectionPool(DatabaseCreator.getDataSource());
		entityFactory = TestDAOEntityFactory.getFactory(pool);
		routeDAO = entityFactory.getRouteDAO();
		requestDAO = entityFactory.getRequestDAO();
		customerDAO = entityFactory.getCustomerDAO();
		carDAO = entityFactory.getCarDAO();
	}

	@AfterClass
	public static void destroy() throws IOException {
		try {
			pool.closePool();
		} catch (SQLException e) {
			log.error(e);
		}
	}

	@BeforeMethod
	public void fillData() {
		DatabaseCreator.fillDatabase();
		DatabaseEnumLoader.loadWithConnectionPool(pool);
	}

	@AfterMethod
	public void cleanData() {
		DatabaseCreator.cleanDatabase();
	}

	@Test
	public void testAdd() {
		try {
			Customer customer = customerDAO.getEntityById(11);
			Request req = new Request(customer, "new request", "рассматривается");
			int id = requestDAO.add(req);
			req.setId(id);
			Route route = new Route(id, "route with id=" + id);
			routeDAO.add(route);
			Route readRoute = routeDAO.getEntityById(id);
			assertEquals(route, readRoute);
		} catch (DAOLayerException | EntityIdException e) {
			log.error(e);
		}
	}

	@Test
	public void testUpdateRouteStatus() {
		try {
			Customer customer = customerDAO.getEntityById(11);
			Request req = new Request(customer, "new request", "рассматривается");
			int id = requestDAO.add(req);
			req.setId(id);
			Route route = new Route(id, "route with id=" + id);
			routeDAO.add(route);
			routeDAO.updateRouteStatus(id, "выполнен");
			Route readRoute = routeDAO.getEntityById(id);
			assertEquals("выполнен", readRoute.getStatus());
		} catch (DAOLayerException | EntityIdException e) {
			log.error(e);
		}
	}

	@Test
	public void testUpdateRouteStatus_wrong_data() throws DAOLayerException {
		Customer customer = customerDAO.getEntityById(11);
		Request req = new Request(customer, "new request");
		int id = requestDAO.add(req);
		Route route = new Route(id, "route with id=" + id);
		routeDAO.add(route);
		routeDAO.updateRouteStatus(id, "!!!!");
		Route readRoute = routeDAO.getEntityById(id);
		assertEquals("запланирован", readRoute.getStatus());
	}

}
