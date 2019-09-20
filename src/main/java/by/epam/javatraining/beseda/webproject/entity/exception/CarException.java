package by.epam.javatraining.beseda.webproject.entity.exception;

public class CarException extends EntityLogicException {

	public CarException() {
		super("Car field assignment exception");
	}

	public CarException(String message) {
		super(message);
	}
}