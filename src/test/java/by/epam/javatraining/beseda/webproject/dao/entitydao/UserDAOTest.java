package by.epam.javatraining.beseda.webproject.dao.entitydao;

import static by.epam.javatraining.beseda.webproject.util.LoggerName.TEST_LOGGER;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
import by.epam.javatraining.beseda.webproject.dao.interfacedao.UserInterface;
import by.epam.javatraining.beseda.webproject.dao.util.dataloader.DatabaseEnumLoader;
import by.epam.javatraining.beseda.webproject.entity.exception.UserException;
import by.epam.javatraining.beseda.webproject.entity.user.User;
import by.epam.javatraining.beseda.webproject.integrationtests.databasecreator.DatabaseCreator;
import by.epam.javatraining.beseda.webproject.service.PasswordHash;

public class UserDAOTest {

	static Logger log = Logger.getLogger(TEST_LOGGER);
	static ConnectionPool pool;
	static UserInterface userDAO;
	static TestDAOEntityFactory entityFactory;

	@BeforeClass
	public static void init() {
		pool = TestPool.createConnectionPool(DatabaseCreator.getDataSource());
		entityFactory = TestDAOEntityFactory.getFactory(pool);
		userDAO = entityFactory.getUserDAO();
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
	public void testGetUserByLogin() {
		try {
			User user = userDAO.getUserByLogin("tatianaA");
			assertNotNull(user);
		} catch (DAOLayerException e) {
			log.error(e);
		}
	}

	@Test
	public void testGetUserByLogin_no_such_user() {
		try {
			User user = userDAO.getUserByLogin("unreal user");
			assertNull(user);
		} catch (DAOLayerException e) {
			log.error(e);
		}
	}

	@Test
	public void testGetUserByLoginAndPassword() {
		try {
			User user = userDAO.getUserByLoginAndPassword("tatianaA", PasswordHash.getHash("25taniaQ"));
			assertNotNull(user);
		} catch (DAOLayerException e) {
			log.error(e);
		}
	}

	@Test
	public void testGetUserByLoginAndPassword_no_such_user() {
		try {
			User user = userDAO.getUserByLoginAndPassword("tatianaA", PasswordHash.getHash("wrong password"));
			assertNull(user);
		} catch (DAOLayerException e) {
			log.error(e);
		}
	}

	@Test
	public void testUpdatePassword() {
		try {
			String newPassword = "12345QWEqw";
			byte[] hash = PasswordHash.getHash(newPassword);
			User user = userDAO.getEntityById(3);
			user.setPassword(hash);
			userDAO.updatePassword(user.getId(), hash);
			User readUser = userDAO.getEntityById(user.getId());
			assertArrayEquals(hash, readUser.getPassword());
		} catch (DAOLayerException | UserException e) {
			log.error(e);
		}
	}

}
