package by.epam.javatraining.beseda.webproject.model.exception.entityexception.route;

import by.epam.javatraining.beseda.webproject.model.exception.entityexception.EntityLogicException;

public class IllegalRouteNameException  extends EntityLogicException {
    public IllegalRouteNameException() {
    }

    public IllegalRouteNameException(String message) {
        super(message);
    }
}
