package by.epam.javatraining.beseda.webproject.service.exception;

/**
 * General class for technical exceptions in ServiceLayer.
 * 
 * @author Maryia_Biaseda
 *
 */
public class ServiceTechnicalException extends ServiceLayerException {

	public ServiceTechnicalException() {
	}

	public ServiceTechnicalException(String message) {
		super(message);
	}

	public ServiceTechnicalException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceTechnicalException(Throwable cause) {
		super(cause);
	}

	public ServiceTechnicalException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
