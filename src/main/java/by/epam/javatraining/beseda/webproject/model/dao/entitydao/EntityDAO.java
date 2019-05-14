package by.epam.javatraining.beseda.webproject.model.dao.entitydao;

import by.epam.javatraining.beseda.webproject.model.exception.DAOexception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.model.exception.DAOexception.DAOTechnicalException;

import java.util.List;

/**
 * Contains methode declarations for entity DAO
 *
 * @param <E>
 */
public interface EntityDAO<E> {

    /**
     * Method for getting all the data from the specified table in the database
     *
     * @return list of E type objects
     * @throws DAOLayerException
     */
    public abstract List<E> getAll() throws DAOLayerException;

    /**
     * Method for finding the specified entity by id
     *
     * @param id entity id in database
     * @return the specified object or null if no object was found
     * @throws DAOTechnicalException
     */
    public abstract E findEntityById(int id) throws DAOLayerException;

    /**
     *Deletes object specified by id from database
     *
     * @param id entity id
     */
    public void delete(int id);

    /**
     * Deletes specified object from database
     *
     * @param entity the object to delete
     */
    public void delete(E entity);

    /**
     * Method for adding object to database
     *
     * @param entity the object to add
     * @return database-generated entity id
     * @throws DAOLayerException
     */
    public abstract int add(E entity) throws DAOLayerException;

    /**
     * Updates data of specified object
     *
     * @param entity the object to update
     * @throws DAOLayerException
     */
    public abstract void update(E entity) throws DAOLayerException;

    /**
     * Method for closing DAO connection
     */
    public void closeConnection();


}
