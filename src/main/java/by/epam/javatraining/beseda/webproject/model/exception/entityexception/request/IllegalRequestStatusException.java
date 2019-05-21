package by.epam.javatraining.beseda.webproject.model.exception.entityexception.request;

import by.epam.javatraining.beseda.webproject.model.exception.entityexception.EntityLogicException;

public class IllegalRequestStatusException extends EntityLogicException {

    public IllegalRequestStatusException() {
    }

    public IllegalRequestStatusException(String message) {
        super(message);
    }
}
