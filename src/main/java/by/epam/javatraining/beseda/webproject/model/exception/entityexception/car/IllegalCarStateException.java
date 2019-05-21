package by.epam.javatraining.beseda.webproject.model.exception.entityexception.car;

import by.epam.javatraining.beseda.webproject.model.exception.entityexception.EntityLogicException;

public class IllegalCarStateException extends EntityLogicException {
    public IllegalCarStateException() {
    }

    public IllegalCarStateException(String message) {
        super(message);
    }
}
