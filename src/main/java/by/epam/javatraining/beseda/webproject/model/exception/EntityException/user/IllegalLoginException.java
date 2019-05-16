package by.epam.javatraining.beseda.webproject.model.exception.EntityException.user;

import by.epam.javatraining.beseda.webproject.model.exception.EntityException.EntityLogicException;

public class IllegalLoginException extends EntityLogicException {
    public IllegalLoginException() {
    }

    public IllegalLoginException(String message) {
        super(message);
    }
}
