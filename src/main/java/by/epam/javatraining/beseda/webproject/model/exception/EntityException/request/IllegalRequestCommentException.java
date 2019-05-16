package by.epam.javatraining.beseda.webproject.model.exception.EntityException.request;

import by.epam.javatraining.beseda.webproject.model.exception.EntityException.EntityLogicException;

public class IllegalRequestCommentException extends EntityLogicException {
    public IllegalRequestCommentException() {
    }

    public IllegalRequestCommentException(String message) {
        super(message);
    }
}
