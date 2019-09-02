package by.epam.javatraining.beseda.webproject.entity.exception.route;

import by.epam.javatraining.beseda.webproject.entity.exception.EntityLogicException;

public class IllegalRouteStatusException  extends EntityLogicException {

    public IllegalRouteStatusException() {
    }

    public IllegalRouteStatusException(String message) {
        super(message);
    }
}
