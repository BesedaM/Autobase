package by.epam.javatraining.beseda.webproject.dao.dependencedao;

import by.epam.javatraining.beseda.webproject.entity.BaseEntity;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.dao.util.wrapperconnector.WrapperConnector;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Top hierarchy class for dependenceDAO
 *
 * @param <M> entityservice type parameter
 * @param <K> dependenceservice type parameter
 */
public abstract class DependenceDAO<M extends BaseEntity, K extends BaseEntity> {

    protected WrapperConnector connector;

    protected DependenceDAO() {
        this.connector = new WrapperConnector();
    }

    /**
     * Sets the dependency to the object
     *
     * @param entity     object
     * @param dependence dependency object
     * @throws DAOTechnicalException
     */
    public synchronized void setDependence(M entity, K dependence) throws DAOTechnicalException {
        if (entity != null && dependence != null) {
            PreparedStatement st = null;
            try {
                st = connector.prepareStatement(updateDependenceStatement());
                st.setInt(1, dependence.getId());
                st.setInt(2, entity.getId());
                st.executeUpdate();
            } catch (SQLException e) {
                throw new DAOTechnicalException("Error retrieving data from database", e);
            } finally {
                connector.closeStatement(st);
            }
        }
    }

    /**
     * Sets the dependency to the object
     *
     * @param entityId     entity id
     * @param dependenceId dependency id
     * @throws DAOTechnicalException
     */
    public synchronized void setDependence(int entityId, int dependenceId) throws DAOTechnicalException {
        if (entityId >0 && dependenceId >0) {
            PreparedStatement st = null;
            try {
                st = connector.prepareStatement(updateDependenceStatement());
                st.setInt(1, dependenceId);
                st.setInt(2, entityId);
                st.executeUpdate();
            } catch (SQLException e) {
                throw new DAOTechnicalException("Error updating database", e);
            } finally {
                connector.closeStatement(st);
            }
        }
    }

    /**
     *
     * @return string representation of update statement
     */
    protected abstract String updateDependenceStatement();

}
