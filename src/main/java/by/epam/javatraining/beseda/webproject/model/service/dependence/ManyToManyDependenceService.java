package by.epam.javatraining.beseda.webproject.model.service.dependence;

import by.epam.javatraining.beseda.webproject.model.dao.dependencedao.ManyToManyDependenceDAO;
import by.epam.javatraining.beseda.webproject.model.dao.exception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.model.dao.exception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.model.entity.BaseEntity;
import by.epam.javatraining.beseda.webproject.model.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.model.service.exception.ServiceTechnicalException;


public class ManyToManyDependenceService<M extends BaseEntity, K extends BaseEntity> extends DependenceService<M, K> {

    protected ManyToManyDependenceService() {
        super();
    }


    public void deleteDependence(M entity01, K entity02) throws ServiceTechnicalException {
        if (entity01 != null && entity02 != null) {
            try {
                ((ManyToManyDependenceDAO<M, K>) dependenceDAO).deleteDependence(entity01, entity02);
            } catch (DAOTechnicalException e) {
                throw new ServiceTechnicalException();
            }
        }
    }

    public int[] getEntities01Id(K entity) throws ServiceLayerException {
        int[] entityId01 = null;
        if (entity != null) {
            try {
                entityId01 = ((ManyToManyDependenceDAO<M, K>) dependenceDAO).getDependencesId(entity);
            } catch (DAOLayerException e) {
                throw new ServiceLayerException(e);
            }
        }
        return entityId01;
    }

    public int[] getEntities02Id(M entity) throws ServiceLayerException {
        int[] entityId02 = null;
        if (entity != null) {
            try {
                entityId02 = ((ManyToManyDependenceDAO<M, K>) dependenceDAO).getDependencesId(entity);
            } catch (DAOLayerException e) {
                throw new ServiceLayerException(e);
            }
        }
        return entityId02;
    }
}
