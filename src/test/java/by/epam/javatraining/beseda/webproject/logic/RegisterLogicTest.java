package by.epam.javatraining.beseda.webproject.logic;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import by.epam.javatraining.beseda.webproject.config.EnumConfig;
import by.epam.javatraining.beseda.webproject.config.ResultSetExtractorConfig;
import by.epam.javatraining.beseda.webproject.config.RowMapperConfig;
import by.epam.javatraining.beseda.webproject.config.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { RowMapperConfig.class, ResultSetExtractorConfig.class, EnumConfig.class,
		TestConfig.class })
public class RegisterLogicTest {

	private final String goodSmallPassword = "1sWuiq";

	@Test
	public void testLegalPassword_smallButOk() {
		assertTrue(RegisterLogic.legalPassword(goodSmallPassword));
	}

	@Test
	public void testLegalPassword_tooSmall() {
		String password = goodSmallPassword.substring(0, 4);
		assertFalse(RegisterLogic.legalPassword(password));
	}

	@Test
	public void testLegalPassword_tooBig() {
		String password = "1sWui";
		String pass = password + password + password + password + 1;
		assertFalse(RegisterLogic.legalPassword(pass));
	}

	@Test
	public void testLegalPassword_bigButOk() {
		String password = "1sWui";
		String pass = password + password + password + password;
		assertTrue(RegisterLogic.legalPassword(pass));
	}

	@Test
	public void testLegalPassword_noNumbers() {
		String password = goodSmallPassword.replace('1', 'a');
		assertFalse(RegisterLogic.legalPassword(password));
	}

	@Test
	public void testLegalPassword_noUpperCase() {
		String password = goodSmallPassword.toLowerCase();
		assertFalse(RegisterLogic.legalPassword(password));
	}

	@Test
	public void testLegalPassword_noLowerCase() {
		String password = goodSmallPassword.toUpperCase();
		assertFalse(RegisterLogic.legalPassword(password));
	}

}
