package by.epam.javatraining.beseda.webproject.entity.exception.address;

import by.epam.javatraining.beseda.webproject.entity.exception.EntityLogicException;

public class IllegalDistrictNameException  extends EntityLogicException {
    public IllegalDistrictNameException() {
    }

    public IllegalDistrictNameException(String message) {
        super(message);
    }
}
