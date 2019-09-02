package by.epam.javatraining.beseda.webproject.entity.exception.address;

import by.epam.javatraining.beseda.webproject.entity.exception.EntityLogicException;

public class IllegalCountryNameException  extends EntityLogicException {

    public IllegalCountryNameException() {
    }

    public IllegalCountryNameException(String message) {
        super(message);
    }
}
