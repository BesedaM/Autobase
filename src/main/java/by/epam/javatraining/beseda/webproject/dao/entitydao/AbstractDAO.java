package by.epam.javatraining.beseda.webproject.dao.entitydao;

import by.epam.javatraining.beseda.webproject.dao.interfacedao.EntityDAO;
import by.epam.javatraining.beseda.webproject.entity.EntityBase;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOLogicException;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.entity.exception.EntityLogicException;
import by.epam.javatraining.beseda.webproject.dao.util.wrapperconnector.WrapperConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import static by.epam.javatraining.beseda.webproject.dao.util.database.DBConstants.*;
import static by.epam.javatraining.beseda.webproject.dao.util.database.SQLQuery.END_OF_STATEMENT;

/**
 * Abstract class, containing generic methods for entityservice DAO
 *
 * @param <E> parameter type
 */
public abstract class AbstractDAO<E extends EntityBase> implements EntityDAO<E> {

    protected WrapperConnector connector;
    protected ReentrantLock lock = new ReentrantLock();

    protected AbstractDAO() {
        this.connector = new WrapperConnector();
    }


    public List<E> getAll() throws DAOLayerException {
        List<E> list = new ArrayList<>();
        Statement st = null;
        E entity = null;
        try {
            lock.lock();
            st = connector.createStatement();
            ResultSet result = st.executeQuery(getAllStatement() + END_OF_STATEMENT);
            while (result.next()) {
                entity = buildEntity(result);
                list.add(entity);
            }
        } catch (SQLException e) {
            throw new DAOTechnicalException("Error retrieving data from database", e);
        } catch (EntityLogicException e) {
            throw new DAOTechnicalException("Error creating entity", e);
        } finally {
            connector.closeStatement(st);
            lock.unlock();
        }
        return list;
    }

    /**
     * Return string representation of SQL 'select all' query
     */
    protected abstract String getAllStatement();

    public E getEntityById(int id) throws DAOLayerException {
        E entity = null;
        PreparedStatement st = null;
        try {
            lock.lock();
            st = connector.prepareStatement(getEntityByIdStatement());
            st.setInt(1, id);
            ResultSet res = st.executeQuery();
            if (res.first()) {
                entity = buildEntity(res);
            }
        } catch (SQLException e) {
            throw new DAOTechnicalException("Error retrieving data from database", e);
        } catch (EntityLogicException e) {
            throw new DAOTechnicalException("Error creating entity", e);
        } finally {
            connector.closeStatement(st);
            lock.unlock();
        }
        return entity;
    }


    public List<E> getEntitiesByIdList(int[] idArr) throws DAOLayerException {
        List<E> list = new ArrayList<>();
        Statement st = null;
        try {
            lock.lock();
            st = connector.createStatement();
            String array = Arrays.toString(idArr);
            String newArr = array.replace(OPENING_SQUARE_BRACKET, SPACE)
                    .replace(CLOSING_SQUARE_BRACKET, SPACE).replace(SPACE, EMPTY_CHARACTER);
            String modifiedStatement = getEntityListByIdStatement().replace(QUESTION_MARK, newArr);
            ResultSet res = st.executeQuery(modifiedStatement);

            while (res.next()) {
                E entity = buildEntity(res);
                list.add(entity);
            }
        } catch (SQLException e) {
            throw new DAOTechnicalException("Error retrieving data from database", e);
        } catch (EntityLogicException e) {
            throw new DAOTechnicalException("Error creating entity", e);
        } finally {
            connector.closeStatement(st);
            lock.unlock();
        }
        return list;
    }

    protected abstract String getEntityListByIdStatement();


    /**
     * Return string representation of SQL 'select by id' query
     */
    protected abstract String getEntityByIdStatement();

    protected abstract E buildEntity(ResultSet res) throws DAOLogicException, SQLException, EntityLogicException;

    public void delete(int id) throws DAOTechnicalException {
        PreparedStatement st = null;
        try {
            lock.lock();
            st = connector.prepareStatement(deleteStatement());
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOTechnicalException("Error updating database", e);
        } finally {
            connector.closeStatement(st);
            lock.unlock();
        }
    }

    /**
     * Return string representation of SQL 'delete entityservice' query
     */
    protected abstract String deleteStatement();

    public void delete(E entity) throws DAOTechnicalException {
        delete(entity.getId());
    }

    public int add(E entity) throws DAOLayerException {
        int id = -1;
        if (entity != null) {
            PreparedStatement st = null;
            try {
                lock.lock();
                st = connector.prepareStatementWithAutoGeneratedKeys(addStatement());
                setDataOnPreparedStatement(st, entity);
                st.executeUpdate();
                ResultSet rs = st.getGeneratedKeys();
                rs.first();
                id = rs.getInt(1);
            } catch (SQLException e) {
                throw new DAOTechnicalException("Error updating database", e);
            } finally {
                connector.closeStatement(st);
                lock.unlock();
            }
        }
        return id;
    }

    /**
     * Sets data on prepared statement object
     *
     * @param st     prepared statement
     * @param entity source of data
     * @throws SQLException
     */
    protected abstract void setDataOnPreparedStatement(PreparedStatement st, E entity) throws SQLException, DAOLogicException;

    /**
     * Returns string representation of SQL 'add entity service' query
     */
    protected abstract String addStatement();

    public void update(E entity) throws DAOLayerException {
        if (entity != null) {
            PreparedStatement st = null;
            try {
                lock.lock();
                st = connector.prepareStatement(updateStatement());
                setDataOnPreparedStatement(st, entity);
                st.setInt(updateIdParameterNumber(), entity.getId());
                st.executeUpdate();
            } catch (SQLException e) {
                throw new DAOTechnicalException("Error updating database", e);
            } finally {
                connector.closeStatement(st);
                lock.unlock();
            }
        }
    }

    /**
     * Returns string representation of SQL 'update entityservice' query
     */
    protected abstract String updateStatement();

    protected abstract int updateIdParameterNumber();

    public void close() {
        connector.closeConnector();
    }

}
