package by.epam.javatraining.beseda.webproject.dao.exception;

@SuppressWarnings("serial")
public class CarTypeNotPresentException extends DAOLogicException {

    public CarTypeNotPresentException() {
        super("Unknown car type found");
    }

    public CarTypeNotPresentException(String message) {
        super(message);
    }
}
