package by.epam.javatraining.beseda.webproject.controller.util.constant;

/**
 * Class of constants for all the request attributes used in program.
 */
public class JSPAttribute {

    public static final String ID;
    public static final String CUSTOMER;
    public static final String STATUS_TRUE;

    public static final String ERROR_MESSAGE;

    public static final String ERROR_REGISTER_LOGIN;

    public static final String ERROR_REGISTER_PASSWORD;

    public static final String UNSAFE_PASSWORD;

    public static final String PASSWORD_CHANGED;

    public static final String DATA_CHANGED;

    public static final String TEXT;

    static {
        ID = "id";
        CUSTOMER = "customer";
        STATUS_TRUE = "true";

        ERROR_MESSAGE = "errorMessage";
        ERROR_REGISTER_LOGIN = "errorRegisterLogin";
        ERROR_REGISTER_PASSWORD = "errorRegisterPassword";
        UNSAFE_PASSWORD = "unsafePassword";
        PASSWORD_CHANGED = "passwordChanged";
        DATA_CHANGED = "dataChanged";
        TEXT = "text";
    }
}