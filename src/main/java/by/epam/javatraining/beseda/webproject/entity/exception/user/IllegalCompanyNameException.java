package by.epam.javatraining.beseda.webproject.entity.exception.user;

import by.epam.javatraining.beseda.webproject.entity.exception.EntityLogicException;

public class IllegalCompanyNameException extends EntityLogicException {
    public IllegalCompanyNameException() {
    }

    public IllegalCompanyNameException(String message) {
        super(message);
    }
}
