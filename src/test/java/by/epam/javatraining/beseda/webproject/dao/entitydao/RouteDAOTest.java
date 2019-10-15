package by.epam.javatraining.beseda.webproject.dao.entitydao;

import static by.epam.javatraining.beseda.webproject.util.LoggerName.TEST_LOGGER;
import static org.junit.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import by.epam.javatraining.beseda.webproject.dao.exception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.CarInterface;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.CustomerInterface;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.RequestInterface;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.RouteInterface;
import by.epam.javatraining.beseda.webproject.entity.Request;
import by.epam.javatraining.beseda.webproject.entity.exception.EntityIdException;
import by.epam.javatraining.beseda.webproject.entity.route.Route;
import by.epam.javatraining.beseda.webproject.entity.user.Customer;
import by.epam.javatraining.beseda.webproject.integrationtests.databasecreator.DatabaseConfigure;

class RouteDAOTest {

	@Autowired
	static CarInterface carDAO;
	
	@Autowired
	static RouteInterface routeDAO;	
	
	@Autowired
	static RequestInterface requestDAO;
	
	@Autowired
	static CustomerInterface customerDAO;
	
	static Logger log = Logger.getLogger(TEST_LOGGER);


	@BeforeMethod
	public void fillData() {
		DatabaseConfigure.fillDatabase();
	}

	@AfterMethod
	public void cleanData() {
		DatabaseConfigure.cleanDatabase();
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
