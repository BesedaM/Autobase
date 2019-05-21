package by.epam.javatraining.beseda.webproject.model.exception.entityexception.user;

import by.epam.javatraining.beseda.webproject.model.exception.entityexception.EntityLogicException;

public class IllegalLoginException extends EntityLogicException {
    public IllegalLoginException() {
    }

    public IllegalLoginException(String message) {
        super(message);
    }
}
