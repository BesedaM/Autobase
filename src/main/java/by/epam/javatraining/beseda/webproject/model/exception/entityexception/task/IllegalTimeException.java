package by.epam.javatraining.beseda.webproject.model.exception.entityexception.task;

import by.epam.javatraining.beseda.webproject.model.exception.entityexception.EntityLogicException;

public class IllegalTimeException extends EntityLogicException {

    public IllegalTimeException() {
    }

    public IllegalTimeException(String message) {
        super(message);
    }
}
