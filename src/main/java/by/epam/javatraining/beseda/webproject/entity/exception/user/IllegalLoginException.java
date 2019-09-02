package by.epam.javatraining.beseda.webproject.entity.exception.user;

import by.epam.javatraining.beseda.webproject.entity.exception.EntityLogicException;

public class IllegalLoginException extends EntityLogicException {
    public IllegalLoginException() {
    }

    public IllegalLoginException(String message) {
        super(message);
    }
}
