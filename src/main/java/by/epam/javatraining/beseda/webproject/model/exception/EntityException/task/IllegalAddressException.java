package by.epam.javatraining.beseda.webproject.model.exception.EntityException.task;

import by.epam.javatraining.beseda.webproject.model.exception.EntityException.EntityLogicException;

public class IllegalAddressException extends EntityLogicException {

    public IllegalAddressException() {
    }

    public IllegalAddressException(String message) {
        super(message);
    }
}
