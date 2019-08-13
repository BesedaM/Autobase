package by.epam.javatraining.beseda.webproject.model.service.entitybuilder;

import by.epam.javatraining.beseda.webproject.model.entity.BaseEntity;
import by.epam.javatraining.beseda.webproject.model.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.model.service.factory.ServiceDependenceFactory;
import by.epam.javatraining.beseda.webproject.model.service.factory.ServiceEntityFactory;

/**
 * Declares method for building complex objects with it's small one's
 * @param <E> type parameter
 */
public abstract class EntityBuider<E extends BaseEntity> {

    protected static ServiceEntityFactory serviceEntityFactory = ServiceEntityFactory.getFactory();
    protected static ServiceDependenceFactory serviceDependenceFactory = ServiceDependenceFactory.getFactory();

    /**
     * Returns the whole entityservice with 'tree' of inner objects by entityservice's id
     *
     * @param entityId - entityservice id
     * @return entityservice of given type
     * @throws ServiceLayerException
     */
    abstract E getEntity(int entityId) throws ServiceLayerException;

}