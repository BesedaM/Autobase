package by.epam.javatraining.beseda.webproject.model.exception.entityexception.car;

import by.epam.javatraining.beseda.webproject.model.exception.entityexception.EntityLogicException;

public class IllegalTruckCapacityException  extends EntityLogicException {

    public IllegalTruckCapacityException() {
    }

    public IllegalTruckCapacityException(String message) {
        super(message);
    }
}
