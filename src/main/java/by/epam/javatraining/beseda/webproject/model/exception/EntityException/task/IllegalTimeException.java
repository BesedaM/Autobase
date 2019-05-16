package by.epam.javatraining.beseda.webproject.model.exception.EntityException.task;

import by.epam.javatraining.beseda.webproject.model.exception.EntityException.EntityLogicException;

public class IllegalTimeException extends EntityLogicException {

    public IllegalTimeException() {
    }

    public IllegalTimeException(String message) {
        super(message);
    }
}
