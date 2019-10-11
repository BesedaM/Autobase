package by.epam.javatraining.beseda.webproject.entity.exception;

public class EnumElementException extends EntityLogicException {

	public EnumElementException() {
		super("Enum field assignment exception");
	}

	public EnumElementException(String message) {
		super(message);
	}
}
