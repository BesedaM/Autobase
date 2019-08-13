package by.epam.javatraining.beseda.webproject.model.service.entityservice;

import by.epam.javatraining.beseda.webproject.model.entity.route.Task;

import java.util.GregorianCalendar;

public class TaskService extends AbstractEntityService<Task> {

    public TaskService() {
        super();
        entityDAO = daoEntityFactory.getTaskDAO();
    }

    /**
     * Creates entityservice WITHOUT ID with the given data
     */
    public Task createTask(GregorianCalendar time, String details) {
        Task task = null;
        if (time != null && details != null) {
            task = new Task(time, details);
        }
        return task;
    }
}
