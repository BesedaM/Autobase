package by.epam.javatraining.beseda.webproject.dao.dependencedao;

import by.epam.javatraining.beseda.webproject.entity.EntityBase;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOTechnicalException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class represents relationship in database one-to-many
 *
 * @param <M> entityservice type parameter
 * @param <K> dependenceservice type parameter
 */
public abstract class ManyToOneDependenceDAO<M extends EntityBase, K extends EntityBase> extends ToOneDependenceDAO<M,K> {

    protected ManyToOneDependenceDAO() {
        super();
    }

    /**
     * Returns array containing entities id having the specified dependency object
     *
     * @param dependence dependent object
     * @return array, containing entities id
     * @throws DAOTechnicalException
     */
    public int[] getEntitiesIdByDependenceId(K dependence) throws DAOTechnicalException {
        int[] entityId = null;
        if (dependence != null) {
            PreparedStatement st = null;
            try {
                lock.lock();
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
                lock.unlock();
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
