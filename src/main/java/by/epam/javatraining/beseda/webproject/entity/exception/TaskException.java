package by.epam.javatraining.beseda.webproject.entity.exception;

public class TaskException extends EntityLogicException {

    public TaskException() {
        super("Task field assignment exception");
    }

    public TaskException(String message) {
        super(message);
    }
}
