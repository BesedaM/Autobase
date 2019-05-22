package by.epam.javatraining.beseda.webproject.model.dao.dependencedao;

import by.epam.javatraining.beseda.webproject.model.entity.route.Address;
import by.epam.javatraining.beseda.webproject.model.entity.route.Task;
import by.epam.javatraining.beseda.webproject.util.wrapperconnector.WrapperConnector;

import static by.epam.javatraining.beseda.webproject.util.database.SQLQuery.TASK_ADDRESS_GET_DEPENDENCE_ID;
import static by.epam.javatraining.beseda.webproject.util.database.SQLQuery.TASK_ADDRESS_GET_ENTITIES;
import static by.epam.javatraining.beseda.webproject.util.database.SQLQuery.TASK_ADDRESS_UPDATE_DEPENDENCE;

public class TaskAddressDependenceDAO extends ManyToOneDependenceDAO<Task, Address> {


//    private static TaskAddressDependenceDAO ourInstance = new TaskAddressDependenceDAO();
//
//    private TaskAddressDependenceDAO() {
//        super();
//    }
//
//    public static TaskAddressDependenceDAO getDAO() {
//        return ourInstance;
//    }


    public TaskAddressDependenceDAO() {
        super();
    }

    public TaskAddressDependenceDAO(WrapperConnector connector) {
        super(connector);
    }

    @Override
    protected String getDependenceIdStatement() {
        return TASK_ADDRESS_GET_DEPENDENCE_ID;
    }

    @Override
    protected String getEntitiesByDependenceStatement() {
        return TASK_ADDRESS_GET_ENTITIES;
    }

    @Override
    protected String updateDependenceStatement() {
        return TASK_ADDRESS_UPDATE_DEPENDENCE;
    }
}
