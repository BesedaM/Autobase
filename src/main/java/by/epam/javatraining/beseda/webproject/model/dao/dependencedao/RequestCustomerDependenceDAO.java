package by.epam.javatraining.beseda.webproject.model.dao.dependencedao;

import by.epam.javatraining.beseda.webproject.model.entity.Request;
import by.epam.javatraining.beseda.webproject.model.entity.user.Customer;
import by.epam.javatraining.beseda.webproject.util.wrapperconnector.WrapperConnector;

import static by.epam.javatraining.beseda.webproject.util.database.SQLQuery.REQUEST_CUSTOMER_GET_DEPENDENCE_ID;
import static by.epam.javatraining.beseda.webproject.util.database.SQLQuery.REQUEST_CUSTOMER_GET_ENTITIES;
import static by.epam.javatraining.beseda.webproject.util.database.SQLQuery.REQUEST_CUSTOMER_UPDATE_DEPENDENCE;

public class RequestCustomerDependenceDAO extends ManyToOneDependenceDAO<Request, Customer> {
//    private static RequestCustomerDependenceDAO ourInstance = new RequestCustomerDependenceDAO();
//
//    private RequestCustomerDependenceDAO() {
//        super();
//    }
//
//    public static RequestCustomerDependenceDAO getDAO() {
//        return ourInstance;
//    }


    public RequestCustomerDependenceDAO() {
        super();
    }

    public RequestCustomerDependenceDAO(WrapperConnector connector) {
        super(connector);
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
