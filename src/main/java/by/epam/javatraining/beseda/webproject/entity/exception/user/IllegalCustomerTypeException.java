package by.epam.javatraining.beseda.webproject.entity.exception.user;

import by.epam.javatraining.beseda.webproject.entity.exception.EntityLogicException;

public class IllegalCustomerTypeException extends EntityLogicException {

    public IllegalCustomerTypeException() {
    }

    public IllegalCustomerTypeException(String message) {
        super(message);
    }
}
