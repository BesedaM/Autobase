package by.epam.javatraining.beseda.webproject.dao.interfacedao;

public interface EntityFactoryInterface {

    AddressInterface getAddressDAO();

    CarInterface getCarDAO();

    CustomerInterface getCustomerDAO();

    DriverInterface getDriverDAO();

    RequestInterface getRequestDAO();

    RouteInterface getRouteDAO();

    TaskInterface getTaskDAO();

    UserInterface getUserDAO();
}
