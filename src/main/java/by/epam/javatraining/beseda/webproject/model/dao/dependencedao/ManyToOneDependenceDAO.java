package by.epam.javatraining.beseda.webproject.model.dao.dependencedao;

import by.epam.javatraining.beseda.webproject.model.entity.BaseEntity;
import by.epam.javatraining.beseda.webproject.model.exception.daoexception.DAOTechnicalException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class represents relationship in database one-to-many
 *
 * @param <M> entity type parameter
 * @param <K> dependence type parameter
 */
public abstract class ManyToOneDependenceDAO<M extends BaseEntity, K extends BaseEntity> extends DependenceToOneDAO<M,K>{

    protected ManyToOneDependenceDAO() {
        super();
    }

    /**
     * Returns array containing entities id having the specified dependency object
     *
     * @param dependence
     * @return
     * @throws DAOTechnicalException
     */
    public synchronized int[] getEntitiesIdByDependenceId(K dependence) throws DAOTechnicalException {
        int[] entityId = null;
        if (dependence != null) {
            PreparedStatement st = null;
            try {
                st = connector.prepareStatement(getEntitiesByDependenceStatement());
                st.setInt(1, dependence.getId());
                ResultSet res = st.executeQuery();
                res.last();
                entityId = new int[res.getRow()];
                res.first();
                for (int i = 0; i < entityId.length; i++) {
                    entityId[i] = res.getInt(1);
                    res.next();
                }
            } catch (SQLException e) {
                throw new DAOTechnicalException("Error retrieving data from database", e);
            } finally {
                connector.closeStatement(st);
            }
        }
        return entityId;
    }

    /**
     *
     * @return string representation of getEntitiesByDependence statement
     */
    protected abstract String getEntitiesByDependenceStatement();

}
