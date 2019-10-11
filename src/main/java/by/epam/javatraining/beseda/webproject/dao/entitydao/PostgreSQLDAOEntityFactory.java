package by.epam.javatraining.beseda.webproject.dao.entitydao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import by.epam.javatraining.beseda.webproject.dao.interfacedao.AddressInterface;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.CarInterface;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.CustomerInterface;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.DriverInterface;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.EntityFactoryInterface;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.RequestInterface;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.RouteInterface;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.TaskInterface;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.UserInterface;

@Component
public class PostgreSQLDAOEntityFactory implements EntityFactoryInterface {

	private static class SingletonHolder {
		public static final PostgreSQLDAOEntityFactory instance = new PostgreSQLDAOEntityFactory();
	}

	@Autowired
	private AddressInterface addressDAO;
	
	@Autowired
	private CarInterface carDAO;
	
	@Autowired
	private CustomerInterface customerDAO;
	
	@Autowired
	private DriverInterface driverDAO;
	
	@Autowired
	private RequestInterface requestDAO;
	
	@Autowired
	private RouteInterface routeDAO;
	
	@Autowired
	private TaskInterface taskDAO;
	
	@Autowired
	@Qualifier("postgresqlUserDAO")
	private UserInterface userDAO;

//	private PostgreSQLDAOEntityFactory() {
//		addressDAO = new AddressDAO();
//		carDAO = new CarDAO();
//		customerDAO = new CustomerDAO();
//		driverDAO = new DriverDAO();
//		requestDAO = new RequestDAO();
//		routeDAO = new RouteDAO();
//		taskDAO = new TaskDAO();
//		userDAO = new UserDAO();
//	}

	public static PostgreSQLDAOEntityFactory getFactory() {
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