package by.epam.javatraining.beseda.webproject.model.exception.EntityException.task;

import by.epam.javatraining.beseda.webproject.model.exception.EntityException.EntityLogicException;

public class IllegalDetailException  extends EntityLogicException {

    public IllegalDetailException() {
    }

    public IllegalDetailException(String message) {
        super(message);
    }
}
