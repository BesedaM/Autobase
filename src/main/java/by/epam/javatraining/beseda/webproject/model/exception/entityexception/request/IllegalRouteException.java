package by.epam.javatraining.beseda.webproject.model.exception.entityexception.request;

import by.epam.javatraining.beseda.webproject.model.exception.entityexception.EntityLogicException;

public class IllegalRouteException extends EntityLogicException {

    public IllegalRouteException() {
    }

    public IllegalRouteException(String message) {
        super(message);
    }
}
