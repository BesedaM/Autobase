package by.epam.javatraining.beseda.webproject.model.exception.entityexception.user;

import by.epam.javatraining.beseda.webproject.model.exception.entityexception.EntityLogicException;

public class IllegalCustomerEmailException extends EntityLogicException {
    public IllegalCustomerEmailException() {
    }

    public IllegalCustomerEmailException(String message) {
        super(message);
    }
}
