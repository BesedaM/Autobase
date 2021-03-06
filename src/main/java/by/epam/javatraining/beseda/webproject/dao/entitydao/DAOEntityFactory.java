package by.epam.javatraining.beseda.webproject.dao.entitydao;

import by.epam.javatraining.beseda.webproject.dao.interfacedao.*;

public class DAOEntityFactory implements EntityFactoryInterface {

	private static class SingletonHolder {
		public static final DAOEntityFactory instance = new DAOEntityFactory();
	}

	private final AddressInterface addressDAO;
	private final CarInterface carDAO;
	private final CustomerInterface customerDAO;
	private final DriverInterface driverDAO;
	private final RequestInterface requestDAO;
	private final RouteInterface routeDAO;
	private final TaskInterface taskDAO;
	private final UserInterface userDAO;

	private DAOEntityFactory() {
		addressDAO = new AddressDAO();
		carDAO = new CarDAO();
		customerDAO = new CustomerDAO();
		driverDAO = new DriverDAO();
		requestDAO = new RequestDAO();
		routeDAO = new RouteDAO();
		taskDAO = new TaskDAO();
		userDAO = new UserDAO();
	}

	public static DAOEntityFactory getFactory() {
		return SingletonHolder.instance;
	}

	@Override
	public AddressInterface getAddressDAO() {
		return addressDAO;
	}

	@Override
	public CarInterface getCarDAO() {
		return carDAO;
	}

	@Override
	public CustomerInterface getCustomerDAO() {
		return customerDAO;
	}

	@Override
	public DriverInterface getDriverDAO() {
		return driverDAO;
	}

	@Override
	public RequestInterface getRequestDAO() {
		return requestDAO;
	}

	@Override
	public RouteInterface getRouteDAO() {
		return routeDAO;
	}

	@Override
	public TaskInterface getTaskDAO() {
		return taskDAO;
	}

	@Override
	public UserInterface getUserDAO() {
		return userDAO;
	}
}