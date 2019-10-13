package by.epam.javatraining.beseda.webproject.dao.entitydao;

import static by.epam.javatraining.beseda.webproject.util.LoggerName.TEST_LOGGER;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import by.epam.javatraining.beseda.webproject.connectionpool.ConnectionPool;
import by.epam.javatraining.beseda.webproject.connectionpool.TestPool;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.CustomerInterface;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.RequestInterface;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.RouteInterface;
import by.epam.javatraining.beseda.webproject.dao.util.dataloader.DatabaseEnumLoader;
import by.epam.javatraining.beseda.webproject.entity.Request;
import by.epam.javatraining.beseda.webproject.entity.exception.EntityIdException;
import by.epam.javatraining.beseda.webproject.entity.exception.RequestException;
import by.epam.javatraining.beseda.webproject.entity.user.Customer;
import by.epam.javatraining.beseda.webproject.integrationtests.databasecreator.DatabaseCreator;

public class RequestDAOTest {

	static Logger log = Logger.getLogger(TEST_LOGGER);
	static ConnectionPool pool;
	static RouteInterface routeDAO;
	static RequestInterface requestDAO;
	static CustomerInterface customerDAO;
	static TestDAOEntityFactory entityFactory;

	@BeforeClass
	public static void init() {
		pool = TestPool.createConnectionPool(DatabaseCreator.getDataSource());
		entityFactory = TestDAOEntityFactory.getFactory(pool);
		routeDAO = entityFactory.getRouteDAO();
		requestDAO = entityFactory.getRequestDAO();
		customerDAO = entityFactory.getCustomerDAO();
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
	public void testSelectActiveCustomerRequests() {
		List<Request> requests = null;
		try {
			requests = requestDAO.selectActiveCustomerRequests(10);
		} catch (DAOTechnicalException e) {
			log.error(e);
		}
		int[] result = new int[7];
		result[0] = requests.get(0).getId();
		result[1] = requests.get(1).getId();
		result[2] = requests.get(2).getId();
		result[3] = requests.get(3).getId();
		result[4] = requests.get(4).getId();
		result[5] = requests.get(5).getId();
		result[6] = requests.get(6).getId();
		assertArrayEquals(new int[] { 7, 9, 10, 12, 13, 14, 17 }, result);
	}

	@Test
	public void testUpdate() {
		try {
			Customer customer = customerDAO.getEntityById(10);
			Request req = new Request(customer, "some text");
			int id = requestDAO.add(req);
			req.setId(id);
			req.setComment("another text");
			requestDAO.update(req);
			Request readRequest = requestDAO.getEntityById(id);
			assertNotEquals("some text", readRequest.getComment());
		} catch (DAOLayerException | RequestException | EntityIdException e) {
			log.error(e);
		}
	}

	@Test
	public void testGetNewRequests() {
		try {
			List<Request> list = requestDAO.getNewRequests();
			assertEquals(5, list.size());
		} catch (DAOLayerException e) {
			log.error(e);
		}
	}

	@Test
	public void testGetCurrentRequestsId() {
		try {
			List<Request> requests = requestDAO.getCurrentRequests();
			int[] result = new int[4];
			result[0] = requests.get(0).getId();
			result[1] = requests.get(1).getId();
			result[2] = requests.get(2).getId();
			result[3] = requests.get(3).getId();
			assertArrayEquals(new int[] { 6, 7, 8, 10 }, result);
		} catch (DAOLayerException e) {
			log.error(e);
		}
	}

	@Test
	public void testGetFulfilledRequestsId() {
		try {
			List<Request> requests = requestDAO.getFulfilledRequests();
			int[] result = new int[5];
			result[0] = requests.get(0).getId();
			result[1] = requests.get(1).getId();
			result[2] = requests.get(2).getId();
			result[3] = requests.get(3).getId();
			result[4] = requests.get(4).getId();
			assertArrayEquals(new int[] { 1, 2, 3, 4, 5 }, result);
		} catch (DAOLayerException e) {
			log.error(e);
		}
	}

	@Test
	public void testGetRejectedRequestsId() {
		try {
			List<Request> requests = requestDAO.getRejectedRequests();
			int[] result = new int[3];
			result[0] = requests.get(0).getId();
			result[1] = requests.get(1).getId();
			result[2] = requests.get(2).getId();
			assertArrayEquals(new int[] { 12, 14, 16 }, result);
		} catch (DAOLayerException e) {
			log.error(e);
		}
	}

	@Test
	public void testNoRequestsIdFound() {
		try {
			DatabaseCreator.truncateTable("requests");
			List<Request> requests = requestDAO.getRejectedRequests();
			assertEquals(0, requests.size());
		} catch (DAOLayerException e) {
			log.error(e);
		}
	}

	@Test
	public void testNoNewRequests() {
		try {
			DatabaseCreator.truncateTable("requests");
			List<Request> list = requestDAO.getNewRequests();
			assertEquals(0, list.size());
		} catch (DAOLayerException e) {
			log.error(e);
		}
	}

}
