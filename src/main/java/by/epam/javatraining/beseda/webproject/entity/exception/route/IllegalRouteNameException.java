package by.epam.javatraining.beseda.webproject.entity.exception.route;

import by.epam.javatraining.beseda.webproject.entity.exception.EntityLogicException;

public class IllegalRouteNameException  extends EntityLogicException {
    public IllegalRouteNameException() {
    }

    public IllegalRouteNameException(String message) {
        super(message);
    }
}
