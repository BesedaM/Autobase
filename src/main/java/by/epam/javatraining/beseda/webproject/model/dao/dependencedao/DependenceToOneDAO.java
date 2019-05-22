package by.epam.javatraining.beseda.webproject.model.dao.dependencedao;

import by.epam.javatraining.beseda.webproject.model.entity.BaseEntity;
import by.epam.javatraining.beseda.webproject.model.exception.daoexception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.util.wrapperconnector.WrapperConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.epam.javatraining.beseda.webproject.util.database.SQLQuery.NULL;

/**
 * Class represents part relationship in database -to-one
 *
 * @param <M> entity type parameter
 * @param <K> dependence type parameter
 */
public abstract class DependenceToOneDAO<M extends BaseEntity, K extends BaseEntity> extends DependenceDAO<M, K> {

    protected DependenceToOneDAO() {
        super();
    }

    protected DependenceToOneDAO(WrapperConnector connector) {
        super(connector);
    }

    /**
     * Returns dependence id
     *
     * @param entity the specified entity
     * @return dependence id according to entity id
     * @throws DAOTechnicalException
     */
    public int getDependenceId(M entity) throws DAOTechnicalException {
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
    public void deleteDependence(M entity) throws DAOTechnicalException {
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
