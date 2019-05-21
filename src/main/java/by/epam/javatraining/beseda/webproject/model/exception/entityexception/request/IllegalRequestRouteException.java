package by.epam.javatraining.beseda.webproject.model.exception.entityexception.request;

import by.epam.javatraining.beseda.webproject.model.exception.entityexception.EntityLogicException;

public class IllegalRequestRouteException extends EntityLogicException {

    public IllegalRequestRouteException() {
    }

    public IllegalRequestRouteException(String message) {
        super(message);
    }
}
