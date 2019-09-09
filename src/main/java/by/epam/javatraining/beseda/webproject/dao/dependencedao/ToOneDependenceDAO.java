package by.epam.javatraining.beseda.webproject.dao.dependencedao;

import by.epam.javatraining.beseda.webproject.entity.EntityBase;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOTechnicalException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.epam.javatraining.beseda.webproject.dao.util.database.SQLQuery.NULL;

/**
 * Class represents part relationship in database -to-one
 *
 * @param <M> entityservice type parameter
 * @param <K> dependenceservice type parameter
 */
public abstract class ToOneDependenceDAO<M extends EntityBase, K extends EntityBase> extends DependenceDAO<M, K> {

    protected ToOneDependenceDAO() {
        super();
    }


    /**
     * Returns dependenceservice id
     *
     * @param entity the specified entityservice
     * @return dependenceservice id according to entityservice id
     * @throws DAOTechnicalException
     */
    public int getDependenceId(M entity) throws DAOTechnicalException {

        int dependenceId = 0;
        if (entity != null) {
            PreparedStatement st = null;
            try {
                lock.lock();
                st = connector.prepareStatement(getDependenceIdStatement());
                st.setInt(1, entity.getId());
                ResultSet res = st.executeQuery();
                if (res.first()) {
                    dependenceId = res.getInt(1);
                }
            } catch (SQLException e) {
                throw new DAOTechnicalException("Error retrieving data from database", e);
            } finally {
                connector.closeStatement(st);
                lock.unlock();
            }
        }
        return dependenceId;
    }

    /**
     * @return string representation of get statement
     */
    protected abstract String getDependenceIdStatement();

    /**
     * Deletes dependenceservice for the specified entityservice
     *
     * @param entity the specified entityservice
     * @throws DAOTechnicalException
     */
    public void deleteDependence(M entity) throws DAOTechnicalException {
        if (entity != null) {
            PreparedStatement st = null;
            try {
                lock.lock();
                st = connector.prepareStatement(updateDependenceStatement());
                st.setInt(1, NULL);
                st.setInt(2, entity.getId());
                st.executeUpdate();
            } catch (SQLException e) {
                throw new DAOTechnicalException("Error updating database", e);
            } finally {
                connector.closeStatement(st);
                lock.unlock();
            }
        }
    }


    public void deleteDependence(int entityId) throws DAOTechnicalException {
        if (entityId >0) {
            PreparedStatement st = null;
            try {
                lock.lock();
                st = connector.prepareStatement(updateDependenceStatement());
                st.setInt(1, NULL);
                st.setInt(2, entityId);
                st.executeUpdate();
            } catch (SQLException e) {
                throw new DAOTechnicalException("Error updating database", e);
            } finally {
                connector.closeStatement(st);
                lock.unlock();
            }
        }
    }
}
