package by.epam.javatraining.beseda.webproject.dao.util;

public class SQLQuery {

	public static final int NULL = 0;
	public static final String SEMICOLON = ";";

	// Dependence DAO
	// CarDriver
	public static final String CAR_DRIVER_GET_DRIVER_ID;
	public static final String CAR_DRIVER_GET_CAR_ID;
	public static final String CAR_DRIVER_UPDATE_DEPENDENCE;

	// TaskAddress
	public static final String TASK_ADDRESS_GET_ADDRESS_ID;
	public static final String TASK_ADDRESS_GET_TASKS_ID;
	public static final String TASK_ADDRESS_UPDATE_DEPENDENCE;

	// RequestCustomer
	public static final String REQUEST_CUSTOMER_GET_CUSTOMER_ID;
	public static final String REQUEST_CUSTOMER_GET_REQUESTS_ID;
	public static final String REQUEST_CUSTOMER_UPDATE_DEPENDENCE;

	// TaskRoute
	public static final String TASK_ROUTE_GET_ROUTE_ID;
	public static final String TASK_ROUTE_GET_TASKS_ID;
	public static final String TASK_ROUTE_UPDATE_DEPENDENCE;

	// CarRoute
	public static final String CAR_ROUTE_GET_ROUTES_ID;
	public static final String CAR_ROUTE_GET_CAR_ID;
	public static final String CAR_ROUTE_GET_ACTIVE_ROUTE_ID;
	public static final String CAR_ROUTE_GET_PLANNED_ROUTE_ID;
	public static final String CAR_ROUTE_GET_ACTIVE_PLANNED_ROUTE_ID;
	public static final String CAR_ROUTE_UPDATE_DEPENDENCE;
	public static final String CAR_ROUTE_DELETE_DEPENDENCE;

	// enum
	public static final String SELECT_ENUM;

	// User DAO
	public static final String USER_ID;
	public static final String SELECT_ALL_USERS;
	public static final String SELECT_USERS_BY_ID_LIST;
	public static final String SELECT_USER_BY_ID;
	public static final String SELECT_USER_BY_LOGIN_AND_PASSWORD;
	public static final String SELECT_USER_BY_LOGIN;
	public static final String DELETE_USER_BY_ID;
	public static final String ADD_NEW_USER;
	public static final String UPDATE_USER;
	public static final String UPDATE_USER_PASSWORD;

	// Car DAO
	public static final String CAR_ID;
	public static final String SELECT_ALL_CARS;
	public static final String SELECT_CARS_BY_TYPE;
	public static final String SELECT_CARS_BY_ID_LIST;
	public static final String SELECT_CAR_BY_ID;
	public static final String ADD_NEW_BUS;
	public static final String ADD_NEW_TRUCK;
	public static final String DELETE_CAR_BY_ID;
	public static final String UPDATE_BUS;
	public static final String UPDATE_TRUCK;
	public static final String UPDATE_CAR_STATE;

	// Address DAO
	public static final String ADDRESS_ID;
	public static final String SELECT_ALL_ADDRESSES;
	public static final String SELECT_ADDRESSES_BY_ID_LIST;
	public static final String SELECT_ADDRESS_BY_ID;
	public static final String DELETE_ADDRESS_BY_ID;
	public static final String INSERT_ADDRESS;
	public static final String UPDATE_ADDRESS;

	// Task DAO
	public static final String TASK_ID;
	public static final String SELECT_ALL_TASKS;
	public static final String SELECT_TASKS_BY_ID_LIST;
	public static final String SELECT_TASK_BY_ID;
	public static final String DELETE_TASK_BY_ID;
	public static final String INSERT_TASK;
	public static final String UPDATE_TASK;

	// Route DAO
	public static final String ROUTE_ID;
	public static final String SELECT_ALL_ROUTES;
	public static final String SELECT_ROUTES_BY_ID_LIST;
	public static final String SELECT_ROUTE_BY_ID;
	public static final String DELETE_ROUTE_BY_ID;
	public static final String INSERT_ROUTE;
	public static final String UPDATE_ROUTE;
	public static final String UPDATE_ROUTE_STATUS;

