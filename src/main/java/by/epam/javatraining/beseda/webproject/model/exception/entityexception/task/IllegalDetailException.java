package by.epam.javatraining.beseda.webproject.model.exception.entityexception.task;

import by.epam.javatraining.beseda.webproject.model.exception.entityexception.EntityLogicException;

public class IllegalDetailException  extends EntityLogicException {

    public IllegalDetailException() {
    }

    public IllegalDetailException(String message) {
        super(message);
    }
}
