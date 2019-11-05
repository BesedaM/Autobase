package by.epam.javatraining.beseda.webproject.service.entityservice;

import java.util.List;

import org.springframework.stereotype.Service;

import by.epam.javatraining.beseda.webproject.dao.exception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.EntityDAO;
import by.epam.javatraining.beseda.webproject.entity.EntityBase;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLogicException;

/**
 * Abstract class containing main methods for accessing the data
 * 
 * @author Maryia_Biaseda
 *
 * @param <E> type parameter of EntityBase superclass
 */
@Service
public abstract class AbstractEntityService<E extends EntityBase> implements EntityService<E> {

    protected EntityDAO<E> entityDAO;

    public abstract void setEntityDAO(EntityDAO<E> entityDAO);

    @Override
    public List<E> getAll(){
        List<E> list = null;
        list = entityDAO.getAll();
        return list;
    }

    @Override
    public E getEntityById(int id){
        E entity = null;
        if (id > 0) {
            entity = entityDAO.getEntityById(id);
        }
        return entity;
    }

    @Override
    public List<E> getEntitiesByIdList(int[] idArr){
        List<E> list = null;
        list = entityDAO.getEntitiesByIdList(idArr);
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
            } catch (DAOLayerException e) {
                throw new ServiceLogicException(e);
            }
        }
    }

    @Override
    public void update(E entity) throws ServiceLayerException {
        if (entity != null) {
            try {

                System.out.println("inside service update method");

                entityDAO.update(entity);
            } catch (DAOLayerException e) {
                throw new ServiceLogicException(e);
            }
        }
    }

    @Override
    public void delete(int id){
        if (id > 0) {
            entityDAO.delete(id);
        }
    }

    @Override
    public final void delete(E entity){
        if (entity != null) {
            entityDAO.delete(entity.getId());
        }
    }
}