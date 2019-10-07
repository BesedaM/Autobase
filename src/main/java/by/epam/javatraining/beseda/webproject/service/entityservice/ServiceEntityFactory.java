package by.epam.javatraining.beseda.webproject.service.entityservice;

/**
 * Class for creating instances for all the available services in the application.
 * 
 * @author Maryia_Biaseda
 *
 */
public class ServiceEntityFactory {

	private static class SingletonHolder {
		private static ServiceEntityFactory instance = new ServiceEntityFactory();
	}

	private final AddressService addressService;
	private final CarService carService;
	private final CustomerService customerService;
	private final DriverService driverService;
	private final RequestService requestService;
	private final RouteService routeService;
	private final TaskService taskService;
	private final UserService userService;

	private ServiceEntityFactory() {
		addressService = new AddressService();
		carService = new CarService();
		customerService = new CustomerService();
		driverService = new DriverService();
		requestService = new RequestService();
		routeService = new RouteService();
		taskService = new TaskService();
		userService = new UserService();
	}

	public static ServiceEntityFactory getFactory() {
		return SingletonHolder.instance;
	}

	public AddressService getAddressService() {
		return addressService;
	}

	public CarService getCarService() {
		return carService;
	}

	public CustomerService getCustomerService() {
		return customerService;
	}

	public DriverService getDriverService() {
		return driverService;
	}

	public RequestService getRequestService() {
		return requestService;
	}

	public RouteService getRouteService() {
		return routeService;
	}

	public TaskService getTaskService() {
		return taskService;
	}

	public UserService getUserService() {
		return userService;
	}
}