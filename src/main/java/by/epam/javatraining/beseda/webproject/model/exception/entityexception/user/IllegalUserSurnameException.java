package by.epam.javatraining.beseda.webproject.model.exception.entityexception.user;

import by.epam.javatraining.beseda.webproject.model.exception.entityexception.EntityLogicException;

public class IllegalUserSurnameException extends EntityLogicException {
    public IllegalUserSurnameException() {
    }

    public IllegalUserSurnameException(String message) {
        super(message);
    }
}