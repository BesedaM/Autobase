package by.epam.javatraining.beseda.webproject.entity.exception.car;

import by.epam.javatraining.beseda.webproject.entity.exception.EntityLogicException;

public class IllegalCarStatusException extends EntityLogicException {
    public IllegalCarStatusException() {
    }

    public IllegalCarStatusException(String message) {
        super(message);
    }
}
