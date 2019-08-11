package by.epam.javatraining.beseda.webproject.model.logic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.epam.javatraining.beseda.webproject.model.service.ServiceConstants.PASSWORD_VALIDATE;

public class RegisterLogic {

    public static boolean legalPassword(String password) {
        Pattern pattern = Pattern.compile(PASSWORD_VALIDATE);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
