package by.epam.javatraining.beseda.webproject.dao.dependencedao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.epam.javatraining.beseda.webproject.connectionpool.ConnectionPool;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.entity.EntityBase;

/**
 * Class represents relationship in database many-to-many.
 *
 * @param <M> entity type parameter
 * @param <K> dependence type parameter
 */
public abstract class ManyToManyDependenceDAO<M extends EntityBase, K extends EntityBase> extends DependenceDAO<M, K> {

	protected ManyToManyDependenceDAO() {
		super();
	}


	protected ManyToManyDependenceDAO(ConnectionPool pool) {
		super(pool);
	}
	
	/**
	 * Returns dependence ids according to specified entity.
	 *
	 * @param entity one oа two dependencies
	 * @return array, containing all the dependence service ids
	 * @throws DAOTechnicalException
	 */
	public int[] getDependenceId(EntityBase entity) throws DAOTechnicalException {
		int[] dependencyId =  new int[1];
		if (entity != null) {
			PreparedStatement st = null;
			try {
				String statement = getDependenceStatement(entity);
				lock.lock();
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
				throw new DAOTechnicalException(e);
			} finally {
				connector.closeStatement(st);
				lock.unlock();
			}
		}
		return dependencyId;
	}

	/**
	 * @param entity
	 * @return string representation of getDependence statement specified with the
	 *         entity type
	 */
	protected abstract String getDependenceStatement(EntityBase entity);

	protected abstract String updateDependenceStatement();

	public void deleteDependence(M entity, K dependency) throws DAOTechnicalException {
		if (entity != null) {
			PreparedStatement st = null;
			try {
				lock.lock();
				st = connector.prepareStatement(deleteDependenceStatement());
				st.setInt(1, entity.getId());
				st.setInt(2, dependency.getId());
				st.executeUpdate();
			} catch (SQLException e) {
				throw new DAOTechnicalException(e);
			} finally {
				connector.closeStatement(st);
				lock.unlock();
			}
		}
	}

	public void deleteDependence(int entityId, int dependenceId) throws DAOTechnicalException {
		if (entityId > 0 && dependenceId > 0) {
			PreparedStatement st = null;
			try {
				lock.lock();
				st = connector.prepareStatement(deleteDependenceStatement());
				st.setInt(1, entityId);
				st.setInt(2, dependenceId);
				st.executeUpdate();
			} catch (SQLException e) {
				throw new DAOTechnicalException(e);
			} finally {
				connector.closeStatement(st);
				lock.unlock();
			}
		}
	}

	protected abstract String deleteDependenceStatement();

}