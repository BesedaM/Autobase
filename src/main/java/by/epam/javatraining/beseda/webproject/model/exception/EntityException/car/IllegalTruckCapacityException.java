package by.epam.javatraining.beseda.webproject.model.exception.EntityException.car;

import by.epam.javatraining.beseda.webproject.model.exception.EntityException.EntityLogicException;

public class IllegalTruckCapacityException  extends EntityLogicException {

    public IllegalTruckCapacityException() {
    }

    public IllegalTruckCapacityException(String message) {
        super(message);
    }
}
