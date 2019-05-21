package by.epam.javatraining.beseda.webproject.model.exception.entityexception.address;

import by.epam.javatraining.beseda.webproject.model.exception.entityexception.EntityLogicException;

public class IllegalCountryNameException  extends EntityLogicException {

    public IllegalCountryNameException() {
    }

    public IllegalCountryNameException(String message) {
        super(message);
    }
}
