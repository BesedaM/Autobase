package by.epam.javatraining.beseda.webproject.model.exception.entityexception.address;

import by.epam.javatraining.beseda.webproject.model.exception.entityexception.EntityLogicException;

public class IllegalStreetNameException extends EntityLogicException {

    public IllegalStreetNameException() {
    }

    public IllegalStreetNameException(String message) {
        super(message);
    }
}
