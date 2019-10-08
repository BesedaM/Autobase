package by.epam.javatraining.beseda.webproject.dao.entitydao;

import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.ADD_NEW_CUSTOMER;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.DELETE_CUSTOMER_BY_ID;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.SELECT_ALL_CUSTOMERS;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.SELECT_CUSTOMERS_BY_ID_LIST;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.SELECT_CUSTOMER_BY_ID;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.UPDATE_CUSTOMER;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import by.epam.javatraining.beseda.webproject.connectionpool.ConnectionPool;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.dao.exception.NotEnoughArgumentsException;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.CustomerInterface;
import by.epam.javatraining.beseda.webproject.dao.util.dataloader.DatabaseEnumLoader;
import by.epam.javatraining.beseda.webproject.entity.user.Customer;

public class CustomerDAO extends AbstractDAO<Customer> implements CustomerInterface {

	{
		builder = entityBuilderFactory.getCustomerBuilder();
	}

	CustomerDAO() {
		super();
	}

	CustomerDAO(ConnectionPool pool) {
		super(pool);
	}

	@Override
	public int add(Customer user) throws DAOLayerException {
		int id = -1;
		if (user != null) {
			PreparedStatement st = null;
			try {
				lock.lock();
				st = connector.prepareStatement(addStatement());
				setDataOnPreparedStatement(st, user);
				id = user.getId();
				st.setInt(7, id);
				st.executeUpdate();
			} catch (SQLException e) {
				throw new DAOTechnicalException(e);
			} finally {
				connector.closeStatement(st);
				lock.unlock();
			}
		}
		return id;
	}


	@Override
	protected String getAllStatement() {
		return SELECT_ALL_CUSTOMERS;
	}

	@Override
	protected String getEntityByIdStatement() {
		return SELECT_CUSTOMER_BY_ID;
	}

	@Override
	protected String getEntityListByIdStatement() {
		return SELECT_CUSTOMERS_BY_ID_LIST;
	}

	@Override
	protected String deleteStatement() {
		return DELETE_CUSTOMER_BY_ID;
	}

	@Override
	protected String addStatement() {
		return ADD_NEW_CUSTOMER;
	}

	@Override
	protected String updateStatement() {
		return UPDATE_CUSTOMER;
	}

	@Override
	protected int updateIdParameterNumber() {
		return 7;
	}

	@Override
	protected void setDataOnPreparedStatement(PreparedStatement st, Customer user)
			throws SQLException, NotEnoughArgumentsException {
		if (st != null && user != null) {
			int typeIndex = DatabaseEnumLoader.CUSTOMER_TYPE_MAP.getKey(user.getCustomerType());
			st.setInt(1, typeIndex);
			st.setString(2, user.getName());
			st.setString(3, user.getSurname());
			st.setString(4, user.getPhone());
			st.setString(5, user.getEmail());
			st.setString(6, user.getCompanyName());
		} else {
			throw new NotEnoughArgumentsException();
		}
	}
}
