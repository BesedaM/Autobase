package by.epam.javatraining.beseda.webproject.model.dao.entitydao;

import by.epam.javatraining.beseda.webproject.model.entity.BaseEntity;
import by.epam.javatraining.beseda.webproject.model.exception.daoexception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.model.exception.daoexception.DAOLogicException;
import by.epam.javatraining.beseda.webproject.model.exception.daoexception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.model.exception.entityexception.EntityLogicException;
import by.epam.javatraining.beseda.webproject.util.wrapperconnector.WrapperConnector;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static by.epam.javatraining.beseda.webproject.util.database.SQLQuery.END_OF_STATEMENT;

/**
 * Abstract class, containing generic methods for entity DAO
 *
 * @param <E> parameter type
 */
public abstract class AbstractDAO<E extends BaseEntity> implements EntityDAO<E> {

    protected volatile WrapperConnector connector;
//    protected String dbTableName;

    private Logger log = Logger.getLogger(AbstractDAO.class.getSimpleName());

    protected AbstractDAO() {
        this.connector = new WrapperConnector();
    }

    public List<E> getAll() throws DAOLayerException {
        List<E> list = new ArrayList<>();
        Statement st = null;
        E entity = null;
        try {
            st = connector.createStatement();
            ResultSet result = st.executeQuery(getAllStatement() + END_OF_STATEMENT);
            while (result.next()) {
                entity = createEntity(result);
                list.add(entity);
            }
        } catch (SQLException e) {
            throw new DAOTechnicalException("Error retrieving data from database", e);
        } catch (EntityLogicException e) {
            throw new DAOTechnicalException("Error creating entity " + entity.getClass().getSimpleName(), e);
        } finally {
            closeStatement(st);
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
            st = connector.prepareStatement(getEntityByIdStatement());
            st.setInt(1, id);
            ResultSet res = st.executeQuery();
            entity = createEntity(res);
        } catch (SQLException e) {
            throw new DAOTechnicalException("Error retrieving data from database", e);
        } catch (EntityLogicException e) {
            throw new DAOTechnicalException("Error creating entity " + entity.getClass().getSimpleName(), e);
        } finally {
            closeStatement(st);
        }
        return entity;
    }

    @Override
    public List<E> getEntitiesByIdList(int[] idArr) throws DAOLayerException {
        List<E> list = new ArrayList<>();
        if (idArr != null && idArr.length > 0) {
            E entity = null;
            PreparedStatement st = null;
            try {
                st = connector.prepareStatement(getEntityByIdStatement());
                for (int i = 0; i < idArr.length; i++) {
                    st.setInt(1, idArr[i]);
                    ResultSet res = st.executeQuery();
                    entity = createEntity(res);
                    list.add(entity);
                }
            } catch (SQLException e) {
                throw new DAOTechnicalException("Error retrieving data from database", e);
            } catch (EntityLogicException e) {
                throw new DAOTechnicalException("Error creating entity " + entity.getClass().getSimpleName(), e);
            } finally {
                closeStatement(st);
            }
        }
        return list;
    }


    /**
     * Return string representation of SQL 'select by id' query
     */
    protected abstract String getEntityByIdStatement();

    protected abstract E createEntity(ResultSet res) throws DAOLogicException, SQLException, EntityLogicException;

    public void delete(int id) {
        PreparedStatement st = null;
        try {
            st = connector.prepareStatement(deleteStatement());
            st.setInt(1, id);
        } catch (SQLException e) {
            log.error("SQL exception. Error retrieving data from database: " + e);
        } finally {
            closeStatement(st);
        }
    }

    /**
     * Return string representation of SQL 'delete entity' query
     */
    protected abstract String deleteStatement();

    public void delete(E entity) {
        delete(entity.getId());
    }

    public int add(E user) throws DAOLayerException {
        int id = -1;
        if (user != null) {
            PreparedStatement st = null;
            try {
                st = connector.prepareStatementWithAutoGeneratedKeys(addStatement());
                setDataOnPreparedStatement(st, user);
                st.executeUpdate();
                ResultSet rs = st.getGeneratedKeys();
                rs.first();
                id = rs.getInt(1);
            } catch (SQLException e) {
                throw new DAOTechnicalException("Error updating database", e);
            } finally {
                closeStatement(st);
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
     * Returns string representation of SQL 'add entity' query
     */
    protected abstract String addStatement();

    public void update(E entity) throws DAOLayerException {
        if (entity != null) {
            PreparedStatement st = null;
            try {
                st = connector.prepareStatement(updateStatement());
                setDataOnPreparedStatement(st, entity);
                st.setInt(updateIdParameterNumber(), entity.getId());
                st.executeUpdate();
            } catch (SQLException e) {
                throw new DAOTechnicalException("Error updating database", e);
            } finally {
                closeStatement(st);
            }
        }
    }

    /**
     * Returns string representation of SQL 'update entity' query
     */
    protected abstract String updateStatement();

    protected abstract int updateIdParameterNumber();

    /**
     * Method for getting the WrapperConnector object
     *
     * @return WrapperConnector object
     */
    public WrapperConnector getWrapperConnector() {
        return connector;
    }

    /**
     * Closes the specified statement
     *
     * @param statement statement to close
     */
    protected void closeStatement(Statement statement) {
        if (statement != null) {
            connector.closeStatement(statement);
        }
    }

    public void closeConnection() {
        connector.closeConnection();
    }

}
