package by.epam.javatraining.beseda.webproject.dao.entitydao;

import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.ADD_NEW_REQUEST;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.DELETE_REQUEST_BY_ID;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.SELECT_ACTIVE_CUSTOMER_REQUESTS;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.SELECT_ALL_REQUESTS;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.SELECT_CURRENT_REQUESTS;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.SELECT_FULFILLED_REQUESTS;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.SELECT_NEW_REQUESTS;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.SELECT_REJECTED_REQUESTS;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.SELECT_REQUESTS_BY_ID_LIST;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.SELECT_REQUEST_BY_ID;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.UPDATE_REQUEST;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.REQUEST_CUSTOMER_UPDATE_DEPENDENCE;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.REQUEST_CUSTOMER_GET_CUSTOMER_ID;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBConstants.EMPTY_CHARACTER;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBConstants.QUESTION_MARK;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.COMMENT;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.CUSTOMER_ID_REQUESTS;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.REQUEST_STATUS_ID_REQUESTS;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBConstants.ZERO_VALUE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import by.epam.javatraining.beseda.webproject.dao.exception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.RequestInterface;
import by.epam.javatraining.beseda.webproject.dao.util.dataloader.DatabaseEnumLoader;
import by.epam.javatraining.beseda.webproject.entity.Request;

@Repository
public class RequestDAO extends AbstractDAO<Request> implements RequestInterface {

	public RequestDAO() {
		super();
	}

	public RequestDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Autowired
	@Qualifier("requestMapper")
	@Override
	protected void setRowMapper(RowMapper<Request> rowMapper) {
		this.rowMapper = rowMapper;
	}

	@Override
	public void setCustomer(int customerId, int requestId) {
		jdbcTemplate.update(REQUEST_CUSTOMER_UPDATE_DEPENDENCE, customerId, requestId);
	}

	@Override
	public int getCustomerId(int requestId) {
		return jdbcTemplate.update(REQUEST_CUSTOMER_GET_CUSTOMER_ID, requestId);
	}

	@Override
	public void deleteCustomer(int customerId, int requestId) {
		jdbcTemplate.update(REQUEST_CUSTOMER_UPDATE_DEPENDENCE, ZERO_VALUE, requestId);
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
	public List<Request> selectActiveCustomerRequests(int customerId) throws DAOTechnicalException {
		String sql = SELECT_ACTIVE_CUSTOMER_REQUESTS.replace(QUESTION_MARK, customerId + EMPTY_CHARACTER);
		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public List<Request> getNewRequests() throws DAOLayerException {
		return jdbcTemplate.query(SELECT_NEW_REQUESTS, rowMapper);
	}

	@Override
	public List<Request> getCurrentRequests() throws DAOLayerException {
		return jdbcTemplate.query(SELECT_CURRENT_REQUESTS, rowMapper);
	}

	@Override
	public List<Request> getFulfilledRequests() throws DAOLayerException {
		return jdbcTemplate.query(SELECT_FULFILLED_REQUESTS, rowMapper);
	}

	@Override
	public List<Request> getRejectedRequests() throws DAOLayerException {
		return jdbcTemplate.query(SELECT_REJECTED_REQUESTS, rowMapper);
	}

	@Override
	protected MapSqlParameterSource createMapSqlParameterSource(Request entity) {
		int statusId = DatabaseEnumLoader.REQUEST_STATUS_MAP.getKey(entity.getStatus());
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue(REQUEST_STATUS_ID_REQUESTS, statusId);
		parameters.addValue(COMMENT, entity.getComment());
		parameters.addValue(CUSTOMER_ID_REQUESTS, entity.getCustomer().getId());
		return parameters;
	}

	@Override
	protected Object[] createEntityParamArray(Request entity) {
		int statusId = DatabaseEnumLoader.REQUEST_STATUS_MAP.getKey(entity.getStatus());
		Object[] array = new Object[2];
		array[0] = statusId;
		array[1] = entity.getComment();
		return array;
	}

}
