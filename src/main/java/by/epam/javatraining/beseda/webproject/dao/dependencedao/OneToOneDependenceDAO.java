package by.epam.javatraining.beseda.webproject.dao.dependencedao;

import by.epam.javatraining.beseda.webproject.entity.EntityBase;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOTechnicalException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.epam.javatraining.beseda.webproject.dao.util.database.SQLQuery.NULL;

/**
 * Class represents relationship in database one-to-one
 *
 * @param <M> entityservice type parameter
 * @param <K> dependenceservice type parameter
 */
public abstract class OneToOneDependenceDAO<M extends EntityBase, K extends EntityBase> extends ToOneDependenceDAO<M, K> {

    protected OneToOneDependenceDAO() {
        super();
    }

    /**
     * Returns entityservice id according to dependenceservice
     *
     * @param dependence entityservice-dependenceservice
     * @return
     * @throws DAOTechnicalException
     */
    public int getEntityIdByDependence(K dependence) throws DAOTechnicalException {
        int entityId = NULL;
        if (dependence != null) {
            PreparedStatement st = null;
            try {
                lock.lock();
                st = connector.prepareStatement(getEntityByDependenceStatement());
                st.setInt(1, dependence.getId());
                ResultSet res = st.executeQuery();
                if (res.first()) {
                    entityId = res.getInt(1);
                }
            } catch (SQLException e) {
                throw new DAOTechnicalException(e);
            } finally {
                connector.closeStatement(st);
                lock.unlock();
            }
        }
        return entityId;
    }

    /**
     * @return string representation of getEntityIdByDependence statement
     */
    protected abstract String getEntityByDependenceStatement();

}
