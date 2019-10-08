package by.epam.javatraining.beseda.webproject.service;

public class ServiceConstants {

	// password check
	public static final String PASSWORD_VALIDATE;

	public static final String USER_ROLE;
	public static final String CUSTOMER_TYPE;
	public static final String TRUCK_CAPACITY;
	public static final String CAR_STATUS;
	public static final String CAR_STATE;
	public static final String CAR_TYPE;
	public static final String ROUTE_STATUS;
	public static final String REQUEST_STATUS;
	public static final byte[] SALT_HASHING;
	public static final int ITERATION_COUNT;
	public static final int KEY_LENGTH;

	static {

		PASSWORD_VALIDATE = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=\\S+$).{6,20})";

		USER_ROLE = "user_role";
		CUSTOMER_TYPE = "customer_type";
		TRUCK_CAPACITY = "truck_capacity";
		CAR_STATUS = "car_status";
		CAR_STATE = "car_state";
		CAR_TYPE = "car_type";
		ROUTE_STATUS = "route_status";
		REQUEST_STATUS = "request_status";
		SALT_HASHING = new byte[] { 8, 45, 95, 4, -5, 88, 4 };
		ITERATION_COUNT = 512;
		KEY_LENGTH = 128;
	}

}