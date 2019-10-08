package by.epam.javatraining.beseda.webproject.dao.dependencedao;


import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.TASK_ADDRESS_GET_DEPENDENCE_ID;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.TASK_ADDRESS_GET_ENTITIES;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.TASK_ADDRESS_UPDATE_DEPENDENCE;

import by.epam.javatraining.beseda.webproject.connectionpool.ConnectionPool;
import by.epam.javatraining.beseda.webproject.entity.route.Address;
import by.epam.javatraining.beseda.webproject.entity.route.Task;


public class TaskAddressDependenceDAO extends ManyToOneDependenceDAO<Task, Address> {

    TaskAddressDependenceDAO() {
        super();
    }

    
    TaskAddressDependenceDAO(ConnectionPool pool) {
        super(pool);
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