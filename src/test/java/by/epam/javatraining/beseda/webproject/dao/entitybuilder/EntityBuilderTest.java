package by.epam.javatraining.beseda.webproject.dao.entitybuilder;

import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.CUSTOMER_INDIVIDUAL;
import static by.epam.javatraining.beseda.webproject.util.LoggerName.TEST_LOGGER;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.sql.SQLException;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import by.epam.javatraining.beseda.webproject.connectionpool.ConnectionPool;
import by.epam.javatraining.beseda.webproject.connectionpool.TestPool;
import by.epam.javatraining.beseda.webproject.dao.entitydao.TestDAOEntityFactory;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.AddressInterface;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.CarInterface;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.CustomerInterface;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.DriverInterface;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.RequestInterface;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.RouteInterface;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.TaskInterface;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.UserInterface;
import by.epam.javatraining.beseda.webproject.dao.util.dataloader.DatabaseEnumLoader;
import by.epam.javatraining.beseda.webproject.entity.Request;
import by.epam.javatraining.beseda.webproject.entity.car.Bus;
import by.epam.javatraining.beseda.webproject.entity.car.Truck;
import by.epam.javatraining.beseda.webproject.entity.exception.EntityIdException;
import by.epam.javatraining.beseda.webproject.entity.route.Address;
import by.epam.javatraining.beseda.webproject.entity.route.Route;
import by.epam.javatraining.beseda.webproject.entity.route.Task;
import by.epam.javatraining.beseda.webproject.entity.user.Customer;
import by.epam.javatraining.beseda.webproject.entity.user.Driver;
import by.epam.javatraining.beseda.webproject.entity.user.User;
import by.epam.javatraining.beseda.webproject.integrationtests.databasecreator.DatabaseCreator;
import by.epam.javatraining.beseda.webproject.service.PasswordHash;

public class EntityBuilderTest {

	static Logger log = Logger.getLogger(TEST_LOGGER);
	static ConnectionPool pool;
	static TestDAOEntityFactory entityFactory;

	@BeforeClass
	public static void init() {
		pool = TestPool.createConnectionPool(DatabaseCreator.getDataSource());
		entityFactory = TestDAOEntityFactory.getFactory(pool);
		DatabaseCreator.fillDatabase();
		DatabaseEnumLoader.loadWithConnectionPool(pool);
	}

	@AfterClass
	public static void destroy() throws IOException {
		try {
			pool.closePool();
		} catch (SQLException e) {
			log.error(e);
		}
	}

	@Test
	public void testBuildAddress() {
		Address addr = new Address("Germany", "blue", "Berlin", "Main str.", 10, "a");
		AddressInterface addressDAO = entityFactory.getAddressDAO();
		try {
			int id = addressDAO.add(addr);
			addr.setId(id);
			Address read = addressDAO.getEntityById(id);
			assertEquals(addr, read);
		} catch (DAOLayerException | EntityIdException e) {
			log.error(e);
		}
	}

	@Test
	public void testBuildBus() {
		CarInterface carDAO = entityFactory.getCarDAO();
		try {
			Bus bus = new Bus("123456", "Volvo", 2);
			int id = carDAO.add(bus);
			bus.setId(id);
			Bus readCar = (Bus) carDAO.getEntityById(id);
			assertEquals(bus, readCar);
		} catch (DAOLayerException | EntityIdException e) {
			log.error(e);
		}
	}

	@Test
	public void testBuildTruck() {
		CarInterface carDAO = entityFactory.getCarDAO();
		try {
			Truck truck = new Truck("MAZ-410", "БелАЗ", 6);
			int id = carDAO.add(truck);
			truck.setId(id);
			Truck readCar = (Truck) carDAO.getEntityById(id);
			assertEquals(truck, readCar);
		} catch (DAOLayerException | EntityIdException e) {
			log.error(e);
		}
	}

	@Test
	public void testBuildUser() {
		UserInterface userDAO = entityFactory.getUserDAO();
		try {
			User user = new User("slon", PasswordHash.getHash("where1is"), "admin");
			int id = userDAO.add(user);
			user.setId(id);
			User readUser = userDAO.getEntityById(id);
			assertEquals(user, readUser);
		} catch (DAOLayerException | EntityIdException e) {
			log.error(e);
		}
	}

	@Test
	public void testBuildCustomer() {
		UserInterface userDAO = entityFactory.getUserDAO();
		CustomerInterface customerDAO = entityFactory.getCustomerDAO();
		try {
			User user = new User("slon_01", PasswordHash.getHash("where1is"), "customer");
			int id = userDAO.add(user);
			user.setId(id);
			Customer customer = new Customer(user, "Irina", "Hmelnova", "(029)158-98-74", CUSTOMER_INDIVIDUAL,
					"ghghg@gmail.ru");
			customerDAO.add(customer);
			Customer readCustomer = customerDAO.getEntityById(id);
			assertEquals(customer, readCustomer);
		} catch (DAOLayerException | EntityIdException e) {
			log.error(e);
		}
	}

	@Test
	public void testBuildDriver() {
		UserInterface userDAO = entityFactory.getUserDAO();
		DriverInterface driverDAO = entityFactory.getDriverDAO();
		try {
			User user = new User("slon_02", PasswordHash.getHash("where1is"), "driver");
			int id = userDAO.add(user);
			user.setId(id);
			Driver driver = new Driver(user, "Fedor", "Hmelnov", "(029)138-68-74");
			driverDAO.add(driver);
			Driver readDriver = driverDAO.getEntityById(id);
			assertEquals(driver, readDriver);
		} catch (DAOLayerException | EntityIdException e) {
			log.error(e);
		}
	}

	@Test
	public void testBuildRequest() {
		RequestInterface requestDAO = entityFactory.getRequestDAO();
		CustomerInterface customerDAO = entityFactory.getCustomerDAO();
		try {
			Customer customer = customerDAO.getEntityById(10);
			Request request = new Request(customer, "перевозка пассажиров до р.Вилия");
			int id = requestDAO.add(request);
			request.setId(id);
			Request readRequest = requestDAO.getEntityById(id);
			assertEquals(request, readRequest);
		} catch (DAOLayerException | EntityIdException e) {
			log.error(e);
		}
	}

	@Test
	public void testBuildRoute() {
		RequestInterface requestDAO = entityFactory.getRequestDAO();
		RouteInterface routeDAO = entityFactory.getRouteDAO();
		CustomerInterface customerDAO = entityFactory.getCustomerDAO();
		try {
			Customer customer = customerDAO.getEntityById(10);
			Request request = new Request(customer, "перевозка пассажиров до р.Вилия");
			int id = requestDAO.add(request);
			request.setId(id);
			Route route = new Route(id, "Fishing");
			routeDAO.add(route);
			Route readRoute = routeDAO.getEntityById(id);
			assertEquals(route, readRoute);
		} catch (DAOLayerException | EntityIdException e) {
			log.error(e);
		}
	}

}
