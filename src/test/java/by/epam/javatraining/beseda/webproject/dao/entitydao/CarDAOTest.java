package by.epam.javatraining.beseda.webproject.dao.entitydao;

import static by.epam.javatraining.beseda.webproject.util.LoggerName.TEST_LOGGER;
import static org.junit.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import by.epam.javatraining.beseda.webproject.config.EnumConfig;
import by.epam.javatraining.beseda.webproject.config.ResultSetExtractorConfig;
import by.epam.javatraining.beseda.webproject.config.RowMapperConfig;
import by.epam.javatraining.beseda.webproject.config.TestConfig;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.CarInterface;
import by.epam.javatraining.beseda.webproject.entity.car.Bus;
import by.epam.javatraining.beseda.webproject.entity.car.Truck;
import by.epam.javatraining.beseda.webproject.util.TestDatabaseConfigure;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class,classes= {TestConfig.class})
public class CarDAOTest {

	static Logger log = Logger.getLogger(TEST_LOGGER);

	@Autowired
	private CarInterface carDAO;

	@Autowired
	private TestDatabaseConfigure dbc;
	
	@Before
	public void fillData() {
		dbc.fillDatabase();
	}

	@After
	public void cleanData() {
		dbc.cleanDatabase();
	}

	
	@Test(expected = Exception.class)
	public void testAdd_wrong_bus()  throws DAOLayerException{
			Bus bus = new Bus(null, null, 5);
			int id = carDAO.add(bus);
				bus.setId(id);
			Bus readCar = (Bus) carDAO.getEntityById(id);
			assertEquals(bus, readCar);
	}
	
	@Test
	public void testAdd_bus() {
		try {
			Bus bus = new Bus("123", "Nissan", 5);
			int id = carDAO.add(bus);
			Bus readCar = (Bus) carDAO.getEntityById(id);
			assertEquals(bus, readCar);
		} catch (DAOLayerException e) {
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
		} catch (DAOLayerException e) {
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
		} catch (DAOLayerException e) {
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
		} catch (DAOLayerException e) {
			log.error(e);
		}
	}

}
