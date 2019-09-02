package by.epam.javatraining.beseda.webproject.entity.exception.address;

import by.epam.javatraining.beseda.webproject.entity.exception.EntityLogicException;

public class IllegalBuildingException  extends EntityLogicException {
    public IllegalBuildingException() {
    }

    public IllegalBuildingException(String message) {
        super(message);
    }
}
