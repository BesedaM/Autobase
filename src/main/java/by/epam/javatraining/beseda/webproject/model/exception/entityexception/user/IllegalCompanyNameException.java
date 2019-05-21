package by.epam.javatraining.beseda.webproject.model.exception.entityexception.user;

import by.epam.javatraining.beseda.webproject.model.exception.entityexception.EntityLogicException;

public class IllegalCompanyNameException extends EntityLogicException {
    public IllegalCompanyNameException() {
    }

    public IllegalCompanyNameException(String message) {
        super(message);
    }
}
