package by.epam.javatraining.beseda.webproject.model.exception.EntityException.address;

import by.epam.javatraining.beseda.webproject.model.exception.EntityException.EntityLogicException;

public class IllegalDistrictNameException  extends EntityLogicException {
    public IllegalDistrictNameException() {
    }

    public IllegalDistrictNameException(String message) {
        super(message);
    }
}
