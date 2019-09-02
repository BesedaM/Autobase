package by.epam.javatraining.beseda.webproject.entity.exception.request;

import by.epam.javatraining.beseda.webproject.entity.exception.EntityLogicException;

public class IllegalRequestStatusException extends EntityLogicException {

    public IllegalRequestStatusException() {
    }

    public IllegalRequestStatusException(String message) {
        super(message);
    }
}
