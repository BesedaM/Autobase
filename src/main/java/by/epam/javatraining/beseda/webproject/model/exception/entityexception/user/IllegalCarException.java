package by.epam.javatraining.beseda.webproject.model.exception.entityexception.user;

import by.epam.javatraining.beseda.webproject.model.exception.entityexception.EntityLogicException;

public class IllegalCarException extends EntityLogicException {

    public IllegalCarException() {
    }

    public IllegalCarException(String message) {
        super(message);
    }
}
