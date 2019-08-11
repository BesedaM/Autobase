package by.epam.javatraining.beseda.webproject.model.dao.dependencedao;

import by.epam.javatraining.beseda.webproject.model.entity.route.Route;
import by.epam.javatraining.beseda.webproject.model.entity.route.Task;

import static by.epam.javatraining.beseda.webproject.model.dao.util.database.SQLQuery.*;

public class TaskRouteDependenceDAO extends ManyToOneDependenceDAO<Task, Route> {

    public TaskRouteDependenceDAO() {
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
