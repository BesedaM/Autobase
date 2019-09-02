package by.epam.javatraining.beseda.webproject.entity.exception.address;

import by.epam.javatraining.beseda.webproject.entity.exception.EntityLogicException;

public class IllegalCityNameException  extends EntityLogicException {

    public IllegalCityNameException() {
    }

    public IllegalCityNameException(String message) {
        super(message);
    }
}
