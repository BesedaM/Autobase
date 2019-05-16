package by.epam.javatraining.beseda.webproject.model.exception.EntityException.address;

import by.epam.javatraining.beseda.webproject.model.exception.EntityException.EntityLogicException;

public class IllegalCountryNameException  extends EntityLogicException {

    public IllegalCountryNameException() {
    }

    public IllegalCountryNameException(String message) {
        super(message);
    }
}
