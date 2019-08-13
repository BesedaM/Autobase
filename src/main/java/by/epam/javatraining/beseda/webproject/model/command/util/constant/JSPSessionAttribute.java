package by.epam.javatraining.beseda.webproject.model.command.util.constant;

/**
 * Class of constants for all the HttpSession attributes used in program
 */
public class JSPSessionAttribute {

    public static final String EMPTY_VALUE;
    public static final String LANGUAGE;
    public static final String LANGUAGE_EN;
    public static final String LANGUAGE_RU;

    public static final String LOCALE_FILE;
    public static final String LOCALE_EN;
    public static final String LOCALE_RU;

    public static final String USER_DATA;
    public static final String USER_ROLE;

    public static final String CAR;
    public static final String CAR_TYPE;
    public static final String CAR_BUS;
    public static final String CAR_TRUCK;
    public static final String ROUTE;
    public static final String ACTIVE_ROUTE;
    public static final String ROUTE_LIST;
    public static final String REQUEST_LIST;
    public static final String DRIVER_MAP;
    public static final String REQUEST_STATUS_LIST;

    public static final String ROUTE_STATUS;
    public static final String CAR_STATE;

    static {
        EMPTY_VALUE = "";
        LANGUAGE = "language";
        LANGUAGE_EN = "en";
        LANGUAGE_RU = "ru";

        LOCALE_FILE = "locale_file";
        LOCALE_EN = "locale_en";
        LOCALE_RU = "locale_ru";

        USER_DATA = "user";
        USER_ROLE = "role";

        CAR = "car";
        CAR_TYPE = "car_type";
        CAR_BUS = "bus";
        CAR_TRUCK = "truck";
        ROUTE = "route";
        ACTIVE_ROUTE = "active_route";
        ROUTE_LIST = "route_list";
        REQUEST_LIST = "request_list";
        DRIVER_MAP="driver_map";
        REQUEST_STATUS_LIST="request_status_list";

        ROUTE_STATUS = "route_status";
        CAR_STATE = "car_state";
    }
}
