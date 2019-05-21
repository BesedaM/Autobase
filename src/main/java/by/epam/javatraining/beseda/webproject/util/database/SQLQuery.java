package by.epam.javatraining.beseda.webproject.util.database;

public class SQLQuery {

    //Dependence DAO
    public static final int NULL = 0;
    public static final String FIRST_PARAMETER = "**";
    public static final String SECOND_PARAMETER = "//";
    public static final String UPDATE_DEPENDENCY
            = "UPDATE autobase." + FIRST_PARAMETER + " SET " + SECOND_PARAMETER + "=? WHERE id=?";
    public static final String ADD_ROUTE_CAR_DEPENDENCY = "INSERT INTO autobase." + FIRST_PARAMETER
            + " (car_id, route_id) VALUES (?,?)";
    public static final String DELETE_ROUTE_CAR_DEPENDENCY = "DELETE from autobase." + FIRST_PARAMETER + " WHERE car_id=? AND route_id=?";
    public static final String FIND_CAR_DEPENDENCY_ID
            = "SELECT " + FIRST_PARAMETER + " FROM autobase." + SECOND_PARAMETER + " WHERE route_id=?";

    public static final String FIND_DEPENDENCY_ID
            = "SELECT id FROM autobase." + FIRST_PARAMETER + " WHERE " + SECOND_PARAMETER + "=?";      //"SELECT " + FIRST_PARAMETER + " FROM autobase." + SECOND_PARAMETER + " WHERE id=?";
    public static final String FIND_ONE_TO_ONE_DEPENDENCY_ID
            = "SELECT " + FIRST_PARAMETER + " FROM autobase." + SECOND_PARAMETER + " WHERE id=?";


    //enum
    public static final String SELECT_ENUM;

    //User DAO
    public static final String USER_ID;
    public static final String SELECT_ALL_USERS;
    public static final String SELECT_USER_BY_ID;
    public static final String SELECT_USER_BY_LOGIN_AND_PASSWORD;
    public static final String SELECT_USER_BY_LOGIN;
    public static final String DELETE_USER_BY_ID;
    public static final String ADD_NEW_USER;
    public static final String UPDATE_USER;
    public static final String UPDATE_USER_PASSWORD;

    //Car DAO
    public static final String CAR_ID;
    public static final String SELECT_ALL_CARS;
    public static final String SELECT_CAR_BY_ID;
    public static final String ADD_NEW_BUS;
    public static final String ADD_NEW_TRUCK;
    public static final String DELETE_CAR_BY_ID;
    public static final String UPDATE_BUS;
    public static final String UPDATE_TRUCK;

    //Address DAO
    public static final String ADDRESS_ID;
    public static final String SELECT_ALL_ADDRESSES;
    public static final String SELECT_ADDRESS_BY_ID;
    public static final String DELETE_ADDRESS_BY_ID;
    public static final String INSERT_ADDRESS;
    public static final String UPDATE_ADDRESS;

    //Task DAO
    public static final String TASK_ID;
    public static final String SELECT_ALL_TASKS;
    public static final String SELECT_TASK_BY_ID;
    public static final String DELETE_TASK_BY_ID;
    public static final String INSERT_TASK;
    public static final String UPDATE_TASK;

    //Route DAO
    public static final String ROUTE_ID;
    public static final String SELECT_ALL_ROUTES;
    public static final String SELECT_ROUTE_BY_ID;
    public static final String DELETE_ROUTE_BY_ID;
    public static final String INSERT_ROUTE;
    public static final String UPDATE_ROUTE;

    //Customer DAO
    public static final String CUSTOMER_ID;
    public static final String SELECT_ALL_CUSTOMERS;
    public static final String SELECT_CUSTOMER_BY_ID;
    public static final String DELETE_CUSTOMER_BY_ID;
    public static final String ADD_NEW_CUSTOMER;
    public static final String UPDATE_CUSTOMER;

    //Driver DAO
    public static final String DRIVER_ID;
    public static final String SELECT_ALL_DRIVERS;
    public static final String SELECT_DRIVER_BY_ID;
    public static final String DELETE_DRIVER_BY_ID;
    public static final String ADD_NEW_DRIVER;
    public static final String UPDATE_DRIVER;

    //Request DAO
    public static final String REQUEST_ID;
    public static final String SELECT_ALL_REQUESTS;
    public static final String SELECT_REQUEST_BY_ID;
    public static final String DELETE_REQUEST_BY_ID;
    public static final String ADD_NEW_REQUEST;
    public static final String UPDATE_REQUEST;

    public static final String END_OF_STATEMENT = ";";


