package by.epam.javatraining.beseda.webproject.model.exception.entityexception.address;

import by.epam.javatraining.beseda.webproject.model.exception.entityexception.EntityLogicException;

public class IllegalHouseNumberException extends EntityLogicException {

    public IllegalHouseNumberException() {
    }

    public IllegalHouseNumberException(String message) {
        super(message);
    }
}
