package by.epam.javatraining.beseda.webproject.model.exception.entityexception.address;

import by.epam.javatraining.beseda.webproject.model.exception.entityexception.EntityLogicException;

public class IllegalDistrictNameException  extends EntityLogicException {
    public IllegalDistrictNameException() {
    }

    public IllegalDistrictNameException(String message) {
        super(message);
    }
}
