package by.epam.javatraining.beseda.webproject.model.service.entity;

import by.epam.javatraining.beseda.webproject.model.entity.route.Task;

import java.util.GregorianCalendar;

public class TaskService extends AbstractEntityService<Task> {

    public TaskService() {
        super();
        entityDAO = daoEntityFactory.getTaskDAO();
    }

//    private static class SingletonHolder {
//        public static final TaskService instance = new TaskService();
//    }
//
//    public static TaskService getService() {
//        return SingletonHolder.instance;
//    }

    /**
     * Creates entity WITHOUT ID with the given data
     */
    public Task createTask(GregorianCalendar time, String details) {
        Task task = null;
        if (time != null && details != null) {
            task = new Task(time, details);
        }
        return task;
    }
}
