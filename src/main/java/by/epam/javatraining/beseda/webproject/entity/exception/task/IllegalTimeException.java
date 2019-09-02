package by.epam.javatraining.beseda.webproject.entity.exception.task;

import by.epam.javatraining.beseda.webproject.entity.exception.EntityLogicException;

public class IllegalTimeException extends EntityLogicException {

    public IllegalTimeException() {
    }

    public IllegalTimeException(String message) {
        super(message);
    }
}
