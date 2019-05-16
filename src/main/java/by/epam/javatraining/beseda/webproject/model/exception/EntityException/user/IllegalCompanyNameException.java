package by.epam.javatraining.beseda.webproject.model.exception.EntityException.user;

import by.epam.javatraining.beseda.webproject.model.exception.EntityException.EntityLogicException;

public class IllegalCompanyNameException extends EntityLogicException {
    public IllegalCompanyNameException() {
    }

    public IllegalCompanyNameException(String message) {
        super(message);
    }
}
