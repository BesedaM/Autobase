package by.epam.javatraining.beseda.webproject.entity.exception.user;

import by.epam.javatraining.beseda.webproject.entity.exception.EntityLogicException;

public class IllegalUserRoleException extends EntityLogicException {

    public IllegalUserRoleException() {
    }

    public IllegalUserRoleException(String message) {
        super(message);
    }
}
