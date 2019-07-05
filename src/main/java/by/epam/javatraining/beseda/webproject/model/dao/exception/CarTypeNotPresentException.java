package by.epam.javatraining.beseda.webproject.model.dao.exception;

public class CarTypeNotPresentException extends DAOLogicException {

    public CarTypeNotPresentException() {
        super("Unknown car type found");
    }

    public CarTypeNotPresentException(String message) {
        super(message);
    }
}
