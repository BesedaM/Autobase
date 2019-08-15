package by.epam.javatraining.beseda.webproject.model.dao.util.database;

public class SQLQuery {

    public static final int NULL = 0;
    public static final String SEMICOLON = ";";

    //Dependence DAO
    //CarDriver
    public static final String CAR_DRIVER_GET_DEPENDENCE_ID;
    public static final String CAR_DRIVER_GET_ENTITIES_ID;
    public static final String CAR_DRIVER_UPDATE_DEPENDENCE;

    //TaskAddress
    public static final String TASK_ADDRESS_GET_DEPENDENCE_ID;
    public static final String TASK_ADDRESS_GET_ENTITIES;
    public static final String TASK_ADDRESS_UPDATE_DEPENDENCE;

    //RequestCustomer
    public static final String REQUEST_CUSTOMER_GET_DEPENDENCE_ID;
    public static final String REQUEST_CUSTOMER_GET_ENTITIES;
    public static final String REQUEST_CUSTOMER_UPDATE_DEPENDENCE;

    //TaskRoute
    public static final String TASK_ROUTE_GET_DEPENDENCE_ID;
    public static final String TASK_ROUTE_GET_ENTITIES;
    public static final String TASK_ROUTE_UPDATE_DEPENDENCE;

    //CarRoute
    public static final String CAR_ROUTE_GET_DEPENDENCE_ROUTE_ID;
    public static final String CAR_ROUTE_GET_DEPENDENCE_CAR_ID;
    public static final String CAR_ROUTE_GET_DEPENDENCE_ACTIVE_ROUTE_ID;
    public static final String CAR_ROUTE_GET_DEPENDENCE_PLANNED_ROUTE_ID;
    public static final String CAR_ROUTE_GET_DEPENDENCE_ACTIVE_PLANNED_ROUTE_ID;
    public static final String CAR_ROUTE_UPDATE_DEPENDENCE;
    public static final String CAR_ROUTE_DELETE_DEPENDENCE;


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
    public static final String UPDATE_CAR_STATE;

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
    public static final String UPDATE_ROUTE_STATUS;

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
    public static final String SELECT_ACTIVE_CUSTOMER_REQUESTS_ID;
    public static final String SELECT_REQUEST_BY_ID;
    public static final String DELETE_REQUEST_BY_ID;
    public static final String ADD_NEW_REQUEST;
    public static final String UPDATE_REQUEST;
    public static final String SELECT_NEW_REQUESTS;

    public static final String END_OF_STATEMENT = ";";


