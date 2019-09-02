package by.epam.javatraining.beseda.webproject.entity.exception.user;

import by.epam.javatraining.beseda.webproject.entity.exception.EntityLogicException;

public class IllegalCustomerEmailException extends EntityLogicException {
    public IllegalCustomerEmailException() {
    }

    public IllegalCustomerEmailException(String message) {
        super(message);
    }
}
