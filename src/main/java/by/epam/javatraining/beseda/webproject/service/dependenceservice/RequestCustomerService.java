package by.epam.javatraining.beseda.webproject.service.dependenceservice;

import by.epam.javatraining.beseda.webproject.entity.Request;
import by.epam.javatraining.beseda.webproject.entity.user.Customer;

public class RequestCustomerService extends ManyToOneDependenceService<Request, Customer>{

    RequestCustomerService() {
        super();
        dependenceDAO = daoDependenceFactory.getRequestCustomerDAO();
    }

}
