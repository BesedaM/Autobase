package by.epam.javatraining.beseda.webproject.model.service.dependenceservice;

import by.epam.javatraining.beseda.webproject.model.dao.dependencedao.ManyToOneDependenceDAO;
import by.epam.javatraining.beseda.webproject.model.dao.exception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.model.entity.BaseEntity;
import by.epam.javatraining.beseda.webproject.model.service.exception.ServiceLayerException;

public class ManyToOneDependenceService<M extends BaseEntity,K extends BaseEntity> extends ToOneDependenceService<M,K> {

    public ManyToOneDependenceService() {
        super();
    }

    public int[] getEntitiesIdByDependenceId(K entity) throws ServiceLayerException {
        int[] entityId02=null;
        if (entity != null) {
            try {
                entityId02 = ((ManyToOneDependenceDAO<M,K>)dependenceDAO).getEntitiesIdByDependenceId(entity);
            } catch (DAOLayerException e) {
                throw new ServiceLayerException(e);
            }
        }
        return entityId02;
    }
}
