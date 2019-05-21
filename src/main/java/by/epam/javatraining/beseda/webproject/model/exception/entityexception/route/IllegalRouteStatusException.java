package by.epam.javatraining.beseda.webproject.model.exception.entityexception.route;

import by.epam.javatraining.beseda.webproject.model.exception.entityexception.EntityLogicException;

public class IllegalRouteStatusException  extends EntityLogicException {

    public IllegalRouteStatusException() {
    }

    public IllegalRouteStatusException(String message) {
        super(message);
    }
}
