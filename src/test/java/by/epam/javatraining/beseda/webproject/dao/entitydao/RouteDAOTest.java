package by.epam.javatraining.beseda.webproject.dao.entitydao;

import static by.epam.javatraining.beseda.webproject.util.LoggerName.TEST_LOGGER;
import static org.junit.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import by.epam.javatraining.beseda.webproject.config.EnumConfig;
import by.epam.javatraining.beseda.webproject.config.ResultSetExtractorConfig;
import by.epam.javatraining.beseda.webproject.config.RowMapperConfig;
import by.epam.javatraining.beseda.webproject.config.TestConfig;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.CarInterface;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.CustomerInterface;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.RequestInterface;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.RouteInterface;
import by.epam.javatraining.beseda.webproject.entity.Request;
import by.epam.javatraining.beseda.webproject.entity.route.Route;
import by.epam.javatraining.beseda.webproject.entity.user.Customer;
import by.epam.javatraining.beseda.webproject.util.TestDatabaseConfigure;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class,classes= {TestConfig.class})
public class RouteDAOTest {

	@Autowired
	private RouteInterface routeDAO;	
	
	@Autowired
	private RequestInterface requestDAO;
	
	@Autowired
	private CustomerInterface customerDAO;
	
	@Autowired
	private TestDatabaseConfigure dbc;
	
	private Logger log = Logger.getLogger(TEST_LOGGER);


	@Before
	public void fillData() {
		dbc.fillDatabase();
	}

	@After
	public void cleanData() {
		dbc.cleanDatabase();
	}

	@Test
	public void testAdd() {
		try {
			Customer customer = customerDAO.getEntityById(10);
			Request req = new Request(customer, "new request");
			int id = requestDAO.add(req);
			Route route = new Route(id, "route with id=" + id);
			routeDAO.add(route);
			Route readRoute = routeDAO.getEntityById(id);
			assertEquals(route, readRoute);
		} catch (DAOLayerException e) {
			log.error(e);
		}
	}


}
