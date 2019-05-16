package by.epam.javatraining.beseda.webproject.model.exception.EntityException.user;

import by.epam.javatraining.beseda.webproject.model.exception.EntityException.EntityLogicException;

public class IllegalUserPhoneNumberException extends EntityLogicException {

    public IllegalUserPhoneNumberException() {
    }

    public IllegalUserPhoneNumberException(String message) {
        super(message);
    }
}
