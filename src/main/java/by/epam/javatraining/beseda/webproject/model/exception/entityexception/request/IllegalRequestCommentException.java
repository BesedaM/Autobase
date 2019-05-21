package by.epam.javatraining.beseda.webproject.model.exception.entityexception.request;

import by.epam.javatraining.beseda.webproject.model.exception.entityexception.EntityLogicException;

public class IllegalRequestCommentException extends EntityLogicException {
    public IllegalRequestCommentException() {
    }

    public IllegalRequestCommentException(String message) {
        super(message);
    }
}
