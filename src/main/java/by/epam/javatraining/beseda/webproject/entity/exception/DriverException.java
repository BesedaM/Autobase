package by.epam.javatraining.beseda.webproject.entity.exception;

public class DriverException extends EntityLogicException{

    public DriverException() {
        super("Driver field assignment exception");
    }

    public DriverException(String message) {
        super(message);
    }
}
