package by.epam.javatraining.beseda.webproject.model.exception.serviceexception;

public class ServiceTechnicalException extends ServiceLayerException {

    public ServiceTechnicalException() {
    }

    public ServiceTechnicalException(String message) {
        super(message);
    }

    public ServiceTechnicalException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceTechnicalException(Throwable cause) {
        super(cause);
    }

    public ServiceTechnicalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
