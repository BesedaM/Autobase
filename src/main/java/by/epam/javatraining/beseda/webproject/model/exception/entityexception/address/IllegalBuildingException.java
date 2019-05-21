package by.epam.javatraining.beseda.webproject.model.exception.entityexception.address;

import by.epam.javatraining.beseda.webproject.model.exception.entityexception.EntityLogicException;

public class IllegalBuildingException  extends EntityLogicException {
    public IllegalBuildingException() {
    }

    public IllegalBuildingException(String message) {
        super(message);
    }
}