    static {

        //enum
        SELECT_ENUM = "SELECT * FROM autobase.";

        //User DAO
        USER_ID = "users.id";
        SELECT_ALL_USERS = "SELECT u.id, u.login, u.password, r.role_name \n" +
                "FROM autobase.users u \n" +
                "LEFT JOIN autobase.role r ON u.role_id=r.id";
        SELECT_USER_BY_ID = SELECT_ALL_USERS + " WHERE u.id=?";
        SELECT_USER_BY_LOGIN_AND_PASSWORD = SELECT_ALL_USERS + " WHERE login=? AND password=?";
        SELECT_USER_BY_LOGIN = SELECT_ALL_USERS + " WHERE login=?";
        DELETE_USER_BY_ID = "DELETE FROM autobase.users WHERE id=?";
        ADD_NEW_USER = "INSERT INTO autobase.users (login, password, role_id) VALUES (?, ?, ?)";
        UPDATE_USER_PASSWORD = "UPDATE autobase.users SET password=? WHERE users.login=?";
        UPDATE_USER = "UPDATE autobase.users SET login=?, password=?, role_id=? WHERE users.id=?";

        //Car DAO
        CAR_ID = "cars.id";
        SELECT_ALL_CARS = "SELECT * FROM autobase.cars c \n" +
                "LEFT JOIN autobase.car_type ct on c.type_id=ct.id\n" +
                "LEFT JOIN autobase.truck_capacity t on c.capacity_id=t.id\n" +
                "LEFT JOIN autobase.car_status cs1 on c.status_id=cs1.id\n" +
                "LEFT JOIN autobase.car_state cs2 on c.state_id=cs2.id";
        SELECT_CAR_BY_ID = SELECT_ALL_CARS + " WHERE cars.id=?";
        ADD_NEW_BUS = "INSERT INTO autobase.cars  (type_id, seatsNumber, car_number, model, status_id, state_id) VALUES (?, ?, ?, ?, ?, ?)";
        ADD_NEW_TRUCK = "INSERT INTO autobase.cars  (type_id, capacity_id, car_number, model, status_id, state_id) VALUES (?, ?, ?, ?, ?, ?)";
        DELETE_CAR_BY_ID = "DELETE FROM autobase.cars WHERE id=?";
        UPDATE_BUS = "UPDATE autobase.cars SET type_id=?, seatsNumber=?, car_number=?, model=?, status_id=?, state_id=? WHERE cars.id=?";
        UPDATE_TRUCK = "UPDATE autobase.cars SET type_id=?, capacity_id=?, car_number=?, model=?, status_id=?, state_id=? WHERE cars.id=?";

        //Address DAO
        ADDRESS_ID = "addresses.id";
        SELECT_ALL_ADDRESSES = "SELECT * FROM autobase.addresses";
        SELECT_ADDRESS_BY_ID = SELECT_ALL_ADDRESSES + " WHERE addresses.id=?";
        DELETE_ADDRESS_BY_ID = "DELETE FROM autobase.addresses WHERE id=?";
        INSERT_ADDRESS = "INSERT INTO autobase.addresses (country, district, city, street, houseNum, building) VALUES (?,?,?,?,?,?)";
        UPDATE_ADDRESS = "UPDATE autobase.cars SET country=?, district=?, city=?, street=?, houseNum=?, building=? WHERE addresses.id=?";

        //Task DAO
        TASK_ID = "tasks.id";
        SELECT_ALL_TASKS = "SELECT * FROM autobase.tasks";
        SELECT_TASK_BY_ID = SELECT_ALL_TASKS + " WHERE tasks.id=?";
        DELETE_TASK_BY_ID = "DELETE FROM autobase.tasks WHERE id=?";
        INSERT_TASK = "INSERT INTO autobase.tasks (time, datail) VALUES (?, ?)";
        UPDATE_TASK = "UPDATE autobase.tasks SET time=?, detail=? WHERE tasks.id=?";


        //Route DAO
        ROUTE_ID = "routes.id";
        SELECT_ALL_ROUTES = "SELECT * FROM autobase.routes\n" +
                "LEFT JOIN autobase.route_status on routes.status_id=route_status.id";
        SELECT_ROUTE_BY_ID = SELECT_ALL_ROUTES + " WHERE routes.id=?";
        DELETE_ROUTE_BY_ID = "DELETE FROM autobase.routes WHERE id=?";
        INSERT_ROUTE = "INSERT INTO autobase.routes (name, status_id) VALUES (?, ?)";
        UPDATE_ROUTE = "UPDATE autobase.routes SET name=?, status_id=? WHERE routes.id=?";

        //Customer DAO
        CUSTOMER_ID = "customers.id";
        SELECT_ALL_CUSTOMERS = "SELECT * FROM autobase.users \n" +
                "RIGHT JOIN autobase.customers ON users.id=customers.id \n" +
                "LEFT JOIN autobase.customer_type ON customers.type_id=customer_type.id";
        SELECT_CUSTOMER_BY_ID = SELECT_ALL_CUSTOMERS + " WHERE users.id=?";
        DELETE_CUSTOMER_BY_ID = "DELETE FROM autobase.customers WHERE id=?";
        ADD_NEW_CUSTOMER = "INSERT INTO autobase.customers (type_id, name, surname, phone, email ,company_name, id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        UPDATE_CUSTOMER = "UPDATE autobase.customers SET type_id=?, name=?, surname=?, phone=?, email=? ,company_name=? WHERE customers.id=?";

        //Driver DAO
        DRIVER_ID = "drivers.id";
        SELECT_ALL_DRIVERS = "SELECT * FROM autobase.drivers";
        SELECT_DRIVER_BY_ID = SELECT_ALL_DRIVERS + " WHERE drivers.id=?";
        DELETE_DRIVER_BY_ID = "DELETE FROM autobase.drivers WHERE id=?";
        ADD_NEW_DRIVER = "INSERT INTO autobase.drivers (surname, name, phone, id) VALUES (?, ?, ?, ?)";
        UPDATE_DRIVER = "UPDATE autobase.drivers SET surname=?, name=?, phone=? WHERE drivers.id=?";

        //Request DAO
        REQUEST_ID = "requests.id";
        SELECT_ALL_REQUESTS = "SELECT * FROM autobase.requests LEFT JOIN autobase.request_status ON requests.status_id=request_status.id";
        SELECT_REQUEST_BY_ID = SELECT_ALL_REQUESTS + " WHERE requests.id=?";
        DELETE_REQUEST_BY_ID = "DELETE FROM autobase.requests WHERE id=?";
        ADD_NEW_REQUEST = "INSERT INTO autobase.requests (status_id, comment) VALUES (?,?)";
        UPDATE_REQUEST = "UPDATE autobase.requests SET status_id=?, comment=? WHERE id=?";
    }
}
