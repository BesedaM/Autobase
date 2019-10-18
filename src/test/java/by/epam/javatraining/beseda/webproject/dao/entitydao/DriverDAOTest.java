package by.epam.javatraining.beseda.webproject.dao.entitydao;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
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
import by.epam.javatraining.beseda.webproject.dao.interfacedao.DriverInterface;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.UserInterface;
import by.epam.javatraining.beseda.webproject.entity.user.Driver;
import by.epam.javatraining.beseda.webproject.entity.user.User;
import by.epam.javatraining.beseda.webproject.service.PasswordHash;
import by.epam.javatraining.beseda.webproject.util.TestDatabaseConfigure;

import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.USER_DRIVER;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { RowMapperConfig.class,
		ResultSetExtractorConfig.class, EnumConfig.class, TestConfig.class })
public class DriverDAOTest {

	@Autowired
	private UserInterface userDAO;

	@Autowired
	private DriverInterface driverDAO;

	@Autowired
	private TestDatabaseConfigure dbc;

	static User user;

	@BeforeClass
	public static void createEntities() {
		String newPassword = "1245QWEqw";
		byte[] hash = PasswordHash.getHash(newPassword);
		user = new User("123456", hash, USER_DRIVER);
	}

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
		int id = userDAO.add(user);
		Driver driver = new Driver(user, "Денис", "Гутеев", "123-34-786");
		driver.setId(id);
		driverDAO.add(driver);
		assertEquals(driver, driverDAO.getEntityById(id));
	}

	@Test
	public void testUpdate() throws DAOLayerException {
		int id = userDAO.add(user);
		Driver driver = new Driver(user, "Денис", "Гутеев", "123-34-786");
		driver.setId(id);
		driverDAO.add(driver);
		driver.setSurname("Финдеев");
		driverDAO.update(driver);
		assertEquals(driver, driverDAO.getEntityById(id));
	}

}
