package by.epam.javatraining.beseda.webproject.dao.dependencedao;

import by.epam.javatraining.beseda.webproject.entity.route.Route;
import by.epam.javatraining.beseda.webproject.entity.route.Task;

import static by.epam.javatraining.beseda.webproject.dao.util.database.SQLQuery.*;

public class TaskRouteDependenceDAO extends ManyToOneDependenceDAO<Task, Route> {

    TaskRouteDependenceDAO() {
        super();
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
