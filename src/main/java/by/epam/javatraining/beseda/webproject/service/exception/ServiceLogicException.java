package by.epam.javatraining.beseda.webproject.service.exception;

/**
 * General class for logic exceptions in ServiceLayer.
 * 
 * @author Maryia_Biaseda
 *
 */
public class ServiceLogicException extends ServiceLayerException {

	public ServiceLogicException() {
	}

	public ServiceLogicException(String message) {
		super(message);
	}

	public ServiceLogicException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceLogicException(Throwable cause) {
		super(cause);
	}

	public ServiceLogicException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}