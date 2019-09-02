package by.epam.javatraining.beseda.webproject.entity.exception.request;

import by.epam.javatraining.beseda.webproject.entity.exception.EntityLogicException;

public class IllegalDateException extends EntityLogicException {

    public IllegalDateException() {
    }

    public IllegalDateException(String message) {
        super(message);
    }
}
