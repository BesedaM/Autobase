package by.epam.javatraining.beseda.webproject.entity.exception;

public class AddressException extends EntityLogicException{

    public AddressException() {
        super("Address field assignment exception");
    }

    public AddressException(String message) {
        super(message);
    }
}
