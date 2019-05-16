package by.epam.javatraining.beseda.webproject.model.exception.EntityException.address;

import by.epam.javatraining.beseda.webproject.model.exception.EntityException.EntityLogicException;

public class IllegalHouseNumberException extends EntityLogicException {

    public IllegalHouseNumberException() {
    }

    public IllegalHouseNumberException(String message) {
        super(message);
    }
}
