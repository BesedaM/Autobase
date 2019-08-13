package by.epam.javatraining.beseda.webproject.model.service.entityservice;

import by.epam.javatraining.beseda.webproject.model.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.model.service.exception.ServiceLogicException;

import java.util.List;

/**
 * Interface, containing main methods for service classes
 *
 * @param <E> type parameter
 */
public interface EntityService<E> {

    /**
     * Returns all the entities of type
     *
     * @return all the entities of type
     * @throws ServiceLayerException
     */
    List<E> getAll() throws ServiceLayerException;

    /**
     * Returns entityservice with specified id
     *
     * @param id entityservice id
     * @return entityservice by id
     * @throws ServiceLayerException
     */
    E getEntityById(int id) throws ServiceLayerException;

    /**
     * Adds entityservice
     *
     * @param entity entityservice to add
     * @throws ServiceLayerException
     */
    void add(E entity) throws ServiceLayerException;

    /**
     * Updates entityservice
     *
     * @param entity entityservice to update
     * @throws ServiceLayerException
     */
    void update(E entity) throws ServiceLayerException;

    /**
     * Deletes entityservice
     *
     * @param id entities id
     */
    void delete(int id) throws ServiceLogicException;

    /**
     * Deletes entityservice
     *
     * @param entity entityservice to delete
     */
    void delete(E entity) throws ServiceLogicException;

}
