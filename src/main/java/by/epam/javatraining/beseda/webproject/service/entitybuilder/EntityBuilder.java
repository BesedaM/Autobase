package by.epam.javatraining.beseda.webproject.service.entitybuilder;

import by.epam.javatraining.beseda.webproject.entity.EntityBase;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.service.entityservice.ServiceEntityFactory;

/**
 * Declares method for building complex objects with it's small one's
 * @param <E> type parameter
 */
public abstract class EntityBuilder<E extends EntityBase> {

    protected static final ServiceEntityFactory serviceEntityFactory = ServiceEntityFactory.getFactory();

        
    /**
     * Returns the whole entityservice with 'tree' of inner objects by entityservice's id
     *
     * @param entityId - entityservice id
     * @return entityservice of given type
     * @throws ServiceLayerException
     */
    public abstract E getEntity(int entityId) throws ServiceLayerException;

}