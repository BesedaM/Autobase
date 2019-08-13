package by.epam.javatraining.beseda.webproject.model.service.exception;

import by.epam.javatraining.beseda.webproject.model.exception.AutobaseProjectException;

public class ServiceLayerException extends AutobaseProjectException {

    public ServiceLayerException() {
    }

    public ServiceLayerException(String message) {
        super(message);
    }

    public ServiceLayerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceLayerException(Throwable cause) {
        super(cause);
    }

    public ServiceLayerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}