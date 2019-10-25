package by.epam.javatraining.beseda.webproject.dao.entitydao;

import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.USER_DRIVER;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
import by.epam.javatraining.beseda.webproject.dao.interfacedao.UserInterface;
import by.epam.javatraining.beseda.webproject.entity.user.Driver;
import by.epam.javatraining.beseda.webproject.entity.user.User;
import by.epam.javatraining.beseda.webproject.service.PasswordHash;
import by.epam.javatraining.beseda.webproject.util.TestDatabaseConfigure;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class,classes= {TestConfig.class})
public class UserDAOTest {


	@Autowired
	private UserInterface userDAO;

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
	public void testAdd() throws DAOLayerException {
		String newPassword = "1245QWEqw";
		byte[] hash = new PasswordHash().getHash(newPassword);
		User user = new User("123456", hash, USER_DRIVER);
		int id = userDAO.add(user);
		assertEquals(user, userDAO.getEntityById(id));
	}
	
	
	@Test
	public void testGetUserByLogin() {
		User user = userDAO.getUserByLogin("tatianaA");
		assertNotNull(user);
	}

	@Test
	public void testGetUserByLogin_no_such_user() {
		User user = userDAO.getUserByLogin("unreal user");
		assertNull(user);
	}

	@Test
	public void testGetUserByLoginAndPassword() {
		User user = userDAO.getUserByLoginAndPassword("tatianaA", new PasswordHash().getHash("25taniaQ"));
		assertNotNull(user);
	}

	@Test
	public void testGetUserByLoginAndPassword_no_such_user() {
		User user = userDAO.getUserByLoginAndPassword("tatianaA", new PasswordHash().getHash("wrong password"));
		assertNull(user);
	}

	@Test
	public void testUpdatePassword() {
		String newPassword = "12345QWEqw";
		byte[] hash = new PasswordHash().getHash(newPassword);
		User user = userDAO.getEntityById(3);
		user.setPassword(hash);
		userDAO.updatePassword(user.getId(), hash);
		User readUser = userDAO.getEntityById(user.getId());
		assertArrayEquals(hash, readUser.getPassword());
	}

}
