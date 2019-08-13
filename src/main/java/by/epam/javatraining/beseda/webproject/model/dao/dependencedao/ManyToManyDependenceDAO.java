package by.epam.javatraining.beseda.webproject.model.dao.dependencedao;

import by.epam.javatraining.beseda.webproject.model.entity.BaseEntity;
import by.epam.javatraining.beseda.webproject.model.dao.exception.DAOTechnicalException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class represents relationship in database many-to-many
 *
 * @param <M> entityservice type parameter
 * @param <K> dependenceservice type parameter
 */
public abstract class ManyToManyDependenceDAO<M extends BaseEntity, K extends BaseEntity> extends DependenceDAO<M, K> {

    protected ManyToManyDependenceDAO() {
        super();
    }

    /**
     * Returns dependenceservice ids according to specified entityservice
     *
     * @param entity one oа two dependencies
     * @return array, containing all the dependenceservice ids
     * @throws DAOTechnicalException
     */
    public synchronized int[] getDependencesId(BaseEntity entity) throws DAOTechnicalException {
        int[] dependencyId = null;
        if (entity != null) {
            PreparedStatement st = null;
            try {
                String statement = getDependencesStatement(entity);
                st = connector.prepareStatement(statement);
                st.setInt(1, entity.getId());
                ResultSet res = st.executeQuery();
                res.last();
                dependencyId = new int[res.getRow()];
                res.first();
                for (int i = 0; i < dependencyId.length; i++) {
                    dependencyId[i] = res.getInt(1);
                    res.next();
                }
            } catch (SQLException e) {
                throw new DAOTechnicalException("Error retrieving data from database", e);
            } finally {
                connector.closeStatement(st);
            }
        }
        return dependencyId;
    }

    /**
     *
     * @param entity
     * @return string representation of getDependence statement specified with the entityservice type
     */
    protected abstract String getDependencesStatement(BaseEntity entity);


    protected abstract String updateDependenceStatement();

    public synchronized void deleteDependence(M entity, K dependency) throws DAOTechnicalException {
        if (entity != null) {
            PreparedStatement st = null;
            try {
                st = connector.prepareStatement(deleteDependenceStatement());
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

    protected abstract String deleteDependenceStatement();
}