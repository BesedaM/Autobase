package by.epam.javatraining.beseda.webproject.entity.exception.user;

import by.epam.javatraining.beseda.webproject.entity.exception.EntityLogicException;

public class IllegalCarException extends EntityLogicException {

    public IllegalCarException() {
    }

    public IllegalCarException(String message) {
        super(message);
    }
}
