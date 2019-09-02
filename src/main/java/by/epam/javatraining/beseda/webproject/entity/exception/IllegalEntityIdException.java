package by.epam.javatraining.beseda.webproject.entity.exception;

public class IllegalEntityIdException extends EntityLogicException{
    public IllegalEntityIdException() {
    }

    public IllegalEntityIdException(String message) {
        super(message);
    }
}
