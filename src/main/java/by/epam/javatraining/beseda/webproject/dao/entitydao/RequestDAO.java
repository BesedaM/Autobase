package by.epam.javatraining.beseda.webproject.dao.entitydao;

import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.ADD_NEW_REQUEST;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.DELETE_REQUEST_BY_ID;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.END_OF_STATEMENT;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.SELECT_ACTIVE_CUSTOMER_REQUESTS_ID;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.SELECT_ALL_REQUESTS;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.SELECT_CURRENT_REQUESTS_ID;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.SELECT_FULFILLED_REQUESTS_ID;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.SELECT_NEW_REQUESTS;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.SELECT_REJECTED_REQUESTS_ID;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.SELECT_REQUESTS_BY_ID_LIST;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.SELECT_REQUEST_BY_ID;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.UPDATE_REQUEST;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.epam.javatraining.beseda.webproject.connectionpool.ConnectionPool;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.dao.exception.NotEnoughArgumentsException;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.RequestInterface;
import by.epam.javatraining.beseda.webproject.dao.util.dataloader.DatabaseEnumLoader;
import by.epam.javatraining.beseda.webproject.entity.Request;
import by.epam.javatraining.beseda.webproject.entity.exception.EntityLogicException;

public class RequestDAO extends AbstractDAO<Request> implements RequestInterface {

	{
		builder = entityBuilderFactory.getRequestBuilder();
	}

	RequestDAO() {
		super();
	}

	RequestDAO(ConnectionPool pool) {
		super(pool);
	}

	@Override
	protected String getAllStatement() {
		return SELECT_ALL_REQUESTS;
	}

	@Override
	protected String getEntityByIdStatement() {
		return SELECT_REQUEST_BY_ID;
	}

	@Override
	protected String getEntityListByIdStatement() {
		return SELECT_REQUESTS_BY_ID_LIST;
	}

	@Override
	protected String deleteStatement() {
		return DELETE_REQUEST_BY_ID;
	}

	@Override
	protected String addStatement() {
		return ADD_NEW_REQUEST;
	}

	@Override
	protected String updateStatement() {
		return UPDATE_REQUEST;
	}

	@Override
	protected int updateIdParameterNumber() {
		return 0;
	}

	@Override
	protected void setDataOnPreparedStatement(PreparedStatement st, Request request)
			throws SQLException, NotEnoughArgumentsException {
		if (st != null && request != null) {
			int statusId = DatabaseEnumLoader.REQUEST_STATUS_MAP.getKey(request.getStatus());
			st.setInt(1, statusId);
			st.setString(2, request.getComment());
			st.setInt(3, request.getCustomer().getId());
		} else {
			throw new NotEnoughArgumentsException();
		}
	}

	@Override
	public int[] selectActiveCustomerRequestsId(int customerId) throws DAOTechnicalException {
		int[] array = new int[0];
		if (customerId > 0) {
			PreparedStatement st = null;
			try {
				lock.lock();
				st = connector.prepareStatement(SELECT_ACTIVE_CUSTOMER_REQUESTS_ID);
				st.setInt(1, customerId);
				ResultSet res = st.executeQuery();
				res.last();
				array = new int[res.getRow()];
				res.first();
				for (int i = 0; i < array.length; i++) {
					array[i] = res.getInt(1);
					res.next();
				}
			} catch (SQLException e) {
				throw new DAOTechnicalException(e);
			} finally {
				connector.closeStatement(st);
				lock.unlock();
			}
		}
		return array;
	}

	@Override
	public void update(Request request) throws DAOLayerException {
		if (request != null) {
			PreparedStatement st = null;
			try {
				lock.lock();
				st = connector.prepareStatement(updateStatement());
				if (DatabaseEnumLoader.REQUEST_STATUS_MAP.getKey(request.getStatus()) != null) {
					int statusId = DatabaseEnumLoader.REQUEST_STATUS_MAP.getKey(request.getStatus());
					st.setInt(1, statusId);
					st.setString(2, request.getComment());
					st.setInt(3, request.getId());
					st.executeUpdate();
				}
			} catch (SQLException e) {
				throw new DAOTechnicalException(e);
			} finally {
				connector.closeStatement(st);
				lock.unlock();
			}
		}
	}

	@Override
	public List<Request> getNewRequests() throws DAOLayerException {
		List<Request> list = new ArrayList<>();
		Statement st = null;
		Request entity = null;
		try {
			lock.lock();
			st = connector.createStatement();
			ResultSet result = st.executeQuery(SELECT_NEW_REQUESTS + END_OF_STATEMENT);
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

	private int[] getSpecifiedRequestsId(String sqlStatement) throws DAOTechnicalException {
		int[] array = new int[0];
		Statement st = null;
		try {
			lock.lock();
			st = connector.createStatement();
			ResultSet result = st.executeQuery(sqlStatement + END_OF_STATEMENT);
			if (result.last()) {
				array = new int[result.getRow()];
				result.first();
				for (int i = 0; i < array.length; i++) {
					array[i] = result.getInt(1);
					result.next();
				}
			}
		} catch (SQLException e) {
			throw new DAOTechnicalException(e);
		} finally {
			connector.closeStatement(st);
			lock.unlock();
		}
		return array;
	}

	@Override
	public int[] getCurrentRequestsId() throws DAOLayerException {
		return getSpecifiedRequestsId(SELECT_CURRENT_REQUESTS_ID);
	}

	@Override
	public int[] getFulfilledRequestsId() throws DAOLayerException {
		return getSpecifiedRequestsId(SELECT_FULFILLED_REQUESTS_ID);
	}

	@Override
	public int[] getRejectedRequestsId() throws DAOLayerException {
		return getSpecifiedRequestsId(SELECT_REJECTED_REQUESTS_ID);
	}

}

