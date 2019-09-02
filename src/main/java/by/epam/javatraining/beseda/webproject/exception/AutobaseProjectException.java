package by.epam.javatraining.beseda.webproject.exception;

public class AutobaseProjectException extends Exception{

    public AutobaseProjectException() {
    }

    public AutobaseProjectException(String message) {
        super(message);
    }

    public AutobaseProjectException(String message, Throwable cause) {
        super(message, cause);
    }

    public AutobaseProjectException(Throwable cause) {
        super(cause);
    }

    public AutobaseProjectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
