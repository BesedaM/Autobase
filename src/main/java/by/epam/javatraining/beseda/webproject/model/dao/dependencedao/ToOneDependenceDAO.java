package by.epam.javatraining.beseda.webproject.model.dao.dependencedao;

import by.epam.javatraining.beseda.webproject.model.entity.BaseEntity;
import by.epam.javatraining.beseda.webproject.model.dao.exception.DAOTechnicalException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.epam.javatraining.beseda.webproject.model.dao.util.database.SQLQuery.NULL;

/**
 * Class represents part relationship in database -to-one
 *
 * @param <M> entity type parameter
 * @param <K> dependence type parameter
 */
public abstract class ToOneDependenceDAO<M extends BaseEntity, K extends BaseEntity> extends DependenceDAO<M, K> {

    protected ToOneDependenceDAO() {
        super();
    }


    /**
     * Returns dependence id
     *
     * @param entity the specified entity
     * @return dependence id according to entity id
     * @throws DAOTechnicalException
     */
    public synchronized int getDependenceId(M entity) throws DAOTechnicalException {
        int dependenceId = 0;
        if (entity != null) {
            PreparedStatement st = null;
            try {
                st = connector.prepareStatement(getDependenceIdStatement());
                st.setInt(1, entity.getId());
                ResultSet res = st.executeQuery();
                dependenceId = res.getInt(1);
            } catch (SQLException e) {
                throw new DAOTechnicalException("Error retrieving data from database", e);
            } finally {
                connector.closeStatement(st);
            }
        }
        return dependenceId;
    }

    /**
     *
     * @return string representation of get statement
     */
    protected abstract String getDependenceIdStatement();

    /**
     * Deletes dependence for the specified entity
     *
     * @param entity the specified entity
     * @throws DAOTechnicalException
     */
    public synchronized void deleteDependence(M entity) throws DAOTechnicalException {
        if (entity != null) {
            PreparedStatement st = null;
            try {
                st = connector.prepareStatement(updateDependenceStatement());
                st.setInt(1, NULL);
                st.setInt(2, entity.getId());
                st.executeUpdate();
            } catch (SQLException e) {
                throw new DAOTechnicalException("Error retrieving data from database", e);
            } finally {
                connector.closeStatement(st);
            }
        }
    }
}
