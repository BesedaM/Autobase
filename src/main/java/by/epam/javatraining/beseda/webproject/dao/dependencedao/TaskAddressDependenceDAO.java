package by.epam.javatraining.beseda.webproject.dao.dependencedao;

import by.epam.javatraining.beseda.webproject.entity.route.Address;
import by.epam.javatraining.beseda.webproject.entity.route.Task;

import static by.epam.javatraining.beseda.webproject.dao.util.database.SQLQuery.*;

public class TaskAddressDependenceDAO extends ManyToOneDependenceDAO<Task, Address> {

    TaskAddressDependenceDAO() {
        super();
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