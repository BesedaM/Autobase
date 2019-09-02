package by.epam.javatraining.beseda.webproject.entity.exception.car;

import by.epam.javatraining.beseda.webproject.entity.exception.EntityLogicException;

public class IllegalCarStateException extends EntityLogicException {
    public IllegalCarStateException() {
    }

    public IllegalCarStateException(String message) {
        super(message);
    }
}
