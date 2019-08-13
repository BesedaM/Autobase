package by.epam.javatraining.beseda.webproject.model.service.dependenceservice;

import by.epam.javatraining.beseda.webproject.model.dao.dependencedao.ToOneDependenceDAO;
import by.epam.javatraining.beseda.webproject.model.dao.exception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.model.dao.exception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.model.entity.BaseEntity;
import by.epam.javatraining.beseda.webproject.model.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.model.service.exception.ServiceTechnicalException;

public class ToOneDependenceService <M extends BaseEntity, K extends BaseEntity> extends DependenceService<M,K>{

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