package by.epam.javatraining.beseda.webproject.model.exception.EntityException.route;

import by.epam.javatraining.beseda.webproject.model.exception.EntityException.EntityLogicException;

public class IllegalRouteNameException  extends EntityLogicException {
    public IllegalRouteNameException() {
    }

    public IllegalRouteNameException(String message) {
        super(message);
    }
}
