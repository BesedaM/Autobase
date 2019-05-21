package by.epam.javatraining.beseda.webproject.model.exception.entityexception.task;

import by.epam.javatraining.beseda.webproject.model.exception.entityexception.EntityLogicException;

public class IllegalAddressException extends EntityLogicException {

    public IllegalAddressException() {
    }

    public IllegalAddressException(String message) {
        super(message);
    }
}
