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
import by.epam.javatraining.beseda.webproject.dao.util.dataloader.DatabaseEnumLoader;
import by.epam.javatraining.beseda.webproject.entity.car.Bus;
import by.epam.javatraining.beseda.webproject.entity.car.Truck;
import by.epam.javatraining.beseda.webproject.entity.exception.CarException;
import by.epam.javatraining.beseda.webproject.entity.exception.EntityIdException;
import by.epam.javatraining.beseda.webproject.integrationtests.databasecreator.DatabaseCreator;

public class CarDAOTest {

	static Logger log = Logger.getLogger(TEST_LOGGER);
	static ConnectionPool pool;
	static CarInterface carDAO;
	static TestDAOEntityFactory entityFactory;

	@BeforeClass
	public static void init() {
		pool = TestPool.createConnectionPool(DatabaseCreator.getDataSource());
		entityFactory = TestDAOEntityFactory.getFactory(pool);
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

	
	@Test(expectedExceptions = DAOLayerException.class)
	public void testAdd_wrong_bus()  throws DAOLayerException{
			Bus bus = new Bus(null, null, 5);
			int id = carDAO.add(bus);
			try {
				bus.setId(id);
			} catch (EntityIdException e) {
				log.error(e);
			}
			Bus readCar = (Bus) carDAO.getEntityById(id);
			assertEquals(bus, readCar);
	}
	
	@Test
	public void testAdd_bus() {
		try {
			Bus bus = new Bus("123", "Nissan", 5);
			int id = carDAO.add(bus);
			bus.setId(id);
			Bus readCar = (Bus) carDAO.getEntityById(id);
			assertEquals(bus, readCar);
		} catch (DAOLayerException | EntityIdException e) {
			log.error(e);
		}
	}

	@Test
	public void testAdd_truck() {
		try {
			Truck truck = new Truck("999", "truck", 0);
			int id = carDAO.add(truck);
			truck.setId(id);
			Truck readCar = (Truck) carDAO.getEntityById(id);
			assertEquals(truck, readCar);
		} catch (DAOLayerException | EntityIdException e) {
			log.error(e);
		}
	}

	@Test
	public void testUpdate_bus() {
		try {
			Bus bus = new Bus("123", "Nissan", 5);
			int id = carDAO.add(bus);
			bus.setId(id);
			bus.setSeats(4);
			carDAO.update(bus);
			Bus readCar = (Bus) carDAO.getEntityById(id);
			assertEquals(bus, readCar);
		} catch (DAOLayerException | CarException | EntityIdException e) {
			log.error(e);
		}
	}

	@Test
	public void testUpdate_truck() {
		try {
			Truck truck = new Truck("999", "truck", 0);
			int id = carDAO.add(truck);
			truck.setNumber("000");
			truck.setId(id);
			carDAO.update(truck);
			Truck readCar = (Truck) carDAO.getEntityById(id);
			assertEquals(truck, readCar);
		} catch (DAOLayerException | CarException | EntityIdException e) {
			log.error(e);
		}
	}

}
