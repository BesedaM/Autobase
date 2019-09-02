package by.epam.javatraining.beseda.webproject.service;

public class ServiceConstants {

    //password check
    public static String PASSWORD_VALIDATE;

    public static String USER_ROLE;
    public static String CUSTOMER_TYPE;
    public static String TRUCK_CAPACITY;
    public static String CAR_STATUS;
    public static String CAR_STATE;
    public static String CAR_TYPE;
    public static String ROUTE_STATUS;
    public static String REQUEST_STATUS;


    static {

        PASSWORD_VALIDATE="((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=\\S+$).{6,20})";

        USER_ROLE = "user_role";
        CUSTOMER_TYPE = "customer_type";
        TRUCK_CAPACITY = "truck_capacity";
        CAR_STATUS = "car_status";
        CAR_STATE = "car_state";
        CAR_TYPE = "car_type";
        ROUTE_STATUS = "route_status";
        REQUEST_STATUS = "request_status";

    }
}
