package by.epam.javatraining.beseda.webproject.model.exception.entityexception.car;

import by.epam.javatraining.beseda.webproject.model.exception.entityexception.EntityLogicException;

public class IllegalBusSeatsNumberException extends EntityLogicException {
    public IllegalBusSeatsNumberException() {
    }

    public IllegalBusSeatsNumberException(String message) {
        super(message);
    }
}
