package by.epam.javatraining.beseda.webproject.model.exception.EntityException.car;

import by.epam.javatraining.beseda.webproject.model.exception.EntityException.EntityLogicException;

public class IllegalCarStatusException extends EntityLogicException {
    public IllegalCarStatusException() {
    }

    public IllegalCarStatusException(String message) {
        super(message);
    }
}
