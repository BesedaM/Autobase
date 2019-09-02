package by.epam.javatraining.beseda.webproject.entity.exception.user;

import by.epam.javatraining.beseda.webproject.entity.exception.EntityLogicException;

public class IllegalCustomerException extends EntityLogicException {

    public IllegalCustomerException() {
        super();
    }

    public IllegalCustomerException(String message) {
        super(message);
    }
}
