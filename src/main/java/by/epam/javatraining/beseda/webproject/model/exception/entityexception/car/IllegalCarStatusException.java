package by.epam.javatraining.beseda.webproject.model.exception.entityexception.car;

import by.epam.javatraining.beseda.webproject.model.exception.entityexception.EntityLogicException;

public class IllegalCarStatusException extends EntityLogicException {
    public IllegalCarStatusException() {
    }

    public IllegalCarStatusException(String message) {
        super(message);
    }
}
