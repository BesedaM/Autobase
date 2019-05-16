package by.epam.javatraining.beseda.webproject.model.exception.EntityException.user;

import by.epam.javatraining.beseda.webproject.model.exception.EntityException.EntityLogicException;

public class IllegalUserRoleException extends EntityLogicException {

    public IllegalUserRoleException() {
    }

    public IllegalUserRoleException(String message) {
        super(message);
    }
}
