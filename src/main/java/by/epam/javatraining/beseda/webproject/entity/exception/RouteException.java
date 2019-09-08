package by.epam.javatraining.beseda.webproject.entity.exception;

public class RouteException extends EntityLogicException {

    public RouteException() {
        super("Route field assignment exception");
    }

    public RouteException(String message) {
        super(message);
    }

}
