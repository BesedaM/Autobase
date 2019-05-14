package by.epam.javatraining.beseda.webproject.model.dao.dependencydao;

import by.epam.javatraining.beseda.webproject.model.exception.DAOexception.DAOLayerException;

/**
 * Interface, defining methods for dependency DAO
 */
public interface Dependency {

    /**
     * Sets the dependency to the object
     *
     * @param entityId     object id
     * @param dependencyId dependency object id
     * @throws DAOLayerException
     */
    void setDependency(int entityId, int dependencyId) throws DAOLayerException;

    /**
     * Returns all the dependencies id for the specified object
     *
     * @param entityId object id
     * @return int array, containing all the dependencies id
     * @throws DAOLayerException
     */
    int[] getDependencyId(int entityId) throws DAOLayerException;

    /**
     * Deletes the specified dependency
     *
     * @param entityId     object id
     * @param dependencyId dependency object id
     * @throws DAOLayerException
     */
    void deleteDependency(int entityId, int dependencyId) throws DAOLayerException;
}
