package by.epam.javatraining.beseda.webproject.entity.exception.car;

import by.epam.javatraining.beseda.webproject.entity.exception.EntityLogicException;

public class IllegalCarModelException  extends EntityLogicException {
    public IllegalCarModelException() {
    }

    public IllegalCarModelException(String message) {
        super(message);
    }
}
