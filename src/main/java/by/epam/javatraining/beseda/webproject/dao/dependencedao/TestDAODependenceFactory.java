package by.epam.javatraining.beseda.webproject.dao.dependencedao;

import by.epam.javatraining.beseda.webproject.connectionpool.ConnectionPool;

public class TestDAODependenceFactory {

	private static TestDAODependenceFactory factory;

	private final CarDriverDependenceDAO carDriverDAO;
	private final CarRouteDependenceDAO carRouteDAO;
	private final RequestCustomerDependenceDAO requestCustomerDAO;
	private final TaskAddressDependenceDAO taskAddressDAO;
	private final TaskRouteDependenceDAO taskRouteDAO;

	private TestDAODependenceFactory(ConnectionPool cpool) {
		carDriverDAO = new CarDriverDependenceDAO(cpool);
		carRouteDAO = new CarRouteDependenceDAO(cpool);
		requestCustomerDAO = new RequestCustomerDependenceDAO(cpool);
		taskAddressDAO = new TaskAddressDependenceDAO(cpool);
		taskRouteDAO = new TaskRouteDependenceDAO(cpool);
	}

	public static TestDAODependenceFactory getFactory(ConnectionPool cpool) {
		if (factory == null) {
			factory = new TestDAODependenceFactory(cpool);
		}
		return factory;
	}

	public CarDriverDependenceDAO getCarDriverDAO() {
		return carDriverDAO;
	}

	public CarRouteDependenceDAO getCarRouteDAO() {
		return carRouteDAO;
	}

	public RequestCustomerDependenceDAO getRequestCustomerDAO() {
		return requestCustomerDAO;
	}

	public TaskAddressDependenceDAO getTaskAddressDAO() {
		return taskAddressDAO;
	}

	public TaskRouteDependenceDAO getTaskRouteDAO() {
		return taskRouteDAO;
	}
}