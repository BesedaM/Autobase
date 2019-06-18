package by.epam.javatraining.beseda.webproject.model.service.entity;

import by.epam.javatraining.beseda.webproject.model.exception.serviceexception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.model.exception.serviceexception.ServiceLogicException;

import java.util.List;

/**
 * Interface, containing main methods for service classes
 *
 * @param <E> type parameter
 */
public interface Service<E> {

    /**
     * Returns all the entities of type
     *
     * @return all the entities of type
     * @throws ServiceLayerException
     */
    List<E> getAll() throws ServiceLayerException;

    /**
     * Returns entity with specified id
     *
     * @param id entity id
     * @return entity by id
     * @throws ServiceLayerException
     */
    E getEntityById(int id) throws ServiceLayerException;

    /**
     * Adds entity
     *
     * @param entity entity to add
     * @throws ServiceLayerException
     */
    void add(E entity) throws ServiceLayerException;

    /**
     * Updates entity
     *
     * @param entity entity to update
     * @throws ServiceLayerException
     */
    void update(E entity) throws ServiceLayerException;

    /**
     * Deletes entity
     *
     * @param id entities id
     */
    void delete(int id) throws ServiceLogicException;

    /**
     * Deletes entity
     *
     * @param entity entity to delete
     */
    void delete(E entity) throws ServiceLogicException;

}
