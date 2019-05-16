package by.epam.javatraining.beseda.webproject.model.exception.EntityException.user;

import by.epam.javatraining.beseda.webproject.model.exception.EntityException.EntityLogicException;

public class IllegalCustomerTypeException extends EntityLogicException {

    public IllegalCustomerTypeException() {
    }

    public IllegalCustomerTypeException(String message) {
        super(message);
    }
}
