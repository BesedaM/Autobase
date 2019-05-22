package by.epam.javatraining.beseda.webproject.model.dao.dependencedao;

import by.epam.javatraining.beseda.webproject.model.entity.BaseEntity;
import by.epam.javatraining.beseda.webproject.model.exception.daoexception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.util.wrapperconnector.WrapperConnector;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Top hierarchy class for dependenceDAO
 *
 * @param <M> entity type parameter
 * @param <K> dependence type parameter
 */
public abstract class DependenceDAO<M extends BaseEntity, K extends BaseEntity> {

    protected volatile WrapperConnector connector;

    protected DependenceDAO() {
        this.connector = new WrapperConnector();
    }

    protected DependenceDAO(WrapperConnector connector) {
        this.connector = connector;
    }

    /**
     * Sets the dependency to the object
     *
     * @param entity     object
     * @param dependency dependency object
     * @throws DAOTechnicalException
     */
    public void setDependence(M entity, K dependency) throws DAOTechnicalException {
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

//    /**
//     * Closes the Wrapper Connection instance
//     */
//    public void closeConnection() {
//        connector.closeConnector();
//    }
}
