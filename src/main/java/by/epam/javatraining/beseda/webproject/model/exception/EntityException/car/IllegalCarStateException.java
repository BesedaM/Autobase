package by.epam.javatraining.beseda.webproject.model.exception.EntityException.car;

import by.epam.javatraining.beseda.webproject.model.exception.EntityException.EntityLogicException;

public class IllegalCarStateException extends EntityLogicException {
    public IllegalCarStateException() {
    }

    public IllegalCarStateException(String message) {
        super(message);
    }
}
