package by.epam.javatraining.beseda.webproject.entity.exception.user;

import by.epam.javatraining.beseda.webproject.entity.exception.EntityLogicException;

public class IllegalUserNameException  extends EntityLogicException {

    public IllegalUserNameException() {
    }

    public IllegalUserNameException(String message) {
        super(message);
    }
}
