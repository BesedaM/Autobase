package by.epam.javatraining.beseda.webproject.model.dao.dependencydao;

import static by.epam.javatraining.beseda.webproject.util.database.DBEntityTableName.ROUTE_ID_TASKS;
import static by.epam.javatraining.beseda.webproject.util.database.DBEntityTableName.T_TASKS;

public class TaskRouteDependencyDAO extends DependencyDAO {

    private static TaskRouteDependencyDAO ourInstance = new TaskRouteDependencyDAO();

    private TaskRouteDependencyDAO() {
        super();
        this.tableName = T_TASKS;
        this.columnName = ROUTE_ID_TASKS;
    }

    public static TaskRouteDependencyDAO getInstance() {
        return ourInstance;
    }
}
