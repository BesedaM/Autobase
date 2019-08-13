package by.epam.javatraining.beseda.webproject.model.service.dependenceservice;

import by.epam.javatraining.beseda.webproject.model.dao.dependencedao.OneToOneDependenceDAO;
import by.epam.javatraining.beseda.webproject.model.dao.exception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.model.entity.BaseEntity;
import by.epam.javatraining.beseda.webproject.model.service.exception.ServiceLayerException;

public class OneToOneDependenceService<M extends BaseEntity,K extends BaseEntity> extends ToOneDependenceService<M,K>{

    protected OneToOneDependenceService() {super();}

    public int getEntityIdByDependence(K entity) throws ServiceLayerException {
        int entityId01 = -1;
        if (entity != null) {
            try {
                entityId01 = ((OneToOneDependenceDAO<M,K>)dependenceDAO).getEntityIdByDependence(entity);
            } catch (DAOLayerException e) {
                throw new ServiceLayerException(e);
            }
        }
        return entityId01;
    }

}