	// Customer DAO
	public static final String CUSTOMER_ID;
	public static final String SELECT_ALL_CUSTOMERS;
	public static final String SELECT_CUSTOMERS_BY_ID_LIST;
	public static final String SELECT_CUSTOMER_BY_ID;
	public static final String DELETE_CUSTOMER_BY_ID;
	public static final String ADD_NEW_CUSTOMER;
	public static final String UPDATE_CUSTOMER;

	// Driver DAO
	public static final String DRIVER_ID;
	public static final String SELECT_ALL_DRIVERS;
	public static final String SELECT_DRIVERS_BY_ID_LIST;
	public static final String SELECT_DRIVER_BY_ID;
	public static final String DELETE_DRIVER_BY_ID;
	public static final String ADD_NEW_DRIVER;
	public static final String UPDATE_DRIVER;

	// Request DAO
	public static final String REQUEST_ID;
	public static final String SELECT_ALL_REQUESTS;
	public static final String SELECT_REQUESTS_BY_ID_LIST;
	public static final String SELECT_ACTIVE_CUSTOMER_REQUESTS;
	public static final String SELECT_REQUEST_BY_ID;
	public static final String DELETE_REQUEST_BY_ID;
	public static final String ADD_NEW_REQUEST;
	public static final String UPDATE_REQUEST;
	public static final String SELECT_NEW_REQUESTS;
	public static final String SELECT_CURRENT_REQUESTS;
	public static final String SELECT_FULFILLED_REQUESTS;
	public static final String SELECT_REJECTED_REQUESTS;

	public static final String END_OF_STATEMENT = ";";

