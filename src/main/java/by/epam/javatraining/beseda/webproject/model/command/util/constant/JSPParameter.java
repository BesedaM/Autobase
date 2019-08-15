package by.epam.javatraining.beseda.webproject.model.command.util.constant;

/**
 * Class of constants for all the request parameters used in program
 */
public class JSPParameter {

    public static final String LANGUAGE_SELECT;
    public static final String CURRENT_PAGE;

    public static final String COMMAND;
    public static final String LOGIN;
    public static final String PASSWORD;
    public static final String PASSWORD_CONFIRM;
    public static final String COMPANY_NAME;

    public static final String PROFILE;
    public static final String NAME;
    public static final String SURNAME;
    public static final String PHONE;
    public static final String EMAIL;


    //driver
    public static final String CAR_INFO;
    public static final String ROUTE_STATUS_CHANGER;
    public static final String ROUTE_ID;
    public static final String CAR_ID;
    public static final String CUSTOMER_ID;
    public static final String CAR_STATE_CHANGER;


    public static final String NEW_REQUEST_TEXT;
    public static final String NEW_REQUEST;
    public static final String NEW_PASSWORD;

    public static final String REQUEST_TEXT;

    public static final String ID;

    public static final String STATUS;


    static {
        LANGUAGE_SELECT = "language_select";
        CURRENT_PAGE = "current_page";

        COMMAND = "command";
        LOGIN = "login";
        PASSWORD = "password";
        PASSWORD_CONFIRM = "password_confirm";
        COMPANY_NAME = "company_name";

        PROFILE = "profile";
        NAME = "name";
        SURNAME = "surname";
        PHONE = "phone";
        EMAIL = "email";

        ID = "id";

        CAR_INFO = "car_info";
        ROUTE_STATUS_CHANGER = "route_status_changer";
        ROUTE_ID = "route_id";
        CAR_ID = "car_id";
        CUSTOMER_ID = "customer_id";
        CAR_STATE_CHANGER = "car_state_changer";


        NEW_REQUEST_TEXT = "new_request_text";
        NEW_REQUEST = "new_request";
        NEW_PASSWORD = "new_password";

        REQUEST_TEXT = "request_text";

        STATUS = "status";
    }
}
