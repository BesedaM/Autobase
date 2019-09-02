package by.epam.javatraining.beseda.webproject.entity.exception.request;

import by.epam.javatraining.beseda.webproject.entity.exception.EntityLogicException;

public class IllegalRouteException extends EntityLogicException {

    public IllegalRouteException() {
    }

    public IllegalRouteException(String message) {
        super(message);
    }
}
