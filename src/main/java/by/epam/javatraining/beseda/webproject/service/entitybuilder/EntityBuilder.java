package by.epam.javatraining.beseda.webproject.service.entitybuilder;

import by.epam.javatraining.beseda.webproject.entity.EntityBase;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;

/**
 * Declares method for building complex objects with it's small one's
 * @param <E> type parameter
 */
public interface EntityBuilder<E extends EntityBase> {
        
    /**
     * Returns the whole entityservice with 'tree' of inner objects by entityservice's id
     *
     * @param entityId - entityservice id
     * @return entityservice of given type
     * @throws ServiceLayerException
     */
    public abstract E getEntity(int entityId) throws ServiceLayerException;

}