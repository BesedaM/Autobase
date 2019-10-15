package by.epam.javatraining.beseda.webproject.dao.entitydao;

import static by.epam.javatraining.beseda.webproject.util.LoggerName.TEST_LOGGER;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import by.epam.javatraining.beseda.webproject.dao.exception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.UserInterface;
import by.epam.javatraining.beseda.webproject.entity.exception.UserException;
import by.epam.javatraining.beseda.webproject.entity.user.User;
import by.epam.javatraining.beseda.webproject.integrationtests.databasecreator.DatabaseConfigure;
import by.epam.javatraining.beseda.webproject.service.PasswordHash;

public class UserDAOTest {

	static Logger log = Logger.getLogger(TEST_LOGGER);

	@Autowired
	static UserInterface userDAO;


	@BeforeMethod
	public void fillData() {
		DatabaseConfigure.fillDatabase();
	}

	@AfterMethod
	public void cleanData() {
		DatabaseConfigure.cleanDatabase();
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
