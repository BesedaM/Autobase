package by.epam.javatraining.beseda.webproject.entity.exception;

public class UserException extends EntityLogicException {

    public UserException() {
        super("User field assignment exception");
    }

    public UserException(String message) {
        super(message);
    }
}
