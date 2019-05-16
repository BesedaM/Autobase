package by.epam.javatraining.beseda.webproject.model.exception.EntityException.car;

import by.epam.javatraining.beseda.webproject.model.exception.EntityException.EntityLogicException;

public class IllegalCarModelException  extends EntityLogicException {
    public IllegalCarModelException() {
    }

    public IllegalCarModelException(String message) {
        super(message);
    }
}
