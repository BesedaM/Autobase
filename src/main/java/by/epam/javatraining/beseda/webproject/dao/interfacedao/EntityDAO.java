package by.epam.javatraining.beseda.webproject.dao.interfacedao;

import java.util.List;

import by.epam.javatraining.beseda.webproject.dao.exception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOTechnicalException;

/**
 * Contains method declarations for entity DAO.
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
	List<E> getAll();

	/**
	 * Method for finding the specified entity by id.
	 *
	 * @param id entity id in database
	 * @return the specified object or null if no object was found
	 * @throws DAOTechnicalException
	 */
	E getEntityById(int id);

	/**
	 * Returns entities list, consisting of entities with id values from idArr.
	 *
	 * @param idArr array with id values
	 * @return entities list
	 * @throws DAOLayerException
	 */
	List<E> getEntitiesByIdList(int[] idArr);

	/**
	 * Deletes object specified by id from database.
	 *
	 * @param id entity id
	 */
	void delete(int id);

	/**
	 * Deletes specified object from database
	 *
	 * @param entity the object to delete
	 */
	void delete(E entity);

	/**
	 * Method for adding object to database.
	 *
	 * @param entity the object to add
	 * @return database-generated entity id
	 * @throws DAOLayerException
	 */
	int add(E entity) throws DAOLayerException;

	/**
	 * Updates data of specified object.
	 *
	 * @param entity the object to update
	 * @throws DAOLayerException
	 */
	void update(E entity) throws DAOLayerException;
}