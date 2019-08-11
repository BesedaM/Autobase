package by.epam.javatraining.beseda.webproject.model.service.dependence;

import by.epam.javatraining.beseda.webproject.model.dao.dependencedao.DependenceDAO;
import by.epam.javatraining.beseda.webproject.model.dao.exception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.model.dao.factory.DAODependenceFactory;
import by.epam.javatraining.beseda.webproject.model.entity.BaseEntity;
import by.epam.javatraining.beseda.webproject.model.service.exception.ServiceTechnicalException;

public class DependenceService<M extends BaseEntity,K extends BaseEntity> {

    protected static DAODependenceFactory daoDependenceFactory = DAODependenceFactory.getFactory();
    protected DependenceDAO<M,K> dependenceDAO;

    protected DependenceService() { }

    public void addDependence(M entity01, K entity02) throws ServiceTechnicalException {
        if (entity01 != null && entity02 != null) {
            try {
                dependenceDAO.setDependence(entity01, entity02);
            } catch (DAOTechnicalException e) {
                throw new ServiceTechnicalException(e);
            }
        }
    }
}
