package by.epam.javatraining.beseda.webproject.dao.entitydao;

import by.epam.javatraining.beseda.webproject.connectionpool.ConnectionPool;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.AddressInterface;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.CarInterface;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.CustomerInterface;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.DriverInterface;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.EntityFactoryInterface;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.RequestInterface;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.RouteInterface;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.TaskInterface;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.UserInterface;

public class TestDAOEntityFactory implements EntityFactoryInterface {

	private static TestDAOEntityFactory factory;

	private final AddressInterface addressDAO;
	private final CarInterface carDAO;
	private final CustomerInterface customerDAO;
	private final DriverInterface driverDAO;
	private final RequestInterface requestDAO;
	private final RouteInterface routeDAO;
	private final TaskInterface taskDAO;
	private final UserInterface userDAO;

	private TestDAOEntityFactory(ConnectionPool pool) {
		addressDAO = new AddressDAO(pool);
		carDAO = new CarDAO(pool);
		customerDAO = new CustomerDAO(pool);
		driverDAO = new DriverDAO(pool);
		requestDAO = new RequestDAO(pool);
		routeDAO = new RouteDAO(pool);
		taskDAO = new TaskDAO(pool);
		userDAO = new UserDAO(pool);
	}

	public static TestDAOEntityFactory getFactory(ConnectionPool pool) {
		if (factory == null) {
			factory = new TestDAOEntityFactory(pool);
		}
		return factory;
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