package by.epam.javatraining.beseda.webproject.model.exception.entityexception.request;

import by.epam.javatraining.beseda.webproject.model.exception.entityexception.EntityLogicException;

public class IllegalDateException extends EntityLogicException {

    public IllegalDateException() {
    }

    public IllegalDateException(String message) {
        super(message);
    }
}
