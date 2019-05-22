package by.epam.javatraining.beseda.webproject.model.dao.dependencedao;

import by.epam.javatraining.beseda.webproject.model.entity.Request;
import by.epam.javatraining.beseda.webproject.model.entity.route.Route;
import by.epam.javatraining.beseda.webproject.util.wrapperconnector.WrapperConnector;

import static by.epam.javatraining.beseda.webproject.util.database.SQLQuery.REQUEST_ROUTE_GET_DEPENDENCE_ID;
import static by.epam.javatraining.beseda.webproject.util.database.SQLQuery.REQUEST_ROUTE_GET_ENTITIES;
import static by.epam.javatraining.beseda.webproject.util.database.SQLQuery.REQUEST_ROUTE_UPDATE_DEPENDENCE;

public class RequestRouteDependenceDAO extends OneToOneDependenceDAO<Request,Route> {
//    private static RequestRouteDependenceDAO ourInstance = new RequestRouteDependenceDAO();
//
//    private RequestRouteDependenceDAO() {
//        super();
//    }
//
//    public static RequestRouteDependenceDAO getDAO() {
//        return ourInstance;
//    }


    public RequestRouteDependenceDAO() {
        super();
    }

    public RequestRouteDependenceDAO(WrapperConnector connector) {
        super(connector);
    }

    @Override
    protected String getDependenceIdStatement() {
        return REQUEST_ROUTE_GET_DEPENDENCE_ID;
    }

    @Override
    protected String getEntityByDependenceStatement() {
        return REQUEST_ROUTE_GET_ENTITIES;
    }

    @Override
    protected String updateDependenceStatement() {
        return REQUEST_ROUTE_UPDATE_DEPENDENCE;
    }
}
