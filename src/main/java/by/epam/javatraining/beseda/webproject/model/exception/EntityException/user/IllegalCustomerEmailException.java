package by.epam.javatraining.beseda.webproject.model.exception.EntityException.user;

import by.epam.javatraining.beseda.webproject.model.exception.EntityException.EntityLogicException;

public class IllegalCustomerEmailException extends EntityLogicException {
    public IllegalCustomerEmailException() {
    }

    public IllegalCustomerEmailException(String message) {
        super(message);
    }
}
