package by.epam.javatraining.beseda.webproject.entity.exception;

public class CustomerException extends EntityLogicException {

	public CustomerException() {
		super("Customer field assignment exception");
	}

	public CustomerException(String message) {
		super(message);
	}
}