package by.epam.javatraining.beseda.webproject.logic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.epam.javatraining.beseda.webproject.service.ServiceConstants.PASSWORD_VALIDATE;

public class RegisterLogic {

	private static Pattern pattern = Pattern.compile(PASSWORD_VALIDATE);

	public static boolean legalPassword(String password) {
		boolean isLegal = false;
		if (password != null) {
			Matcher matcher = pattern.matcher(password);
			isLegal = matcher.matches();
		}
		return isLegal;
	}
}