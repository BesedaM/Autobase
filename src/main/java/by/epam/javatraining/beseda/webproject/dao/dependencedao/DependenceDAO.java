package by.epam.javatraining.beseda.webproject.dao.dependencedao;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.locks.ReentrantLock;

import by.epam.javatraining.beseda.webproject.connectionpool.ConnectionPool;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.dao.util.wrapperconnector.ConnectionWrap;
import by.epam.javatraining.beseda.webproject.dao.util.wrapperconnector.TestWrapperConnector;
import by.epam.javatraining.beseda.webproject.dao.util.wrapperconnector.WrapperConnector;
import by.epam.javatraining.beseda.webproject.entity.EntityBase;

/**
 * Top hierarchy class for dependenceDAO.
 *
 * @param <M> entity type parameter
 * @param <K> dependence type parameter
 */
public abstract class DependenceDAO<M extends EntityBase, K extends EntityBase> {

	protected ConnectionWrap connector;
	protected final ReentrantLock lock = new ReentrantLock();

	protected DependenceDAO() {
		this.connector = new WrapperConnector();
	}

	protected DependenceDAO(ConnectionPool pool) {
		this.connector = new TestWrapperConnector(pool);
	}
	
	/**
	 * Sets the dependence to the object
	 *
	 * @param entity     object
	 * @param dependence dependency object
	 * @throws DAOTechnicalException
	 */
	public void setDependence(M entity, K dependence) throws DAOTechnicalException {
		if (entity != null && dependence != null) {
			PreparedStatement st = null;
			try {
				lock.lock();
				st = connector.prepareStatement(updateDependenceStatement());
				st.setInt(1, dependence.getId());
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

	/**
	 * Sets the dependence to the object
	 *
	 * @param entityId     entity id
	 * @param dependenceId dependency id
	 * @throws DAOTechnicalException
	 */
	public void setDependence(int entityId, int dependenceId) throws DAOTechnicalException {
		if (entityId > 0 && dependenceId > 0) {
			PreparedStatement st = null;
			try {
				lock.lock();
				st = connector.prepareStatement(updateDependenceStatement());
				st.setInt(1, dependenceId);
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

	/**
	 *
	 * @return string representation of update statement
	 */
	protected abstract String updateDependenceStatement();

	public void close() {
		lock.lock();
		connector.closeConnector();
		lock.unlock();
	}
}
