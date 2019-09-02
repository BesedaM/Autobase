package by.epam.javatraining.beseda.webproject.entity.exception.task;

import by.epam.javatraining.beseda.webproject.entity.exception.EntityLogicException;

public class IllegalAddressException extends EntityLogicException {

    public IllegalAddressException() {
    }

    public IllegalAddressException(String message) {
        super(message);
    }
}
