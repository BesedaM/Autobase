package by.epam.javatraining.beseda.webproject.model.exception.daoexception;

public class NotEnoughArgumentsException extends DAOLogicException {
    public NotEnoughArgumentsException() {
        super("Some of the arguments passed to method are null");
    }

    public NotEnoughArgumentsException(String message) {
        super(message);
    }
}
