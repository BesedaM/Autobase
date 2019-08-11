package by.epam.javatraining.beseda.webproject.model.dao.util.database;

/**
 * In this class tables and column names of tables in the corresponding database represented
 */
public class DBEntityTable {

    public static final String T_USERS = "users";
    public static final String ID = "id";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String ROLE_ID_USERS = "role_id";

    public static final String T_DRIVERS = "drivers";
    public static final String T_CUSTOMERS = "customers";
    public static final String SURNAME = "surname";
    public static final String NAME = "name";
    public static final String PHONE = "phone";
    public static final String CUSTOMER_TYPE_ID_CUSTOMERS = "type_id";
    public static final String EMAIL = "email";
    public static final String COMPANY_NAME = "company_name";

    public static final String T_CARS = "cars";
    public static final String CAR_NUMBER = "car_number";
    public static final String CAR_TYPE_ID_CARS = "type_id";
    public static final String MODEL = "model";
    public static final String SEATS_NUMBER = "seatsNumber";
    public static final String TRUCK_CAPACITY_ID_CARS = "capacity_id";
    public static final String CAR_STATUS_ID_CARS = "status_id";
    public static final String CAR_STATE_ID_CARS = "state_id";
    public static final String DRIVER_ID = "driver_id";

    public static final String T_REQUESTS = "requests";
    public static final String CUSTOMER_ID_REQUESTS = "customer_id";
    public static final String ROUTE_ID_REQUESTS = "route_id";
    public static final String REQUEST_STATUS_ID_REQUESTS = "status_id";
    public static final String COMMENT = "comment";
    public static final String REQUEST_DATE = "request_date";

    public static final String T_ROUTES = "routes";
    public static final String ROUTE_NAME = "name";
    public static final String STATUS_ID_ROUTES = "status_id";

    public static final String T_TASKS = "tasks";
    public static final String ADDRESS_ID_TASKS = "address_id";
    public static final String ROUTE_ID_TASKS = "route_id";
    public static final String TIME = "time";
    public static final String DETAILS = "details";

    public static final String T_ADDRESSES = "addresses";
    public static final String COUNTRY = "country";
    public static final String DISTRICT = "district";
    public static final String CITY = "city";
    public static final String STREET = "street";
    public static final String HOUSE_NUMBER = "houseNum";
    public static final String BUILDING = "building";

    public static final String T_CARS_IN_ROUTES = "cars_in_routes";
    public static final String CAR_ID_ROUTES = "car_id";
    public static final String ROUTE_ID_CARS = "route_id";

}
