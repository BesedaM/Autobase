package by.epam.javatraining.beseda.webproject.service.entityservice;

import java.util.List;

import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;

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
    List<E> getAll();

    /**
     * Returns entity with specified id.
     *
     * @param id entity id
     * @return entity by id
     * @throws ServiceLayerException
     */
    E getEntityById(int id);


    /**
     * Returns entity list according to the list of entities id.
     *
     * @param idArr array of entities id
     * @return List of entities
     * @throws ServiceLayerException
     */
    List<E> getEntitiesByIdList(int[] idArr);

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
    void delete(int id);

    /**
     * Deletes entity.
     *
     * @param entity entity to delete
     */
    void delete(E entity);

}