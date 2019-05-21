package by.epam.javatraining.beseda.webproject.model.exception.entityexception.user;

import by.epam.javatraining.beseda.webproject.model.exception.entityexception.EntityLogicException;

public class IllegalCustomerTypeException extends EntityLogicException {

    public IllegalCustomerTypeException() {
    }

    public IllegalCustomerTypeException(String message) {
        super(message);
    }
}
