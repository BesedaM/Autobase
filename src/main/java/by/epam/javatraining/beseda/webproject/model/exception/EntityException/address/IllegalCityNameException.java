package by.epam.javatraining.beseda.webproject.model.exception.EntityException.address;

import by.epam.javatraining.beseda.webproject.model.exception.EntityException.EntityLogicException;

public class IllegalCityNameException  extends EntityLogicException {

    public IllegalCityNameException() {
    }

    public IllegalCityNameException(String message) {
        super(message);
    }
}
