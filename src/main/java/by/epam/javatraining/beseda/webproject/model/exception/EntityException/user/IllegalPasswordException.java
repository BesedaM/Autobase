package by.epam.javatraining.beseda.webproject.model.exception.EntityException.user;

import by.epam.javatraining.beseda.webproject.model.exception.EntityException.EntityLogicException;

public class IllegalPasswordException extends EntityLogicException {
    public IllegalPasswordException() {
    }

    public IllegalPasswordException(String message) {
        super(message);
    }
}
