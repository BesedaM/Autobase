package by.epam.javatraining.beseda.webproject.model.exception.EntityException.route;

import by.epam.javatraining.beseda.webproject.model.exception.EntityException.EntityLogicException;

public class IllegalRouteStatusException  extends EntityLogicException {

    public IllegalRouteStatusException() {
    }

    public IllegalRouteStatusException(String message) {
        super(message);
    }
}
