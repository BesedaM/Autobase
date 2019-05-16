package by.epam.javatraining.beseda.webproject.model.exception.EntityException.car;

import by.epam.javatraining.beseda.webproject.model.exception.EntityException.EntityLogicException;

public class IllegalCarNumberException extends EntityLogicException {

    public IllegalCarNumberException() {
    }

    public IllegalCarNumberException(String message) {
        super(message);
    }
}
