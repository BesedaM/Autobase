package by.epam.javatraining.beseda.webproject.dao.exception;

@SuppressWarnings("serial")
public class DAOLogicException extends DAOLayerException {

    public DAOLogicException() {
    }

    public DAOLogicException(String message) {
        super(message);
    }

    public DAOLogicException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOLogicException(Throwable cause) {
        super(cause);
    }

    public DAOLogicException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
