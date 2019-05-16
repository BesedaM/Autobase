package by.epam.javatraining.beseda.webproject.model.exception.EntityException.request;

import by.epam.javatraining.beseda.webproject.model.exception.EntityException.EntityLogicException;

public class IllegalRequestRouteException extends EntityLogicException {

    public IllegalRequestRouteException() {
    }

    public IllegalRequestRouteException(String message) {
        super(message);
    }
}
