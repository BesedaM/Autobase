package by.epam.javatraining.beseda.webproject.dao.exception;

@SuppressWarnings("serial")
public class DAOTechnicalException extends DAOLayerException {

    public DAOTechnicalException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOTechnicalException(Throwable cause) {
        super(cause);
    }

    public DAOTechnicalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
