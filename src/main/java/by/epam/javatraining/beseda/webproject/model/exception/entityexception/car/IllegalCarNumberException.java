package by.epam.javatraining.beseda.webproject.model.exception.entityexception.car;

import by.epam.javatraining.beseda.webproject.model.exception.entityexception.EntityLogicException;

public class IllegalCarNumberException extends EntityLogicException {

    public IllegalCarNumberException() {
    }

    public IllegalCarNumberException(String message) {
        super(message);
    }
}
