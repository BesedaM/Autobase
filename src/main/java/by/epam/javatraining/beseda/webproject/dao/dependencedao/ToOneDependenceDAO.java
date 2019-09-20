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
 * @param <M> entity type parameter
 * @param <K> dependence type parameter
 */
public abstract class ToOneDependenceDAO<M extends EntityBase, K extends EntityBase> extends DependenceDAO<M, K> {

	protected ToOneDependenceDAO() {
		super();
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
				lock.lock();
				st = connector.prepareStatement(getDependenceIdStatement());
				st.setInt(1, entity.getId());
				ResultSet res = st.executeQuery();
				if (res.first()) {
					dependenceId = res.getInt(1);
				}
			} catch (SQLException e) {
				throw new DAOTechnicalException(e);
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
	 * Deletes dependence for the specified entity
	 *
	 * @param entity the specified entity
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
				throw new DAOTechnicalException(e);
			} finally {
				connector.closeStatement(st);
				lock.unlock();
			}
		}
	}

	public void deleteDependence(int entityId) throws DAOTechnicalException {
		if (entityId > 0) {
			PreparedStatement st = null;
			try {
				lock.lock();
				st = connector.prepareStatement(updateDependenceStatement());
				st.setInt(1, NULL);
				st.setInt(2, entityId);
				st.executeUpdate();
			} catch (SQLException e) {
				throw new DAOTechnicalException(e);
			} finally {
				connector.closeStatement(st);
				lock.unlock();
			}
		}
	}
}