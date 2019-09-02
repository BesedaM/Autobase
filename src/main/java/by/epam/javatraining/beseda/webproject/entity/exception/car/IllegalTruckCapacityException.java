package by.epam.javatraining.beseda.webproject.entity.exception.car;

import by.epam.javatraining.beseda.webproject.entity.exception.EntityLogicException;

public class IllegalTruckCapacityException  extends EntityLogicException {

    public IllegalTruckCapacityException() {
    }

    public IllegalTruckCapacityException(String message) {
        super(message);
    }
}
