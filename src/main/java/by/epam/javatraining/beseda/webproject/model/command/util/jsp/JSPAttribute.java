package by.epam.javatraining.beseda.webproject.model.command.util.jsp;

public class JSPAttribute {

    public static final String ERROR_MESSAGE;
    public static final String ERROR_MESSAGE_TEXT;

    public static final String ERROR_REGISTER_LOGIN;
    public static final String ERROR_REGISTER_LOGIN_MESSAGE;

    public static final String ERROR_REGISTER_PASSWORD;
    public static final String ERROR_REGISTER_PASSWORD_MESSAGE;

    static {
        ERROR_MESSAGE = "errorMessage";
        ERROR_MESSAGE_TEXT = "Incorrect login or password";

        ERROR_REGISTER_LOGIN = "errorRegisterLogin";
        ERROR_REGISTER_LOGIN_MESSAGE = "Such login already exists. Choose another login";

        ERROR_REGISTER_PASSWORD = "errorRegisterPassword";
        ERROR_REGISTER_PASSWORD_MESSAGE = "Passwords don't match";
    }
}
