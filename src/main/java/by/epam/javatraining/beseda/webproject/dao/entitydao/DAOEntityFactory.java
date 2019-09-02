package by.epam.javatraining.beseda.webproject.dao.entitydao;

public class DAOEntityFactory {

    private static class SingletonHolder {
        public static DAOEntityFactory instance = new DAOEntityFactory();
    }

    private final AddressDAO addressDAO;
    private final CarDAO carDAO;
    private final CustomerDAO customerDAO;
    private final DriverDAO driverDAO;
    private final RequestDAO requestDAO;
    private final RouteDAO routeDAO;
    private final TaskDAO taskDAO;
    private final UserDAO userDAO;

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

    public AddressDAO getAddressDAO() {
        return addressDAO;
    }

    public CarDAO getCarDAO() {
        return carDAO;
    }

    public CustomerDAO getCustomerDAO() {
        return customerDAO;
    }

    public DriverDAO getDriverDAO() {
        return driverDAO;
    }

    public RequestDAO getRequestDAO() {
        return requestDAO;
    }

    public RouteDAO getRouteDAO() {
        return routeDAO;
    }

    public TaskDAO getTaskDAO() {
        return taskDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }
}
