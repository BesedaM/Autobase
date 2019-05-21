package by.epam.javatraining.beseda.webproject.model.exception.entityexception.user;

import by.epam.javatraining.beseda.webproject.model.exception.entityexception.EntityLogicException;

public class IllegalPasswordException extends EntityLogicException {
    public IllegalPasswordException() {
    }

    public IllegalPasswordException(String message) {
        super(message);
    }
}
