package by.epam.javatraining.beseda.webproject.logic;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.testng.annotations.Test;

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
