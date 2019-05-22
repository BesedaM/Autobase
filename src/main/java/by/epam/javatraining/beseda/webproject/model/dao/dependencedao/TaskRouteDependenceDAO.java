package by.epam.javatraining.beseda.webproject.model.dao.dependencedao;

import by.epam.javatraining.beseda.webproject.model.entity.route.Route;
import by.epam.javatraining.beseda.webproject.model.entity.route.Task;
import by.epam.javatraining.beseda.webproject.util.wrapperconnector.WrapperConnector;

import static by.epam.javatraining.beseda.webproject.util.database.SQLQuery.TASK_ROUTE_GET_DEPENDENCE_ID;
import static by.epam.javatraining.beseda.webproject.util.database.SQLQuery.TASK_ROUTE_GET_ENTITIES;
import static by.epam.javatraining.beseda.webproject.util.database.SQLQuery.TASK_ROUTE_UPDATE_DEPENDENCE;

public class TaskRouteDependenceDAO extends ManyToOneDependenceDAO<Task, Route> {

//    private static TaskRouteDependenceDAO ourInstance = new TaskRouteDependenceDAO();
//
//    private TaskRouteDependenceDAO() {
//        super();
//    }
//
//    public static TaskRouteDependenceDAO getDAO() {
//        return ourInstance;
//    }


    public TaskRouteDependenceDAO() {
        super();
    }

    public TaskRouteDependenceDAO(WrapperConnector connector) {
        super(connector);
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
