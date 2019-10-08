package by.epam.javatraining.beseda.webproject.integrationtests.databasecreator;

public class CopyQuery {

	static final String ADDRESSES_SQL;
	static final String CAR_STATE_SQL;
	static final String CAR_STATUS_SQL;
	static final String CAR_TYPE_SQL;
	static final String CARS_SQL;
	static final String CARS_IN_ROUTES_SQL;
	static final String CUSTOMER_TYPE_SQL;
	static final String CUSTOMERS_SQL;
	static final String DRIVERS_SQL;
	static final String REQUEST_STATUS_SQL;
	static final String REQUESTS_SQL;
	static final String ROLE_SQL;
	static final String ROUTE_STATUS_SQL;
	static final String ROUTES_SQL;
	static final String TASKS_SQL;
	static final String TRUCK_CAPACITY_SQL;
	static final String USERS_SQL;

	static {
		ADDRESSES_SQL = "COPY trucking_company.addresses (id, country, district, city, street, housenum, building) FROM stdin;";
		CAR_STATE_SQL = "COPY trucking_company.car_state (id, state) FROM stdin;";
		CAR_STATUS_SQL = "COPY trucking_company.car_status (id, status) FROM stdin;";
		CAR_TYPE_SQL = "COPY trucking_company.car_type (id, type) FROM stdin;";
		CARS_SQL = "COPY trucking_company.cars (id, car_number, type_id, model, seatsnumber, capacity_id, status_id, state_id, driver_id) FROM stdin;";
		CARS_IN_ROUTES_SQL = "COPY trucking_company.cars_in_routes (id, route_id, car_id) FROM stdin;";
		CUSTOMER_TYPE_SQL = "COPY trucking_company.customer_type (id, type) FROM stdin;";
		CUSTOMERS_SQL = "COPY trucking_company.customers (id, type_id, name, surname, phone, email, company_name) FROM stdin;";
		DRIVERS_SQL = "COPY trucking_company.drivers (id, surname, name, phone) FROM stdin;";
		REQUEST_STATUS_SQL = "COPY trucking_company.request_status (id, status) FROM stdin;";
		REQUESTS_SQL = "COPY trucking_company.requests (id, customer_id, status_id, request_date, comment) FROM stdin;";
		ROLE_SQL = "COPY trucking_company.role (id, role_name) FROM stdin;";
		ROUTE_STATUS_SQL = "COPY trucking_company.route_status (id, status) FROM stdin;";
		ROUTES_SQL = "COPY trucking_company.routes (id, name, status_id) FROM stdin;";
		TASKS_SQL = "COPY trucking_company.tasks (id, route_id, address_id, \"time\", details) FROM stdin;";
		TRUCK_CAPACITY_SQL = "COPY trucking_company.truck_capacity (id, \"capacity, ton\") FROM stdin;";
		USERS_SQL = "COPY trucking_company.users (id, login, role_id, password) FROM stdin;";
	}

}
