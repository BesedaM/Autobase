package by.epam.javatraining.beseda.webproject.dao.dependencedao;

import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.REQUEST_CUSTOMER_GET_DEPENDENCE_ID;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.REQUEST_CUSTOMER_GET_ENTITIES;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.REQUEST_CUSTOMER_UPDATE_DEPENDENCE;

import by.epam.javatraining.beseda.webproject.connectionpool.ConnectionPool;
import by.epam.javatraining.beseda.webproject.entity.Request;
import by.epam.javatraining.beseda.webproject.entity.user.Customer;

public class RequestCustomerDependenceDAO extends ManyToOneDependenceDAO<Request, Customer> {

    RequestCustomerDependenceDAO() {
        super();
    }

    RequestCustomerDependenceDAO(ConnectionPool pool) {
        super(pool);
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