package by.epam.javatraining.beseda.webproject.entity.exception.car;

import by.epam.javatraining.beseda.webproject.entity.exception.EntityLogicException;

public class IllegalCarNumberException extends EntityLogicException {

    public IllegalCarNumberException() {
    }

    public IllegalCarNumberException(String message) {
        super(message);
    }
}
