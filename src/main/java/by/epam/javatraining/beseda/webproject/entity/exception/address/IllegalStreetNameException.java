package by.epam.javatraining.beseda.webproject.entity.exception.address;

import by.epam.javatraining.beseda.webproject.entity.exception.EntityLogicException;

public class IllegalStreetNameException extends EntityLogicException {

    public IllegalStreetNameException() {
    }

    public IllegalStreetNameException(String message) {
        super(message);
    }
}
