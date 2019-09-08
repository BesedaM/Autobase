package by.epam.javatraining.beseda.webproject.service.dependenceservice;

import by.epam.javatraining.beseda.webproject.dao.dependencedao.ToOneDependenceDAO;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.entity.EntityBase;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceTechnicalException;

public abstract class ToOneDependenceService <M extends EntityBase, K extends EntityBase> extends DependenceService<M,K>{

    protected ToOneDependenceService() {
        super();
    }

    public void deleteDependence(M entity) throws ServiceTechnicalException {
        if (entity != null) {
            try {
                ((ToOneDependenceDAO<M,K>)dependenceDAO).deleteDependence(entity);
            } catch (DAOTechnicalException e) {
                throw new ServiceTechnicalException(e);
            }
        }
    }

    public void deleteDependence(int entityId) throws ServiceTechnicalException {
        if (entityId>0) {
            try {
                ((ToOneDependenceDAO<M,K>)dependenceDAO).deleteDependence(entityId);
            } catch (DAOTechnicalException e) {
                throw new ServiceTechnicalException(e);
            }
        }
    }

    public int getEntity02Id(M entity) throws ServiceLayerException {
        int entityId02 = -1;
        if (entity != null) {
            try {
                entityId02 = ((ToOneDependenceDAO<M,K>)dependenceDAO).getDependenceId(entity);
            } catch (DAOLayerException e) {
                throw new ServiceLayerException(e);
            }
        }
        return entityId02;
    }
}
