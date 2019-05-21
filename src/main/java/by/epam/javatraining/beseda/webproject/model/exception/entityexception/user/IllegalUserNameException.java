package by.epam.javatraining.beseda.webproject.model.exception.entityexception.user;

import by.epam.javatraining.beseda.webproject.model.exception.entityexception.EntityLogicException;

public class IllegalUserNameException  extends EntityLogicException {

    public IllegalUserNameException() {
    }

    public IllegalUserNameException(String message) {
        super(message);
    }
}
