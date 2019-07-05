package by.epam.javatraining.beseda.webproject.model.dao.dependencedao;

import by.epam.javatraining.beseda.webproject.model.entity.Request;
import by.epam.javatraining.beseda.webproject.model.entity.route.Route;

import static by.epam.javatraining.beseda.webproject.model.dao.util.database.SQLQuery.*;

public class RequestRouteDependenceDAO extends OneToOneDependenceDAO<Request,Route> {

    public RequestRouteDependenceDAO() {
        super();
    }

//    private static class SingletonHolder {
//        public static final RequestRouteDependenceDAO instance = new RequestRouteDependenceDAO();
//    }
//
//    public static RequestRouteDependenceDAO getDAO() {
//        return SingletonHolder.instance;
//    }


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
