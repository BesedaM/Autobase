package by.epam.javatraining.beseda.webproject.model.dao.factory;

import by.epam.javatraining.beseda.webproject.model.dao.dependencedao.*;

public class DAODependenceFactory {

    private static class SingletonHolder {
        public static final DAODependenceFactory instance = new DAODependenceFactory();
    }

    private final CarDriverDependenceDAO carDriverDAO;
    private final CarRouteDependenceDAO carRouteDAO;
    private final RequestCustomerDependenceDAO requestCustomerDAO;
    private final TaskAddressDependenceDAO taskAddressDAO;
    private final TaskRouteDependenceDAO taskRouteDAO;

    private DAODependenceFactory() {
        carDriverDAO = new CarDriverDependenceDAO();
        carRouteDAO = new CarRouteDependenceDAO();
        requestCustomerDAO = new RequestCustomerDependenceDAO();
        taskAddressDAO = new TaskAddressDependenceDAO();
        taskRouteDAO = new TaskRouteDependenceDAO();
    }

    public static DAODependenceFactory getFactory() {
        return SingletonHolder.instance;
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
