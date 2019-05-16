package by.epam.javatraining.beseda.webproject.model.exception.EntityException.user;

import by.epam.javatraining.beseda.webproject.model.exception.EntityException.EntityLogicException;

public class IllegalUserNameException  extends EntityLogicException {

    public IllegalUserNameException() {
    }

    public IllegalUserNameException(String message) {
        super(message);
    }
}