	static {

		// enum
		SELECT_ENUM = "SELECT * FROM trucking_company.";

		// CarDriver DependenceDAO
		CAR_DRIVER_GET_DRIVER_ID = "SELECT driver_id FROM trucking_company.cars WHERE id=?";
		CAR_DRIVER_GET_CAR_ID = "SELECT id FROM trucking_company.cars WHERE driver_id=?";
		CAR_DRIVER_UPDATE_DEPENDENCE = "UPDATE trucking_company.cars SET driver_id=? WHERE id=?";

		// TaskAddress DependenceDAO
		TASK_ADDRESS_GET_ADDRESS_ID = "SELECT address_id FROM trucking_company.tasks WHERE id=?";
		TASK_ADDRESS_GET_TASKS_ID = "SELECT id FROM trucking_company.tasks WHERE address_id=?";
		TASK_ADDRESS_UPDATE_DEPENDENCE = "UPDATE trucking_company.tasks SET address_id=? WHERE id=?";

		// RequestCustomer DependenceDAO
		REQUEST_CUSTOMER_GET_CUSTOMER_ID 
		= "SELECT customer_id FROM trucking_company.requests WHERE id=?";
		REQUEST_CUSTOMER_GET_REQUESTS_ID 
		= "SELECT id FROM trucking_company.requests WHERE customer_id=?";
		REQUEST_CUSTOMER_UPDATE_DEPENDENCE 
		= "UPDATE trucking_company.requests SET customer_id=? WHERE id=?";

		// TaskRoute DependenceDAO
		TASK_ROUTE_GET_ROUTE_ID = "SELECT route_id FROM trucking_company.tasks WHERE id=?";
		TASK_ROUTE_GET_TASKS_ID = "SELECT id FROM trucking_company.tasks WHERE route_id=?";
		TASK_ROUTE_UPDATE_DEPENDENCE = "UPDATE trucking_company.tasks SET route_id=? WHERE id=?";

		// CarRoute DependenceDAO
		CAR_ROUTE_GET_ROUTES_ID = "SELECT route_id FROM trucking_company.cars_in_routes WHERE car_id=?";
		CAR_ROUTE_GET_ACTIVE_ROUTE_ID = "SELECT route_id FROM trucking_company.cars_in_routes \n"
				+ "LEFT JOIN trucking_company.routes ON cars_in_routes.route_id=routes.id\n"
				+ "LEFT JOIN trucking_company.route_status ON routes.status_id=route_status.id\n"
				+ "WHERE car_id=? AND route_status.status='на выполнении';";
		CAR_ROUTE_GET_PLANNED_ROUTE_ID = "SELECT route_id FROM trucking_company.cars_in_routes \n"
				+ "LEFT JOIN trucking_company.routes ON cars_in_routes.route_id=routes.id\n"
				+ "LEFT JOIN trucking_company.route_status ON routes.status_id=route_status.id\n"
				+ "WHERE car_id=? AND route_status.status='запланирован';";
		CAR_ROUTE_GET_ACTIVE_PLANNED_ROUTE_ID = "SELECT route_id FROM trucking_company.cars_in_routes \n"
				+ "LEFT JOIN trucking_company.routes ON cars_in_routes.route_id=routes.id\n"
				+ "LEFT JOIN trucking_company.route_status ON routes.status_id=route_status.id\n"
				+ "WHERE car_id=? AND route_status.status IN ('запланирован','на выполнении');";

		CAR_ROUTE_GET_CAR_ID = "SELECT car_id FROM trucking_company.cars_in_routes WHERE route_id=?";
		CAR_ROUTE_UPDATE_DEPENDENCE = "INSERT INTO trucking_company.cars_in_routes (route_id, car_id) VALUES (?,?)";
		CAR_ROUTE_DELETE_DEPENDENCE = "DELETE FROM trucking_company.cars_in_routes WHERE car_id=? AND route_id=?";

		// User DAO
		USER_ID = "users.id";
		SELECT_ALL_USERS = "SELECT users.id, users.login, users.password, role.role_name \n"
				+ "FROM trucking_company.users \n" + "LEFT JOIN trucking_company.role ON role.id=users.role_id";
		SELECT_USERS_BY_ID_LIST = SELECT_ALL_USERS + " WHERE users.id IN (?)";
		SELECT_USER_BY_ID = SELECT_ALL_USERS + " WHERE users.id=?";
		SELECT_USER_BY_LOGIN_AND_PASSWORD = SELECT_ALL_USERS + " WHERE login=? AND password=?";
		SELECT_USER_BY_LOGIN = SELECT_ALL_USERS + " WHERE login=?";
		DELETE_USER_BY_ID = "DELETE FROM trucking_company.users WHERE id=?";
		ADD_NEW_USER = "INSERT INTO trucking_company.users (login, password, role_id) VALUES (:login, :password, :role_id)";
		UPDATE_USER_PASSWORD = "UPDATE trucking_company.users SET password=? WHERE users.id=?";
		UPDATE_USER = "UPDATE trucking_company.users SET login=?, password=?, role_id=? WHERE id=?";

		// Car DAO
		CAR_ID = "cars.id";
		SELECT_ALL_CARS = "SELECT * FROM trucking_company.cars \n"
				+ "LEFT JOIN trucking_company.car_type on cars.type_id=car_type.id\n"
				+ "LEFT JOIN trucking_company.truck_capacity on cars.capacity_id=truck_capacity.id\n"
				+ "LEFT JOIN trucking_company.car_status on cars.status_id=car_status.id\n"
				+ "LEFT JOIN trucking_company.car_state on cars.state_id=car_state.id";
		SELECT_CARS_BY_TYPE = SELECT_ALL_CARS + " WHERE car_type.type=?";
		SELECT_CARS_BY_ID_LIST = SELECT_ALL_CARS + " WHERE cars.id IN (?)";
		SELECT_CAR_BY_ID = SELECT_ALL_CARS + " WHERE cars.id=?";
		ADD_NEW_BUS = "INSERT INTO trucking_company.cars  (type_id, seatsNumber, car_number, model, status_id, state_id) VALUES (?, ?, ?, ?, ?, ?)";
		ADD_NEW_TRUCK = "INSERT INTO trucking_company.cars  (type_id, capacity_id, car_number, model, status_id, state_id) VALUES (?, ?, ?, ?, ?, ?)";
		DELETE_CAR_BY_ID = "DELETE FROM trucking_company.cars WHERE id=?";
		UPDATE_BUS = "UPDATE trucking_company.cars SET type_id=?, seatsNumber=?, car_number=?, model=?, status_id=?, state_id=? WHERE cars.id=?";
		UPDATE_TRUCK = "UPDATE trucking_company.cars SET type_id=?, capacity_id=?, car_number=?, model=?, status_id=?, state_id=? WHERE cars.id=?";
		UPDATE_CAR_STATE = "UPDATE trucking_company.cars SET state_id=? WHERE cars.id=?";

		// Address DAO
		ADDRESS_ID = "addresses.id";
		SELECT_ALL_ADDRESSES = "SELECT * FROM trucking_company.addresses";
		SELECT_ADDRESSES_BY_ID_LIST = SELECT_ALL_ADDRESSES + " WHERE addresses.id IN (?)";
		SELECT_ADDRESS_BY_ID = SELECT_ALL_ADDRESSES + " WHERE id=?";
		DELETE_ADDRESS_BY_ID = "DELETE FROM trucking_company.addresses WHERE id=?";
		INSERT_ADDRESS = "INSERT INTO trucking_company.addresses (country, district, city, street, houseNum, building) VALUES (?,?,?,?,?,?)";
		UPDATE_ADDRESS = "UPDATE trucking_company.cars SET country=?, district=?, city=?, street=?, houseNum=?, building=? WHERE id=?";

		// Task DAO
		TASK_ID = "tasks.id";
		SELECT_ALL_TASKS = "SELECT * FROM trucking_company.tasks";
		SELECT_TASKS_BY_ID_LIST = SELECT_ALL_TASKS + " WHERE tasks.id IN (?)";
		SELECT_TASK_BY_ID = SELECT_ALL_TASKS + " WHERE id=?";
		DELETE_TASK_BY_ID = "DELETE FROM trucking_company.tasks WHERE id=?";
		INSERT_TASK = "INSERT INTO trucking_company.tasks (time, details) VALUES (?, ?)";
		UPDATE_TASK = "UPDATE trucking_company.tasks SET time=?, details=? WHERE tasks.id=?";

		// Route DAO
		ROUTE_ID = "routes.id";
		SELECT_ALL_ROUTES = "SELECT * FROM trucking_company.routes\n"
				+ "LEFT JOIN trucking_company.route_status on routes.status_id=route_status.id";
		SELECT_ROUTES_BY_ID_LIST = SELECT_ALL_ROUTES + " WHERE routes.id IN (?)";
		SELECT_ROUTE_BY_ID = SELECT_ALL_ROUTES + " WHERE routes.id=?";
		DELETE_ROUTE_BY_ID = "DELETE FROM trucking_company.routes WHERE id=?";
		INSERT_ROUTE = "INSERT INTO trucking_company.routes (name, status_id,id) VALUES (?, ?, ?)";
		UPDATE_ROUTE = "UPDATE trucking_company.routes SET name=?, status_id=? WHERE routes.id=?";
		UPDATE_ROUTE_STATUS = "UPDATE trucking_company.routes SET status_id=? WHERE routes.id=?";

		// Customer DAO
		CUSTOMER_ID = "customers.id";
		SELECT_ALL_CUSTOMERS = "SELECT * FROM trucking_company.users \n"
				+ "RIGHT JOIN trucking_company.customers ON users.id=customers.id \n"
				+ "LEFT JOIN trucking_company.customer_type ON customers.type_id=customer_type.id";
		SELECT_CUSTOMERS_BY_ID_LIST = SELECT_ALL_CUSTOMERS + " WHERE customers.id IN (?)";
		SELECT_CUSTOMER_BY_ID = SELECT_ALL_CUSTOMERS + " WHERE users.id=?";
		DELETE_CUSTOMER_BY_ID = "DELETE FROM trucking_company.customers WHERE id=?";
		ADD_NEW_CUSTOMER = "INSERT INTO trucking_company.customers (type_id, name, surname, phone, email ,company_name, id) VALUES (?, ?, ?, ?, ?, ?, ?)";
		UPDATE_CUSTOMER = "UPDATE trucking_company.customers SET type_id=?, name=?, surname=?, phone=?, email=? ,company_name=? WHERE customers.id=?";

		// Driver DAO
		DRIVER_ID = "drivers.id";
		SELECT_ALL_DRIVERS = "SELECT * FROM trucking_company.drivers LEFT JOIN trucking_company.users ON drivers.id=users.id";
		SELECT_DRIVERS_BY_ID_LIST = SELECT_ALL_DRIVERS + " WHERE drivers.id IN (?)";
		SELECT_DRIVER_BY_ID = SELECT_ALL_DRIVERS + " WHERE drivers.id=?";
		DELETE_DRIVER_BY_ID = "DELETE FROM trucking_company.drivers WHERE id=?";
		ADD_NEW_DRIVER = "INSERT INTO trucking_company.drivers (surname, name, phone, id) VALUES (?, ?, ?, ?)";
		UPDATE_DRIVER = "UPDATE trucking_company.drivers SET surname=?, name=?, phone=? WHERE drivers.id=?";

		// Request DAO
		REQUEST_ID = "requests.id";
		SELECT_ALL_REQUESTS = "SELECT * FROM trucking_company.requests LEFT JOIN trucking_company.request_status ON requests.status_id=request_status.id";
		SELECT_REQUESTS_BY_ID_LIST = SELECT_ALL_REQUESTS + " WHERE requests.id IN (?)";
		SELECT_ACTIVE_CUSTOMER_REQUESTS = "SELECT * FROM \n"
				+ "(SELECT * FROM trucking_company.requests WHERE requests.status_id IN(1,3) "
				+ "UNION SELECT * FROM trucking_company.requests JOIN trucking_company.routes ON requests.id=routes.id \n"
				+ "WHERE routes.status_id IN(1,2) ) AS a WHERE a.customer_id=?";
		SELECT_NEW_REQUESTS = SELECT_ALL_REQUESTS 
				+ " LEFT JOIN trucking_company.routes ON requests.id=routes.id  "
				+ "WHERE request_status.status='рассматривается' OR (request_status.status='принята' AND coalesce(routes.id,0)=0)";
		SELECT_CURRENT_REQUESTS = SELECT_ALL_REQUESTS
				+ " RIGHT JOIN trucking_company.routes ON requests.id=routes.id  \n"
				+ "LEFT JOIN trucking_company.route_status ON routes.status_id=route_status.id\n"
				+ "WHERE request_status.status='принята' AND route_status.status!='выполнен'";
		SELECT_FULFILLED_REQUESTS = SELECT_ALL_REQUESTS
				+ " RIGHT JOIN trucking_company.routes ON requests.id=routes.id  \n"
				+ "LEFT JOIN trucking_company.route_status ON routes.status_id=route_status.id\n"
				+ "WHERE route_status.status='выполнен' LIMIT 20";
		SELECT_REJECTED_REQUESTS = SELECT_ALL_REQUESTS
				+ " WHERE request_status.status='отклонена' LIMIT 20";

		SELECT_REQUEST_BY_ID = SELECT_ALL_REQUESTS + " WHERE requests.id=?";
		DELETE_REQUEST_BY_ID = "DELETE FROM trucking_company.requests WHERE id=?";
		ADD_NEW_REQUEST = "INSERT INTO trucking_company.requests (status_id, comment, customer_id) VALUES (?,?,?)";
		UPDATE_REQUEST = "UPDATE trucking_company.requests SET status_id=?, comment=? WHERE id=?";

	}
}