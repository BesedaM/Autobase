package by.epam.javatraining.beseda.webproject.model.exception.EntityException;

import by.epam.javatraining.beseda.webproject.model.exception.AutobaseProjectException;

public class EntityLogicException extends AutobaseProjectException {

    public EntityLogicException() {
    }

    public EntityLogicException(String message) {
        super(message);
    }

    public EntityLogicException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityLogicException(Throwable cause) {
        super(cause);
    }

    public EntityLogicException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
