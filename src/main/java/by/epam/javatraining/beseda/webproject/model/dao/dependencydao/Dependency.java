package by.epam.javatraining.beseda.webproject.model.dao.dependencydao;

import by.epam.javatraining.beseda.webproject.model.exception.DAOexception.DAOTechnicalException;

public interface Dependency {

    void setDependency(int entityId, int dependencyId) throws DAOTechnicalException;

    int[] getDependencyId(int entityId) throws DAOTechnicalException;

    void deleteDependency(int entityId, int dependencyId) throws DAOTechnicalException;
}
