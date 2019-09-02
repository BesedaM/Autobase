package by.epam.javatraining.beseda.webproject.entity.exception.request;

import by.epam.javatraining.beseda.webproject.entity.exception.EntityLogicException;

public class IllegalRequestCommentException extends EntityLogicException {
    public IllegalRequestCommentException() {
    }

    public IllegalRequestCommentException(String message) {
        super(message);
    }
}
