package by.epam.javatraining.beseda.webproject.model.dao.dependencydao;

import by.epam.javatraining.beseda.webproject.model.entity.route.Route;
import by.epam.javatraining.beseda.webproject.model.entity.route.Task;

import static by.epam.javatraining.beseda.webproject.util.database.DBEntityTableName.ROUTE_ID_TASKS;
import static by.epam.javatraining.beseda.webproject.util.database.DBEntityTableName.T_TASKS;

public class RouteTaskDependencyDAO extends OneToManyDependencyDAO<Route, Task> {

    private static RouteTaskDependencyDAO ourInstance = new RouteTaskDependencyDAO();

    private RouteTaskDependencyDAO() {
        super();
        this.tableName = T_TASKS;
        this.columnName = ROUTE_ID_TASKS;
    }

    public static RouteTaskDependencyDAO getDAO() {
        return ourInstance;
    }
}
