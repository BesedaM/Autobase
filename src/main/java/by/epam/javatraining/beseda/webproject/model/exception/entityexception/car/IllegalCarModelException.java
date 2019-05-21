package by.epam.javatraining.beseda.webproject.model.exception.entityexception.car;

import by.epam.javatraining.beseda.webproject.model.exception.entityexception.EntityLogicException;

public class IllegalCarModelException  extends EntityLogicException {
    public IllegalCarModelException() {
    }

    public IllegalCarModelException(String message) {
        super(message);
    }
}
