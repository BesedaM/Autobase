package by.epam.javatraining.beseda.webproject.dao.entitydao;

import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.DELETE_ADDRESS_BY_ID;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.INSERT_ADDRESS;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.SELECT_ADDRESSES_BY_ID_LIST;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.SELECT_ADDRESS_BY_ID;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.SELECT_ALL_ADDRESSES;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.UPDATE_ADDRESS;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.BUILDING;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.CITY;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.COUNTRY;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.DISTRICT;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.HOUSE_NUMBER;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.STREET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import by.epam.javatraining.beseda.webproject.connectionpool.ConnectionPool;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.AddressInterface;
import by.epam.javatraining.beseda.webproject.entity.route.Address;

@Repository
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


	@Autowired
	@Qualifier("addressMapper")
	@Override
	protected void setRowMapper(RowMapper<Address> rowMapper) {
		this.rowMapper = rowMapper;
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
	protected MapSqlParameterSource createMapSqlParameterSource(Address entity) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue(COUNTRY, entity.getCountry());
		parameters.addValue(DISTRICT, entity.getDistrict());
		parameters.addValue(CITY, entity.getCity());
		parameters.addValue(STREET, entity.getStreet());
		parameters.addValue(HOUSE_NUMBER, entity.getHouse());
		parameters.addValue(BUILDING, entity.getBuilding());
		return parameters;
	}

	@Override
	protected Object[] createEntityParamArray(Address entity) {
		Object[] array = new Object[6];
		array[0] = entity.getCountry();
		array[1] = entity.getDistrict();
		array[2] = entity.getCity();
		array[3] = entity.getStreet();
		array[4] = entity.getHouse();
		array[5] = entity.getBuilding();
		return array;
	}

}
