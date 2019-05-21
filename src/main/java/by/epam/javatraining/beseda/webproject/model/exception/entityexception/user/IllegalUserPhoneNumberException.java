package by.epam.javatraining.beseda.webproject.model.exception.entityexception.user;

import by.epam.javatraining.beseda.webproject.model.exception.entityexception.EntityLogicException;

public class IllegalUserPhoneNumberException extends EntityLogicException {

    public IllegalUserPhoneNumberException() {
    }

    public IllegalUserPhoneNumberException(String message) {
        super(message);
    }
}
