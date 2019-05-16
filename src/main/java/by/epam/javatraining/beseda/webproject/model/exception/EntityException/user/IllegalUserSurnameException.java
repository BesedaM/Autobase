package by.epam.javatraining.beseda.webproject.model.exception.EntityException.user;

import by.epam.javatraining.beseda.webproject.model.exception.EntityException.EntityLogicException;

public class IllegalUserSurnameException extends EntityLogicException {
    public IllegalUserSurnameException() {
    }

    public IllegalUserSurnameException(String message) {
        super(message);
    }
}
