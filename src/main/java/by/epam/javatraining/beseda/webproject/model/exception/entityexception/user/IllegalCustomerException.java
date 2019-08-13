package by.epam.javatraining.beseda.webproject.model.exception.entityexception.user;

import by.epam.javatraining.beseda.webproject.model.exception.entityexception.EntityLogicException;

public class IllegalCustomerException extends EntityLogicException {

    public IllegalCustomerException() {
        super();
    }

    public IllegalCustomerException(String message) {
        super(message);
    }
}
