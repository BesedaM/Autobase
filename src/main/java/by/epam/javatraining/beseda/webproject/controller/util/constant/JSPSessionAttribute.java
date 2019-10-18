package by.epam.javatraining.beseda.webproject.controller.util.constant;

/**
 * Class of constants for all the HttpSession attributes used in program
 */
public class JSPSessionAttribute {

	public static final String EMPTY_VALUE;
	public static final String STATUS_TRUE;
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
	public static final String REQUEST_CUSTOMER_MAP;
	public static final String CAR_BUSY_DATES;
	public static final String DRIVER_MAP;
	public static final String REQUEST_STATUS_LIST;
	public static final String TASK_LIST;

	public static final String ROUTE_STATUS;
	public static final String CAR_STATE;
	public static final String CURRENT_ROUTE;
	public static final String CHANGING_ROUTE;
	public static final String CHANGE_CAR;
	public static final String TASK_TO_CHANGE;
	public static final String ADD_TASK_FLAG;

	static {
		EMPTY_VALUE = "";
		STATUS_TRUE = "true";
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
		REQUEST_CUSTOMER_MAP = "request_customer_map";
		CAR_BUSY_DATES = "car_busy_dates";
		DRIVER_MAP = "driver_map";
		REQUEST_STATUS_LIST = "request_status_list";
		TASK_LIST = "task_list";

		ROUTE_STATUS = "route_status";
		CAR_STATE = "car_state";
		CURRENT_ROUTE = "current_route";
		CHANGING_ROUTE = "changing_route";
		CHANGE_CAR = "change_car";
		TASK_TO_CHANGE = "task_to_change";
		ADD_TASK_FLAG = "add_task_flag";
	}
}