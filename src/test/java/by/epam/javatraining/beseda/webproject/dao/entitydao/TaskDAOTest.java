package by.epam.javatraining.beseda.webproject.dao.entitydao;

import static by.epam.javatraining.beseda.webproject.util.LoggerName.TEST_LOGGER;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import by.epam.javatraining.beseda.webproject.dao.exception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.TaskInterface;
import by.epam.javatraining.beseda.webproject.entity.exception.EntityIdException;
import by.epam.javatraining.beseda.webproject.entity.exception.TaskException;
import by.epam.javatraining.beseda.webproject.entity.route.Task;
import by.epam.javatraining.beseda.webproject.integrationtests.databasecreator.DatabaseConfigure;

public class TaskDAOTest {

	static Logger log = Logger.getLogger(TEST_LOGGER);

	@Autowired
	static TaskInterface taskDAO;


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
			Task task = new Task(null, new GregorianCalendar(), "New data");
			int id = taskDAO.add(task);
			Task readTask = taskDAO.getEntityById(id);
			assertNotNull(readTask);
		} catch (DAOLayerException e) {
			log.error(e);
		}
	}

	@Test(expectedExceptions = DAOLayerException.class)
	public void testAdd_wrong_data() throws DAOLayerException{
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
		} catch (DAOLayerException | TaskException | EntityIdException e) {
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
		} catch (DAOLayerException | EntityIdException e) {
			log.error(e);
		}
	}

	@Test
	public void testGetAll() {
		try {
			Task task01 = new Task(null, new GregorianCalendar(), "New data01");
			int id01 = taskDAO.add(task01);
			task01.setId(id01);

			Task task02 = new Task(null, new GregorianCalendar(), "New data02");
			int id02 = taskDAO.add(task02);
			task02.setId(id02);

			Task task03 = new Task(null, new GregorianCalendar(), "New data03");
			int id03 = taskDAO.add(task03);
			task03.setId(id03);

			List<Task> all = taskDAO.getAll();

			assertTrue(all.contains(task01) && all.contains(task02) && all.contains(task03));
		} catch (DAOLayerException | EntityIdException e) {
			log.error(e);
		}
	}

	@Test
	public void testGetEntitiesByIdList() {
		try {
			Task task01 = new Task(null, new GregorianCalendar(), "New data01");
			int id01 = taskDAO.add(task01);
			task01.setId(id01);

			Task task02 = new Task(null, new GregorianCalendar(), "New data02");
			int id02 = taskDAO.add(task02);
			task02.setId(id02);

			Task task03 = new Task(null, new GregorianCalendar(), "New data03");
			int id03 = taskDAO.add(task03);
			task03.setId(id03);

			List<Task> some = taskDAO.getEntitiesByIdList(new int[] { id01, id03 });

			assertTrue(some.contains(task01) && !some.contains(task02) && some.contains(task03));
		} catch (DAOLayerException | EntityIdException e) {
			log.error(e);
		}
	}

}
