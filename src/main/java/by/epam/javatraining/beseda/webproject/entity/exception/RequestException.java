package by.epam.javatraining.beseda.webproject.entity.exception;

public class RequestException extends EntityLogicException {

    public RequestException() {
        super("Request field assignment exception");
    }

    public RequestException(String message) {
        super(message);
    }
}
