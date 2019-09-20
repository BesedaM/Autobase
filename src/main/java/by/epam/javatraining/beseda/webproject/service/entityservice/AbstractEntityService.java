package by.epam.javatraining.beseda.webproject.service.entityservice;

import by.epam.javatraining.beseda.webproject.dao.entitydao.MySQLDAOEntityFactory;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.EntityDAO;
import by.epam.javatraining.beseda.webproject.entity.EntityBase;
import by.epam.javatraining.beseda.webproject.entity.exception.EntityIdException;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLogicException;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceTechnicalException;

import java.util.List;

/**
 * Abstract class containing main methods for accessing the data
 * 
 * @author Maryia_Biaseda
 *
 * @param <E> type parameter of EntityBase superclass
 */
public abstract class AbstractEntityService<E extends EntityBase> implements EntityService<E> {

    protected static MySQLDAOEntityFactory mySQLDAOEntityFactory = MySQLDAOEntityFactory.getFactory();
    protected EntityDAO<E> entityDAO;

    @Override
    public final List<E> getAll() throws ServiceLayerException {
        List<E> list = null;
        try {
            list = entityDAO.getAll();
        } catch (DAOLayerException e) {
            throw new ServiceLayerException(e);
        }
        return list;
    }

    @Override
    public final E getEntityById(int id) throws ServiceLayerException {
        E entity = null;
        if (id > 0) {
            try {
                entity = entityDAO.getEntityById(id);
            } catch (DAOLayerException e) {
                throw new ServiceTechnicalException(e);
            }
        }
        return entity;
    }

    @Override
    public final List<E> getEntitiesByIdList(int[] idArr) throws ServiceLayerException {
        List<E> list = null;
        try {
            list = entityDAO.getEntitiesByIdList(idArr);
        } catch (DAOLayerException e) {
            throw new ServiceLayerException(e);
        }
        return list;
    }

    /**
     * Adds entityservice to database and assigns to entityservice the value of id
     *
     * @param entity entityservice to add
     * @throws ServiceLayerException
     */
    @Override
    public void add(E entity) throws ServiceLayerException {
        if (entity != null) {
            try {
                int id = entityDAO.add(entity);
                entity.setId(id);
            } catch (DAOLayerException | EntityIdException e) {
                throw new ServiceLogicException(e);
            }
        }
    }

    @Override
    public void update(E entity) throws ServiceLayerException {
        if (entity != null) {
            try {
                entityDAO.update(entity);
            } catch (DAOLayerException e) {
                throw new ServiceLogicException(e);
            }
        }
    }

    @Override
    public final void delete(int id) throws ServiceLogicException {
        if (id > 0) {
            try {
                entityDAO.delete(id);
            } catch (DAOTechnicalException e) {
                throw new ServiceLogicException(e);
            }
        }
    }

    @Override
    public final void delete(E entity) throws ServiceLogicException {
        if (entity != null) {
            try {
                entityDAO.delete(entity.getId());
            } catch (DAOTechnicalException e) {
                throw new ServiceLogicException(e);
            }
        }
    }
}