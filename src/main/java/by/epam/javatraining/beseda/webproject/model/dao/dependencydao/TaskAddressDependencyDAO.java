package by.epam.javatraining.beseda.webproject.model.dao.dependencydao;

import by.epam.javatraining.beseda.webproject.model.entity.route.Address;
import by.epam.javatraining.beseda.webproject.model.entity.route.Task;

import static by.epam.javatraining.beseda.webproject.util.database.DBEntityTableName.ADDRESS_ID_TASKS;
import static by.epam.javatraining.beseda.webproject.util.database.DBEntityTableName.T_TASKS;

public class TaskAddressDependencyDAO extends OneToOneDependencyDAO<Address, Task> {


    private static TaskAddressDependencyDAO ourInstance = new TaskAddressDependencyDAO();

    private TaskAddressDependencyDAO() {
        super();
        this.tableName = T_TASKS;
        this.columnName = ADDRESS_ID_TASKS;
    }

    public static TaskAddressDependencyDAO getDAO() {
        return ourInstance;
    }
}
