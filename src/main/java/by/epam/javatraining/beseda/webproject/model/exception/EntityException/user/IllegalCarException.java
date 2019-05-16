package by.epam.javatraining.beseda.webproject.model.exception.EntityException.user;

import by.epam.javatraining.beseda.webproject.model.exception.EntityException.EntityLogicException;

public class IllegalCarException extends EntityLogicException {

    public IllegalCarException() {
    }

    public IllegalCarException(String message) {
        super(message);
    }
}
