package by.epam.javatraining.beseda.webproject.model.dao.dependencedao;

import by.epam.javatraining.beseda.webproject.model.entity.BaseEntity;
import by.epam.javatraining.beseda.webproject.model.exception.daoexception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.util.wrapperconnector.WrapperConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.epam.javatraining.beseda.webproject.util.database.SQLQuery.NULL;

/**
 * Class represents relationship in database one-to-one
 *
 * @param <M> entity type parameter
 * @param <K> dependence type parameter
 */
public abstract class OneToOneDependenceDAO<M extends BaseEntity, K extends BaseEntity> extends DependenceToOneDAO<M, K> {

    protected OneToOneDependenceDAO() {
        super();
    }

    protected OneToOneDependenceDAO(WrapperConnector connector) {
        super(connector);
    }

    /**
     * Returns entity id according to dependence
     *
     * @param dependence entity-dependence
     * @return
     * @throws DAOTechnicalException
     */
    public int getEntityIdByDependence(K dependence) throws DAOTechnicalException {
        int entityId = NULL;
        if (dependence != null) {
            PreparedStatement st = null;
            try {
                st = connector.prepareStatement(getEntityByDependenceStatement());
                st.setInt(1, dependence.getId());
                ResultSet res = st.executeQuery();
                entityId = res.getInt(1);
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
     * @return string representation of getEntityIdByDependence statement
     */
    protected abstract String getEntityByDependenceStatement();

}
