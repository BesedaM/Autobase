package by.epam.javatraining.beseda.webproject.service.entityservice;

import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLogicException;

import java.util.List;

/**
 * Interface, containing main methods for service classes.
 *
 * @param <E> type parameter
 */
public interface EntityService<E> {

    /**
     * Returns all the entities of type.
     *
     * @return all the entities of type
     * @throws ServiceLayerException
     */
    List<E> getAll() throws ServiceLayerException;

    /**
     * Returns entity with specified id.
     *
     * @param id entity id
     * @return entity by id
     * @throws ServiceLayerException
     */
    E getEntityById(int id) throws ServiceLayerException;


    /**
     * Returns entity list according to the list of entities id.
     *
     * @param idArr array of entities id
     * @return List of entities
     * @throws ServiceLayerException
     */
    List<E> getEntitiesByIdList(int[] idArr) throws ServiceLayerException;

    /**
     * Adds entity.
     *
     * @param entity entity to add
     * @throws ServiceLayerException
     */
    void add(E entity) throws ServiceLayerException;

    /**
     * Updates entity.
     *
     * @param entity entity to update
     * @throws ServiceLayerException
     */
    void update(E entity) throws ServiceLayerException;

    /**
     * Deletes entity.
     *
     * @param id entities id
     */
    void delete(int id) throws ServiceLogicException;

    /**
     * Deletes entity.
     *
     * @param entity entity to delete
     */
    void delete(E entity) throws ServiceLogicException;

}