    static {

        //enum
        SELECT_ENUM = "SELECT * FROM autobase.";

        //CarDriver DependenceDAO
        CAR_DRIVER_GET_DEPENDENCE_ID = "SELECT driver_id FROM autobase.cars WHERE id=?";
        CAR_DRIVER_GET_ENTITIES_ID = "SELECT id FROM autobase.cars WHERE driver_id=?";
        CAR_DRIVER_UPDATE_DEPENDENCE = "UPDATE autobase.cars SET driver_id=? WHERE id=?";

        //TaskAddress DependenceDAO
        TASK_ADDRESS_GET_DEPENDENCE_ID = "SELECT address_id FROM autobase.tasks WHERE id=?";
        TASK_ADDRESS_GET_ENTITIES = "SELECT id FROM autobase.tasks WHERE address_id=?";
        TASK_ADDRESS_UPDATE_DEPENDENCE = "UPDATE autobase.tasks SET address_id=? WHERE id=?";

        //RequestCustomer DependenceDAO
        REQUEST_CUSTOMER_GET_DEPENDENCE_ID = "SELECT customer_id FROM autobase.requests WHERE id=?";
        REQUEST_CUSTOMER_GET_ENTITIES = "SELECT id FROM autobase.requests WHERE customer_id=?";
        REQUEST_CUSTOMER_UPDATE_DEPENDENCE = "UPDATE autobase.requests SET customer_id=? WHERE id=?";

        //TaskRoute DependenceDAO
        TASK_ROUTE_GET_DEPENDENCE_ID = "SELECT route_id FROM autobase.tasks WHERE id=?";
        TASK_ROUTE_GET_ENTITIES = "SELECT id FROM autobase.tasks WHERE route_id=?";
        TASK_ROUTE_UPDATE_DEPENDENCE = "UPDATE autobase.tasks SET route_id=? WHERE id=?";

        //CarRoute DependenceDAO
        CAR_ROUTE_GET_DEPENDENCE_ROUTE_ID = "SELECT route_id FROM autobase.cars_in_routes WHERE car_id=?";
        CAR_ROUTE_GET_DEPENDENCE_ACTIVE_ROUTE_ID = "SELECT route_id FROM autobase.cars_in_routes \n" +
                "LEFT JOIN autobase.routes ON cars_in_routes.route_id=routes.id\n" +
                "LEFT JOIN autobase.route_status ON routes.status_id=route_status.id\n" +
                "WHERE car_id=? AND route_status.status='на выполнении';";
        CAR_ROUTE_GET_DEPENDENCE_PLANNED_ROUTE_ID = "SELECT route_id FROM autobase.cars_in_routes \n" +
                "LEFT JOIN autobase.routes ON cars_in_routes.route_id=routes.id\n" +
                "LEFT JOIN autobase.route_status ON routes.status_id=route_status.id\n" +
                "WHERE car_id=? AND route_status.status='запланирован';";
        CAR_ROUTE_GET_DEPENDENCE_ACTIVE_PLANNED_ROUTE_ID = "SELECT route_id FROM autobase.cars_in_routes \n" +
                "LEFT JOIN autobase.routes ON cars_in_routes.route_id=routes.id\n" +
                "LEFT JOIN autobase.route_status ON routes.status_id=route_status.id\n" +
                "WHERE car_id=? AND route_status.status IN ('запланирован','на выполнении');";


        CAR_ROUTE_GET_DEPENDENCE_CAR_ID = "SELECT car_id FROM autobase.cars_in_routes WHERE route_id=?";
        CAR_ROUTE_UPDATE_DEPENDENCE = "INSERT INTO autobase.cars_in_routes (car_id, route_id) VALUES (?,?)";
        CAR_ROUTE_DELETE_DEPENDENCE = "DELETE FROM autobase.cars_in_routes WHERE car_id=? AND route_id=?";


        //User DAO
        USER_ID = "users.id";
        SELECT_ALL_USERS = "SELECT users.id, users.login, users.password, role.role_name \n" +
                "FROM autobase.users \n" +
                "LEFT JOIN autobase.role ON role.id=users.role_id";
        SELECT_USER_BY_ID = SELECT_ALL_USERS + " WHERE users.id=?";
        SELECT_USER_BY_LOGIN_AND_PASSWORD = SELECT_ALL_USERS + " WHERE login=? AND password=?";
        SELECT_USER_BY_LOGIN = SELECT_ALL_USERS + " WHERE login=?";
        DELETE_USER_BY_ID = "DELETE FROM autobase.users WHERE id=?";
        ADD_NEW_USER = "INSERT INTO autobase.users (login, password, role_id) VALUES (?, ?, ?)";
        UPDATE_USER_PASSWORD = "UPDATE autobase.users SET password=? WHERE users.id=?";
        UPDATE_USER = "UPDATE autobase.users SET login=?, password=?, role_id=? WHERE id=?";

        //Car DAO
        CAR_ID = "cars.id";
        SELECT_ALL_CARS = "SELECT * FROM autobase.cars \n" +
                "LEFT JOIN autobase.car_type on cars.type_id=car_type.id\n" +
                "LEFT JOIN autobase.truck_capacity on cars.capacity_id=truck_capacity.id\n" +
                "LEFT JOIN autobase.car_status on cars.status_id=car_status.id\n" +
                "LEFT JOIN autobase.car_state on cars.state_id=car_state.id";
        SELECT_CAR_BY_ID = SELECT_ALL_CARS + " WHERE cars.id=?";
        ADD_NEW_BUS = "INSERT INTO autobase.cars  (type_id, seatsNumber, car_number, model, status_id, state_id) VALUES (?, ?, ?, ?, ?, ?)";
        ADD_NEW_TRUCK = "INSERT INTO autobase.cars  (type_id, capacity_id, car_number, model, status_id, state_id) VALUES (?, ?, ?, ?, ?, ?)";
        DELETE_CAR_BY_ID = "DELETE FROM autobase.cars WHERE id=?";
        UPDATE_BUS = "UPDATE autobase.cars SET seatsNumber=?, car_number=?, model=?, status_id=?, state_id=? WHERE cars.id=?";
        UPDATE_TRUCK = "UPDATE autobase.cars SET capacity_id=?, car_number=?, model=?, status_id=?, state_id=? WHERE cars.id=?";
        UPDATE_CAR_STATE = "UPDATE autobase.cars SET state_id=? WHERE cars.id=?";

        //Address DAO
        ADDRESS_ID = "addresses.id";
        SELECT_ALL_ADDRESSES = "SELECT * FROM autobase.addresses";
        SELECT_ADDRESS_BY_ID = SELECT_ALL_ADDRESSES + " WHERE id=?";
        DELETE_ADDRESS_BY_ID = "DELETE FROM autobase.addresses WHERE id=?";
        INSERT_ADDRESS = "INSERT INTO autobase.addresses (country, district, city, street, houseNum, building) VALUES (?,?,?,?,?,?)";
        UPDATE_ADDRESS = "UPDATE autobase.cars SET country=?, district=?, city=?, street=?, houseNum=?, building=? WHERE id=?";

        //Task DAO
        TASK_ID = "tasks.id";
        SELECT_ALL_TASKS = "SELECT * FROM autobase.tasks";
        SELECT_TASK_BY_ID = SELECT_ALL_TASKS + " WHERE id=?";
        DELETE_TASK_BY_ID = "DELETE FROM autobase.tasks WHERE id=?";
        INSERT_TASK = "INSERT INTO autobase.tasks (time, datails) VALUES (?, ?)";
        UPDATE_TASK = "UPDATE autobase.tasks SET time=?, details=? WHERE tasks.id=?";


        //Route DAO
        ROUTE_ID = "routes.id";
        SELECT_ALL_ROUTES = "SELECT * FROM autobase.routes\n" +
                "LEFT JOIN autobase.route_status on routes.status_id=route_status.id";
        SELECT_ROUTE_BY_ID = SELECT_ALL_ROUTES + " WHERE routes.id=?";
        DELETE_ROUTE_BY_ID = "DELETE FROM autobase.routes WHERE id=?";
        INSERT_ROUTE = "INSERT INTO autobase.routes (name, status_id) VALUES (?, ?)";
        UPDATE_ROUTE = "UPDATE autobase.routes SET name=?, status_id=? WHERE routes.id=?";
        UPDATE_ROUTE_STATUS = "UPDATE autobase.routes SET status_id=? WHERE routes.id=?";

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
        SELECT_ALL_DRIVERS = "SELECT * FROM autobase.drivers LEFT JOIN autobase.users ON drivers.id=users.id";
        SELECT_DRIVER_BY_ID = SELECT_ALL_DRIVERS + " WHERE drivers.id=?";
        DELETE_DRIVER_BY_ID = "DELETE FROM autobase.drivers WHERE id=?";
        ADD_NEW_DRIVER = "INSERT INTO autobase.drivers (surname, name, phone, id) VALUES (?, ?, ?, ?)";
        UPDATE_DRIVER = "UPDATE autobase.drivers SET surname=?, name=?, phone=? WHERE drivers.id=?";

        //Request DAO
        REQUEST_ID = "requests.id";
        SELECT_ALL_REQUESTS = "SELECT * FROM autobase.requests LEFT JOIN autobase.request_status ON requests.status_id=request_status.id";
        SELECT_ACTIVE_CUSTOMER_REQUESTS_ID = "SELECT a.id FROM \n" +
                "(SELECT requests.customer_id, requests.id FROM autobase.requests WHERE requests.status_id IN(1,3) " +
                "UNION SELECT requests.customer_id, requests.id \n" +
                "FROM autobase.requests JOIN autobase.routes ON requests.id=routes.id \n" +
                "WHERE routes.status_id IN(1,2,3) ) AS a WHERE a.customer_id=?";
        SELECT_NEW_REQUESTS="SELECT * FROM autobase.requests LEFT JOIN autobase.routes ON requests.id=routes.id  " +
                "LEFT JOIN autobase.request_status ON requests.status_id=request_status.id\n" +
                "WHERE request_status.status='рассматривается' OR (request_status.status='принята' AND isnull(routes.id));";

        SELECT_REQUEST_BY_ID = SELECT_ALL_REQUESTS + " WHERE requests.id=?";
        DELETE_REQUEST_BY_ID = "DELETE FROM autobase.requests WHERE id=?";
        ADD_NEW_REQUEST = "INSERT INTO autobase.requests (status_id, comment, customer_id) VALUES (?,?,?)";
        UPDATE_REQUEST = "UPDATE autobase.requests SET status_id=?, comment=? WHERE id=?";

    }
}
