package by.epam.javatraining.beseda.webproject.dao.entitydao;

import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.ADD_NEW_CUSTOMER;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.DELETE_CUSTOMER_BY_ID;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.REQUEST_CUSTOMER_GET_REQUESTS_ID;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.SELECT_ALL_CUSTOMERS;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.SELECT_CUSTOMERS_BY_ID_LIST;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.SELECT_CUSTOMER_BY_ID;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.UPDATE_CUSTOMER;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.COMPANY_NAME;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.CUSTOMER_TYPE_ID_CUSTOMERS;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.EMAIL;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.ID;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.NAME;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.PHONE;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.SURNAME;
import static by.epam.javatraining.beseda.webproject.service.ServiceConstants.CUSTOMER_TYPE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import by.epam.javatraining.beseda.webproject.dao.exception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.CustomerInterface;
import by.epam.javatraining.beseda.webproject.entity.user.Customer;
import by.epam.javatraining.beseda.webproject.service.EnumMap;
import by.epam.javatraining.beseda.webproject.util.ReversalHashMap;

@Repository
public class CustomerDAO extends AbstractDAO<Customer> implements CustomerInterface {
	
	@Autowired
	@Qualifier("customerTypeMap")
	private ReversalHashMap<Integer, String> customerTypeMap;
	
	CustomerDAO() {
		super();
	}

	public CustomerDAO(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
	}

	@Override
	@Autowired
	@Qualifier("customerMapper")
	protected void setRowMapper(RowMapper<Customer> rowMapper) {
		this.rowMapper = rowMapper;
	}
	
	@Autowired
	@Qualifier("customerExtractor")
	@Override
	protected void setResultSetExtractor(ResultSetExtractor<Customer> rsExtractor) {
		this.rsExtractor = rsExtractor;
	}

	public List<Integer> getRequestsId(int customerId) {
		return jdbcTemplate.queryForList(REQUEST_CUSTOMER_GET_REQUESTS_ID, new Object[] { customerId }, Integer.class);
	}

	@Override
	public int add(Customer entity) throws DAOLayerException {
		NamedParameterJdbcTemplate npjt=new NamedParameterJdbcTemplate(jdbcTemplate);
		npjt.update(addStatement(), createMapSqlParameterSource(entity));
		return entity.getId();
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
	protected MapSqlParameterSource createMapSqlParameterSource(Customer entity) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		int typeIndex = customerTypeMap.getKey(entity.getCustomerType());
		parameters.addValue(CUSTOMER_TYPE_ID_CUSTOMERS, typeIndex);
		parameters.addValue(NAME, entity.getName());
		parameters.addValue(SURNAME, entity.getSurname());
		parameters.addValue(PHONE, entity.getPhone());
		parameters.addValue(EMAIL, entity.getEmail());
		parameters.addValue(COMPANY_NAME, entity.getCompanyName());
		parameters.addValue(ID, entity.getId());
		return parameters;
	}

}
