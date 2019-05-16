package by.epam.javatraining.beseda.webproject.model.exception.EntityException.address;

import by.epam.javatraining.beseda.webproject.model.exception.EntityException.EntityLogicException;

public class IllegalStreetNameException extends EntityLogicException {

    public IllegalStreetNameException() {
    }

    public IllegalStreetNameException(String message) {
        super(message);
    }
}
