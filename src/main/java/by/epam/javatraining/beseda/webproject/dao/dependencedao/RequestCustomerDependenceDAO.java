package by.epam.javatraining.beseda.webproject.dao.dependencedao;

import by.epam.javatraining.beseda.webproject.entity.Request;
import by.epam.javatraining.beseda.webproject.entity.user.Customer;

import static by.epam.javatraining.beseda.webproject.dao.util.database.SQLQuery.*;

public class RequestCustomerDependenceDAO extends ManyToOneDependenceDAO<Request, Customer> {

    RequestCustomerDependenceDAO() {
        super();
    }


    @Override
    protected String getDependenceIdStatement() {
        return REQUEST_CUSTOMER_GET_DEPENDENCE_ID;
    }

    @Override
    protected String getEntitiesByDependenceStatement() {
        return REQUEST_CUSTOMER_GET_ENTITIES;
    }

    @Override
    protected String updateDependenceStatement() {
        return REQUEST_CUSTOMER_UPDATE_DEPENDENCE;
    }
}