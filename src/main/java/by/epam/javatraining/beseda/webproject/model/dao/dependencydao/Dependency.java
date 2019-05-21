package by.epam.javatraining.beseda.webproject.model.dao.dependencydao;

import by.epam.javatraining.beseda.webproject.model.entity.BaseEntity;
import by.epam.javatraining.beseda.webproject.model.exception.daoexception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.model.exception.daoexception.DAOTechnicalException;

/**
 * Interface, defining methods for dependency DAO
 */
public interface Dependency<M extends BaseEntity, K extends BaseEntity> {

    /**
     * Sets the dependency to the object
     *
     * @param entity     object
     * @param dependency dependency object
     * @throws DAOLayerException
     */
    void setDependency(M entity, K dependency) throws DAOTechnicalException;

    /**
     * Returns all the dependencies id for the specified object
     *
     * @param entity object
     * @return int array, containing all the dependencies id
     * @throws DAOLayerException
     */
    int[] getDependencyId(M entity, K dependency) throws DAOTechnicalException;

    /**
     * Deletes the specified dependency
     *
     * @param entity     object
     * @param dependency dependency object
     * @throws DAOLayerException
     */
    void deleteDependency(M entity, K dependency) throws DAOTechnicalException;
}
