package by.epam.javatraining.beseda.webproject.model.exception.DAOexception;

public class UnknownEnumConstantException extends DAOLogicException {
    public UnknownEnumConstantException() {
        super("Unknown constant value for enum type found");
    }

    public UnknownEnumConstantException(String message) {
        super(message);
    }
}
