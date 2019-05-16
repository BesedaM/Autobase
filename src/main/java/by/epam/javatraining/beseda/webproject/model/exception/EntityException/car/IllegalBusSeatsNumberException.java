package by.epam.javatraining.beseda.webproject.model.exception.EntityException.car;

import by.epam.javatraining.beseda.webproject.model.exception.EntityException.EntityLogicException;

public class IllegalBusSeatsNumberException extends EntityLogicException {
    public IllegalBusSeatsNumberException() {
    }

    public IllegalBusSeatsNumberException(String message) {
        super(message);
    }
}
