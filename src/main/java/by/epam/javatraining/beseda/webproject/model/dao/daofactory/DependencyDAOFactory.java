package by.epam.javatraining.beseda.webproject.model.dao.daofactory;

import by.epam.javatraining.beseda.webproject.model.dao.dependencydao.*;

/**
 * The aim of the class is to allow user of code create easily different types of dependency DAO
 * without need to memorize all of them
 */
public class DependencyDAOFactory {

    public static CarDriverDependencyDAO getCarDriverDependencyDAO() {
        return CarDriverDependencyDAO.getDAO();
    }

    public static RequestCustomerDependencyDAO getRequestCustomerDependencyDAO() {
        return RequestCustomerDependencyDAO.getDAO();
    }

    public static RequestRouteDependencyDAO getRequestRouteDependencyDAO(){
        return RequestRouteDependencyDAO.getDAO();
    }

    public static RouteCarDependencyDAO getRouteCarDependencyDAO(){
        return RouteCarDependencyDAO.getDAO();
    }

    public static TaskAddressDependencyDAO getTaskAddressDependencyDAO(){
        return TaskAddressDependencyDAO.getDAO();
    }

    public static RouteTaskDependencyDAO getTaskRouteDependencyDAO(){
        return RouteTaskDependencyDAO.getDAO();
    }
}
