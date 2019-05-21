package by.epam.javatraining.beseda.webproject.model.exception.entityexception.user;

import by.epam.javatraining.beseda.webproject.model.exception.entityexception.EntityLogicException;

public class IllegalUserRoleException extends EntityLogicException {

    public IllegalUserRoleException() {
    }

    public IllegalUserRoleException(String message) {
        super(message);
    }
}
