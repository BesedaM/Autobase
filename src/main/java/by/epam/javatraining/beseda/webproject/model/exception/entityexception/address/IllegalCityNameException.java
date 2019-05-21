package by.epam.javatraining.beseda.webproject.model.exception.entityexception.address;

import by.epam.javatraining.beseda.webproject.model.exception.entityexception.EntityLogicException;

public class IllegalCityNameException  extends EntityLogicException {

    public IllegalCityNameException() {
    }

    public IllegalCityNameException(String message) {
        super(message);
    }
}
