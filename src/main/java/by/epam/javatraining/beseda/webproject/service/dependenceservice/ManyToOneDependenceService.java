package by.epam.javatraining.beseda.webproject.service.dependenceservice;

import by.epam.javatraining.beseda.webproject.dao.dependencedao.ManyToOneDependenceDAO;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.entity.EntityBase;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;

public abstract class ManyToOneDependenceService<M extends EntityBase,K extends EntityBase> extends ToOneDependenceService<M,K> {

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
