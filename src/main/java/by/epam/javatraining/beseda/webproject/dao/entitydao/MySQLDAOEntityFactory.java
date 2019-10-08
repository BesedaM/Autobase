package by.epam.javatraining.beseda.webproject.dao.entitydao;

import by.epam.javatraining.beseda.webproject.dao.interfacedao.AddressInterface;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.CarInterface;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.CustomerInterface;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.DriverInterface;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.EntityFactoryInterface;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.RequestInterface;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.RouteInterface;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.TaskInterface;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.UserInterface;

public class MySQLDAOEntityFactory implements EntityFactoryInterface {

	private static class SingletonHolder {
		public static final MySQLDAOEntityFactory instance = new MySQLDAOEntityFactory();
	}

	private final AddressInterface addressDAO;
	private final CarInterface carDAO;
	private final CustomerInterface customerDAO;
	private final DriverInterface driverDAO;
	private final RequestInterface requestDAO;
	private final RouteInterface routeDAO;
	private final TaskInterface taskDAO;
	private final UserInterface userDAO;

	private MySQLDAOEntityFactory() {
		addressDAO = new AddressDAO();
		carDAO = new CarDAO();
		customerDAO = new CustomerDAO();
		driverDAO = new DriverDAO();
		requestDAO = new RequestDAO();
		routeDAO = new RouteDAO();
		taskDAO = new TaskDAO();
		userDAO = new UserDAO();
	}

	public static MySQLDAOEntityFactory getFactory() {
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