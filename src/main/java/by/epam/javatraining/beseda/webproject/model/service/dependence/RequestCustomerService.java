package by.epam.javatraining.beseda.webproject.model.service.dependence;

import by.epam.javatraining.beseda.webproject.model.entity.Request;
import by.epam.javatraining.beseda.webproject.model.entity.user.Customer;

public class RequestCustomerService extends ToOneDependenceService<Request, Customer>{

    public RequestCustomerService() {
        super();
        dependenceDAO = daoDependenceFactory.getRequestCustomerDAO();
    }

}
