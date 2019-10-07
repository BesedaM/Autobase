package by.epam.javatraining.beseda.webproject.dao.entitybuilder;

public class EntityBuilderFactory {

	private AddressBuilder addressBuilder;
	private CarBuilder carBuilder;
	private DriverBuilder driverBuilder;
	private CustomerBuilder customerBuilder;
	private RequestBuilder requestBuilder;
	private RouteBuilder routeBuilder;
	private TaskBuilder taskBuilder;
	private UserBuilder userBuilder;

	private static class SingletonHolder {
		public static final EntityBuilderFactory instance = new EntityBuilderFactory();
	}

	public static EntityBuilderFactory getFactory() {
		return SingletonHolder.instance;
	}

	public EntityBuilderFactory() {
		addressBuilder = new AddressBuilder();
		carBuilder = new CarBuilder();
		driverBuilder = new DriverBuilder();
		customerBuilder = new CustomerBuilder();
		requestBuilder = new RequestBuilder();
		routeBuilder = new RouteBuilder();
		taskBuilder = new TaskBuilder();
		userBuilder = new UserBuilder();
	}

	public AddressBuilder getAddressBuilder() {
		return addressBuilder;
	}

	public CarBuilder getCarBuilder() {
		return carBuilder;
	}

	public DriverBuilder getDriverBuilder() {
		return driverBuilder;
	}

	public CustomerBuilder getCustomerBuilder() {
		return customerBuilder;
	}

	public RequestBuilder getRequestBuilder() {
		return requestBuilder;
	}

	public RouteBuilder getRouteBuilder() {
		return routeBuilder;
	}

	public TaskBuilder getTaskBuilder() {
		return taskBuilder;
	}

	public UserBuilder getUserBuilder() {
		return userBuilder;
	}
	
}
