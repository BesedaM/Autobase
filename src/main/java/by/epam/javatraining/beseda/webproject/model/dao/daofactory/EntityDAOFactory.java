package by.epam.javatraining.beseda.webproject.model.dao.daofactory;

import by.epam.javatraining.beseda.webproject.model.dao.entitydao.*;

/**
 * The aim of the class is to allow user of code create easily different types of entity DAO
 * without need to memorize all of them
 */
public class EntityDAOFactory {

    public static AddressDAO getAddressDAO() {
        return AddressDAO.getDAO();
    }

    public static CarDAO getCarDAO() {
        return CarDAO.getDAO();
    }

    public static CustomerDAO getCustomerDAO() {
        return CustomerDAO.getDAO();
    }

    public static DriverDAO getDriverDAO() {
        return DriverDAO.getDAO();
    }

    public static RequestDAO getRequestDAO() {
        return RequestDAO.getDAO();
    }

    public static RouteDAO getRouteDAO() {
        return RouteDAO.getDAO();
    }

    public static TaskDAO getTaskDAO() {
        return TaskDAO.getDAO();
    }

    public static UserDAO getUserDAO() {
        return UserDAO.getDAO();
    }
}
