package by.epam.javatraining.beseda.webproject.model.service.entity;

import by.epam.javatraining.beseda.webproject.model.dao.entitydao.AbstractDAO;
import by.epam.javatraining.beseda.webproject.model.dao.factory.DAOEntityFactory;
import by.epam.javatraining.beseda.webproject.model.entity.BaseEntity;
import by.epam.javatraining.beseda.webproject.model.dao.exception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.model.dao.exception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.model.exception.entityexception.IllegalEntityIdException;
import by.epam.javatraining.beseda.webproject.model.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.model.service.exception.ServiceLogicException;
import by.epam.javatraining.beseda.webproject.model.service.exception.ServiceTechnicalException;

import java.util.List;

public abstract class AbstractEntityService<E extends BaseEntity> implements EntityService<E> {

    protected static DAOEntityFactory daoEntityFactory = DAOEntityFactory.getFactory();
    protected AbstractDAO<E> entityDAO;

    protected AbstractEntityService() {
    }

    @Override
    public List<E> getAll() throws ServiceLayerException {
        List<E> list = null;
        try {
            list = entityDAO.getAll();
        } catch (DAOLayerException e) {
            throw new ServiceLayerException(e);
        }
        return list;
    }

    @Override
    public E getEntityById(int id) throws ServiceLayerException {
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

    /**
     * Adds entity to database and assigns to entity the value of id
     *
     * @param entity entity to add
     * @throws ServiceLayerException
     */
    @Override
    public void add(E entity) throws ServiceLayerException {
        if (entity != null) {
            try {
                int id = entityDAO.add(entity);
                entity.setId(id);
            } catch (DAOLayerException | IllegalEntityIdException e) {
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
    public void delete(int id) throws ServiceLogicException {
        if (id > 0) {
            try {
                entityDAO.delete(id);
            } catch (DAOTechnicalException e) {
                throw new ServiceLogicException(e);
            }
        }
    }

    @Override
    public void delete(E entity) throws ServiceLogicException {
        if (entity != null) {
            try {
                entityDAO.delete(entity.getId());
            } catch (DAOTechnicalException e) {
                throw new ServiceLogicException(e);
            }
        }
    }
}
