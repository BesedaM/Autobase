package by.epam.javatraining.beseda.webproject.dao.entitydao;

import static by.epam.javatraining.beseda.webproject.util.LoggerName.TEST_LOGGER;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.GregorianCalendar;
import java.util.List;

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
import by.epam.javatraining.beseda.webproject.dao.interfacedao.TaskInterface;
import by.epam.javatraining.beseda.webproject.entity.route.Task;
import by.epam.javatraining.beseda.webproject.util.TestDatabaseConfigure;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {TestConfig.class })
public class TaskDAOTest {

	private Logger log = Logger.getLogger(TEST_LOGGER);

	@Autowired
	private TaskInterface taskDAO;

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

	@Test
	public void testAdd() {
		try {
			Task task = new Task(null, new GregorianCalendar(), "New data");
			int id = taskDAO.add(task);
			Task readTask = taskDAO.getEntityById(id);
			assertNotNull(readTask);
		} catch (DAOLayerException e) {
			log.error(e);
		}
	}

	@Test(expected = Exception.class)
	public void testAdd_wrong_data() throws DAOLayerException {
		Task task = new Task(null, new GregorianCalendar(), null);
		int id = taskDAO.add(task);
		Task readTask = taskDAO.getEntityById(id);
		assertEquals(task, readTask);
	}

	@Test
	public void testUpdate() {
		try {
			Task testTask = new Task(null, new GregorianCalendar(), "New data");
			int id = taskDAO.add(testTask);
			testTask.setId(id);
			testTask.setDetails("Некоторые новые детали");
			taskDAO.update(testTask);
			Task readTask = taskDAO.getEntityById(id);
			assertEquals(testTask, readTask);
		} catch (DAOLayerException e) {
			log.error(e);
		}
	}

	@Test
	public void testDelete() {
		try {
			Task testTask = new Task(null, new GregorianCalendar(), "New data");
			int id = taskDAO.add(testTask);
			testTask.setId(id);
			taskDAO.delete(testTask);
			Task readTask = taskDAO.getEntityById(id);
			assertEquals(null, readTask);
		} catch (DAOLayerException e) {
			log.error(e);
		}
	}

	@Test
	public void testGetAll() {
		try {
			Task task01 = new Task(null, new GregorianCalendar(), "New data01");
			taskDAO.add(task01);

			Task task02 = new Task(null, new GregorianCalendar(), "New data02");
			taskDAO.add(task02);

			Task task03 = new Task(null, new GregorianCalendar(), "New data03");
			taskDAO.add(task03);

			List<Task> all = taskDAO.getAll();

			assertTrue(all.contains(task01) && all.contains(task02) && all.contains(task03));
		} catch (DAOLayerException e) {
			log.error(e);
		}
	}

	@Test
	public void testGetEntitiesByIdList() {
		try {
			Task task01 = new Task(null, new GregorianCalendar(), "New data01");
			int id01 = taskDAO.add(task01);

			Task task02 = new Task(null, new GregorianCalendar(), "New data02");
			int id02 = taskDAO.add(task02);

			Task task03 = new Task(null, new GregorianCalendar(), "New data03");
			int id03 = taskDAO.add(task03);

			List<Task> some = taskDAO.getEntitiesByIdList(new int[] { id01, id02, id03 });
			assertTrue(some.size() == 3);
		} catch (DAOLayerException e) {
			log.error(e);
		}
	}

}
