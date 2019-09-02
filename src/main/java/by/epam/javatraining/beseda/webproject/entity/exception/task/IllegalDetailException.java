package by.epam.javatraining.beseda.webproject.entity.exception.task;

import by.epam.javatraining.beseda.webproject.entity.exception.EntityLogicException;

public class IllegalDetailException  extends EntityLogicException {

    public IllegalDetailException() {
    }

    public IllegalDetailException(String message) {
        super(message);
    }
}
