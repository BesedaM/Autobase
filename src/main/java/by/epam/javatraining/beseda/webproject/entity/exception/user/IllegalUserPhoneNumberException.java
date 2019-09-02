package by.epam.javatraining.beseda.webproject.entity.exception.user;

import by.epam.javatraining.beseda.webproject.entity.exception.EntityLogicException;

public class IllegalUserPhoneNumberException extends EntityLogicException {

    public IllegalUserPhoneNumberException() {
    }

    public IllegalUserPhoneNumberException(String message) {
        super(message);
    }
}
