package by.epam.javatraining.beseda.webproject.model.command.util.jsp;

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
    public static final String CAR_STATE_CHANGER;


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

        CAR_INFO = "car_info";
        ROUTE_STATUS_CHANGER = "route_status_changer";
        ROUTE_ID = "route_id";
        CAR_ID = "car_id";
        CAR_STATE_CHANGER = "car_state_changer";
    }
}
