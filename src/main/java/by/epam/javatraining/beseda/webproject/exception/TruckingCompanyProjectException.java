package by.epam.javatraining.beseda.webproject.exception;

public class TruckingCompanyProjectException extends Exception{

    public TruckingCompanyProjectException() {
    }

    public TruckingCompanyProjectException(String message) {
        super(message);
    }

    public TruckingCompanyProjectException(String message, Throwable cause) {
        super(message, cause);
    }

    public TruckingCompanyProjectException(Throwable cause) {
        super(cause);
    }

    public TruckingCompanyProjectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
