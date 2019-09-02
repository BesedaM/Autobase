package by.epam.javatraining.beseda.webproject.entity.exception.user;

import by.epam.javatraining.beseda.webproject.entity.exception.EntityLogicException;

public class IllegalUserSurnameException extends EntityLogicException {
    public IllegalUserSurnameException() {
    }

    public IllegalUserSurnameException(String message) {
        super(message);
    }
}
