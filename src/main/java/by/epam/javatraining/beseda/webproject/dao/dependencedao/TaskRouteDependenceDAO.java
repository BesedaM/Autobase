package by.epam.javatraining.beseda.webproject.dao.dependencedao;


import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.TASK_ROUTE_GET_DEPENDENCE_ID;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.TASK_ROUTE_GET_ENTITIES;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.TASK_ROUTE_UPDATE_DEPENDENCE;

import by.epam.javatraining.beseda.webproject.connectionpool.ConnectionPool;
import by.epam.javatraining.beseda.webproject.entity.route.Route;
import by.epam.javatraining.beseda.webproject.entity.route.Task;

public class TaskRouteDependenceDAO extends ManyToOneDependenceDAO<Task, Route> {

    TaskRouteDependenceDAO() {
        super();
    }
    
    TaskRouteDependenceDAO(ConnectionPool pool) {
        super(pool);
    }

    @Override
    protected String getDependenceIdStatement() {
        return TASK_ROUTE_GET_DEPENDENCE_ID;
    }

    @Override
    protected String getEntitiesByDependenceStatement() {
        return TASK_ROUTE_GET_ENTITIES;
    }

    @Override
    protected String updateDependenceStatement() {
        return TASK_ROUTE_UPDATE_DEPENDENCE;
    }
}