package by.epam.javatraining.beseda.webproject.dao.entitydao;

import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.END_OF_STATEMENT;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBConstants.CLOSING_SQUARE_BRACKET;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBConstants.EMPTY_CHARACTER;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBConstants.OPENING_SQUARE_BRACKET;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBConstants.QUESTION_MARK;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBConstants.SPACE;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import by.epam.javatraining.beseda.webproject.connectionpool.ConnectionPool;
import by.epam.javatraining.beseda.webproject.dao.entitybuilder.EntityBuilder;
import by.epam.javatraining.beseda.webproject.dao.entitybuilder.EntityBuilderFactory;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.EntityDAO;
import by.epam.javatraining.beseda.webproject.dao.util.wrapperconnector.ConnectionWrap;
import by.epam.javatraining.beseda.webproject.dao.util.wrapperconnector.TestWrapperConnector;
import by.epam.javatraining.beseda.webproject.dao.util.wrapperconnector.WrapperConnector;
import by.epam.javatraining.beseda.webproject.entity.EntityBase;
import by.epam.javatraining.beseda.webproject.entity.exception.EntityLogicException;

/**
 * Abstract class, containing generic methods for entity DAO.
 *
 * @param <E> parameter type
 */
public abstract class AbstractDAO<E extends EntityBase> implements EntityDAO<E> {

	protected static EntityBuilderFactory entityBuilderFactory = EntityBuilderFactory.getFactory();

	protected ConnectionWrap connector;
	protected EntityBuilder<E> builder;
	protected final ReentrantLock lock = new ReentrantLock();

	protected AbstractDAO() {
		this.connector = new WrapperConnector();
	}

	protected AbstractDAO(ConnectionPool pool) {
		this.connector = new TestWrapperConnector(pool);
	}

	public List<E> getAll() throws DAOLayerException {
		List<E> list = new ArrayList<>();
		Statement st = null;
		E entity = null;
		try {
			lock.lock();
			st = connector.createStatement();
			ResultSet result = st.executeQuery(getAllStatement() + END_OF_STATEMENT);
			while (result.next()) {
				entity = builder.buildEntity(result);

				list.add(entity);
			}
		} catch (SQLException e) {
			throw new DAOTechnicalException(e);
		} catch (EntityLogicException e) {
			throw new DAOTechnicalException(e);
		} finally {
			connector.closeStatement(st);
			lock.unlock();
		}
		return list;
	}

	/**
	 * Return string representation of SQL 'select all' query.
	 */
	protected abstract String getAllStatement();

	public E getEntityById(int id) throws DAOLayerException {
		E entity = null;
		PreparedStatement st = null;
		try {
			lock.lock();
			st = connector.prepareStatement(getEntityByIdStatement());
			st.setInt(1, id);
			ResultSet res = st.executeQuery();
			if (res.first()) {
				entity = builder.buildEntity(res);
			}
		} catch (SQLException | EntityLogicException e) {
			throw new DAOTechnicalException(e);
		} finally {
			connector.closeStatement(st);
			lock.unlock();
		}
		return entity;
	}

	public List<E> getEntitiesByIdList(int[] idArr) throws DAOLayerException {
		List<E> list = new ArrayList<>();
		Statement st = null;
		try {
			lock.lock();
			st = connector.createStatement();
			String array = Arrays.toString(idArr);
			String newArr = array.replace(OPENING_SQUARE_BRACKET, SPACE).replace(CLOSING_SQUARE_BRACKET, SPACE)
					.replace(SPACE, EMPTY_CHARACTER);
			String modifiedStatement = getEntityListByIdStatement().replace(QUESTION_MARK, newArr);
			ResultSet res = st.executeQuery(modifiedStatement);

			while (res.next()) {
				E entity = builder.buildEntity(res);
				list.add(entity);
			}
		} catch (SQLException | EntityLogicException e) {
			throw new DAOTechnicalException(e);
		} finally {
			connector.closeStatement(st);
			lock.unlock();
		}
		return list;
	}

	protected abstract String getEntityListByIdStatement();

	/**
	 * Return string representation of SQL 'select by id' query.
	 */
	protected abstract String getEntityByIdStatement();


	public void delete(int id) throws DAOTechnicalException {
		PreparedStatement st = null;
		try {
			lock.lock();
			st = connector.prepareStatement(deleteStatement());
			st.setInt(1, id);
			st.executeUpdate();
		} catch (SQLException e) {
			throw new DAOTechnicalException(e);
		} finally {
			connector.closeStatement(st);
			lock.unlock();
		}
	}

	/**
	 * Return string representation of SQL 'delete entity' query.
	 */
	protected abstract String deleteStatement();

	public void delete(E entity) throws DAOTechnicalException {
		delete(entity.getId());
	}

	public int add(E entity) throws DAOLayerException {
		int id = -1;
		if (entity != null) {
			PreparedStatement st = null;
			try {
				lock.lock();
				st = connector.prepareStatementWithAutoGeneratedKeys(addStatement());
				setDataOnPreparedStatement(st, entity);
				st.executeUpdate();
				ResultSet rs = st.getGeneratedKeys();
				rs.next();
				id = rs.getInt(1);
			} catch (SQLException e) {
				throw new DAOTechnicalException(e);
			} finally {
				connector.closeStatement(st);
				lock.unlock();
			}
		}
		return id;
	}

	/**
	 * Sets data on prepared statement object.
	 *
	 * @param st     prepared statement
	 * @param entity source of data
	 * @throws SQLException
	 * @throws DAOTechnicalException
	 */
	protected abstract void setDataOnPreparedStatement(PreparedStatement st, E entity)
			throws SQLException, DAOLayerException;

	/**
	 * Returns string representation of SQL 'add entity service' query.
	 */
	protected abstract String addStatement();

	public void update(E entity) throws DAOLayerException {
		if (entity != null) {
			PreparedStatement st = null;
			try {
				lock.lock();
				st = connector.prepareStatement(updateStatement());
				setDataOnPreparedStatement(st, entity);
				st.setInt(updateIdParameterNumber(), entity.getId());
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
	 * Returns string representation of SQL 'update entity' query.
	 */
	protected abstract String updateStatement();

	protected abstract int updateIdParameterNumber();

	public void close() {
		connector.closeConnector();
	}
}