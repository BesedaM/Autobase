package by.epam.javatraining.beseda.webproject.model.exception.EntityException.request;

import by.epam.javatraining.beseda.webproject.model.exception.EntityException.EntityLogicException;

public class IllegalRequestStatusException extends EntityLogicException {

    public IllegalRequestStatusException() {
    }

    public IllegalRequestStatusException(String message) {
        super(message);
    }
}
