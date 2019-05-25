package by.epam.javatraining.beseda.webproject.model.dao.dependencedao;

import by.epam.javatraining.beseda.webproject.model.entity.Request;
import by.epam.javatraining.beseda.webproject.model.entity.user.Customer;

import static by.epam.javatraining.beseda.webproject.util.database.SQLQuery.*;

public class RequestCustomerDependenceDAO extends ManyToOneDependenceDAO<Request, Customer> {

    private RequestCustomerDependenceDAO() {
        super();
    }

    private static class SingletonHolder {
        public static final RequestCustomerDependenceDAO instance = new RequestCustomerDependenceDAO();
    }

    public static RequestCustomerDependenceDAO getDAO() {
        return SingletonHolder.instance;
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
