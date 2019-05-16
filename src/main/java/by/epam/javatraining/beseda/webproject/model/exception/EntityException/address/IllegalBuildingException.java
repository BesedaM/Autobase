package by.epam.javatraining.beseda.webproject.model.exception.EntityException.address;

import by.epam.javatraining.beseda.webproject.model.exception.EntityException.EntityLogicException;

public class IllegalBuildingException  extends EntityLogicException {
    public IllegalBuildingException() {
    }

    public IllegalBuildingException(String message) {
        super(message);
    }
}
