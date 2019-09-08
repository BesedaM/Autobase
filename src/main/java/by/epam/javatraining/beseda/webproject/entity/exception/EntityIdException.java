package by.epam.javatraining.beseda.webproject.entity.exception;

public class EntityIdException extends EntityLogicException{

    public EntityIdException() {
        super("Entity id value assignment exception");
    }

    public EntityIdException(String message) {
        super(message);
    }
}
