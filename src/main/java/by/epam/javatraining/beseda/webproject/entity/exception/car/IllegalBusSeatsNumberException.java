package by.epam.javatraining.beseda.webproject.entity.exception.car;

import by.epam.javatraining.beseda.webproject.entity.exception.EntityLogicException;

public class IllegalBusSeatsNumberException extends EntityLogicException {
    public IllegalBusSeatsNumberException() {
    }

    public IllegalBusSeatsNumberException(String message) {
        super(message);
    }
}
