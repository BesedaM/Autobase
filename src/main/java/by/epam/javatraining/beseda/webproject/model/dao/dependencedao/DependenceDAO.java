package by.epam.javatraining.beseda.webproject.model.dao.dependencedao;

import by.epam.javatraining.beseda.webproject.model.entity.BaseEntity;
import by.epam.javatraining.beseda.webproject.model.dao.exception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.model.dao.util.wrapperconnector.WrapperConnector;

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
     * @param dependency dependency object
     * @throws DAOTechnicalException
     */
    public synchronized void setDependence(M entity, K dependency) throws DAOTechnicalException {
        if (entity != null && dependency != null) {
            PreparedStatement st = null;
            try {
                st = connector.prepareStatement(updateDependenceStatement());
                st.setInt(1, entity.getId());
                st.setInt(2, dependency.getId());
                st.executeUpdate();
            } catch (SQLException e) {
                throw new DAOTechnicalException("Error retrieving data from database", e);
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
