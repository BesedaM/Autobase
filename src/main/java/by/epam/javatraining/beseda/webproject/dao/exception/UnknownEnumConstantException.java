package by.epam.javatraining.beseda.webproject.dao.exception;

@SuppressWarnings("serial")
public class UnknownEnumConstantException extends DAOLogicException {
    public UnknownEnumConstantException() {
        super("Unknown constant value for enum type found");
    }

    public UnknownEnumConstantException(String message) {
        super(message);
    }
}
