package by.epam.javatraining.beseda.webproject.entity.exception.user;

import by.epam.javatraining.beseda.webproject.entity.exception.EntityLogicException;

public class IllegalPasswordException extends EntityLogicException {
    public IllegalPasswordException() {
    }

    public IllegalPasswordException(String message) {
        super(message);
    }
}
