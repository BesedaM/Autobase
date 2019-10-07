package by.epam.javatraining.beseda.webproject.dao.entitydao;

import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.DELETE_ADDRESS_BY_ID;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.INSERT_ADDRESS;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.SELECT_ADDRESSES_BY_ID_LIST;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.SELECT_ADDRESS_BY_ID;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.SELECT_ALL_ADDRESSES;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.UPDATE_ADDRESS;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import by.epam.javatraining.beseda.webproject.connectionpool.ConnectionPool;
import by.epam.javatraining.beseda.webproject.dao.exception.NotEnoughArgumentsException;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.AddressInterface;
import by.epam.javatraining.beseda.webproject.entity.route.Address;

public class AddressDAO extends AbstractDAO<Address> implements AddressInterface {

	{
		builder = entityBuilderFactory.getAddressBuilder();
	}

	AddressDAO() {
		super();
	}

	AddressDAO(ConnectionPool pool) {
		super(pool);
	}


	@Override
	protected String getAllStatement() {
		return SELECT_ALL_ADDRESSES;
	}

	@Override
	protected String getEntityByIdStatement() {
		return SELECT_ADDRESS_BY_ID;
	}

	@Override
	protected String getEntityListByIdStatement() {
		return SELECT_ADDRESSES_BY_ID_LIST;
	}

	@Override
	protected String deleteStatement() {
		return DELETE_ADDRESS_BY_ID;
	}

	@Override
	protected String addStatement() {
		return INSERT_ADDRESS;
	}

	@Override
	protected String updateStatement() {
		return UPDATE_ADDRESS;
	}

	@Override
	protected int updateIdParameterNumber() {
		return 7;
	}

	@Override
	protected void setDataOnPreparedStatement(PreparedStatement st, Address addr)
			throws SQLException, NotEnoughArgumentsException {
		if (st != null && addr != null) {
			st.setString(1, addr.getCountry());
			st.setString(2, addr.getDistrict());
			st.setString(3, addr.getCity());
			st.setString(4, addr.getStreet());
			st.setInt(5, addr.getHouse());
			st.setString(6, addr.getBuilding());
		} else {
			throw new NotEnoughArgumentsException();
		}
	}

}
