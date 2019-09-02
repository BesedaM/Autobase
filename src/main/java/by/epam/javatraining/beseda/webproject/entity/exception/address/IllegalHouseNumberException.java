package by.epam.javatraining.beseda.webproject.entity.exception.address;

import by.epam.javatraining.beseda.webproject.entity.exception.EntityLogicException;

public class IllegalHouseNumberException extends EntityLogicException {

    public IllegalHouseNumberException() {
    }

    public IllegalHouseNumberException(String message) {
        super(message);
    }
}
