package by.epam.javatraining.beseda.webproject.dao.entitydao;

import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.DELETE_TASK_BY_ID;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.INSERT_TASK;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.SELECT_ALL_TASKS;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.SELECT_TASKS_BY_ID_LIST;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.SELECT_TASK_BY_ID;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.TASK_ADDRESS_GET_ADDRESS_ID;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.TASK_ADDRESS_UPDATE_DEPENDENCE;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.TASK_ROUTE_GET_ROUTE_ID;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.TASK_ROUTE_UPDATE_DEPENDENCE;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.UPDATE_TASK;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBConstants.ZERO_VALUE;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.DETAILS;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.ID;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.TIME;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import by.epam.javatraining.beseda.webproject.dao.exception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.TaskInterface;
import by.epam.javatraining.beseda.webproject.dao.resultsetextractor.TaskExtraсtor;
import by.epam.javatraining.beseda.webproject.entity.route.Task;

@Repository
public class TaskDAO extends AbstractDAO<Task> implements TaskInterface {

	TaskDAO() {
		super();
	}

	public TaskDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Autowired
	@Qualifier("taskMapper")
	@Override
	protected void setRowMapper(RowMapper<Task> rowMapper) {
		this.rowMapper = rowMapper;
	}

	@Autowired
	@Qualifier("taskExtractor")
	@Override
	protected void setResultSetExtractor(ResultSetExtractor<Task> rsExtractor) {
		this.rsExtractor = rsExtractor;

	}

	@Override
	public void setRoute(int routeId, int taskId) {
		this.jdbcTemplate.update(TASK_ROUTE_UPDATE_DEPENDENCE, routeId, taskId);
	}

	@Override
	public int getRouteId(int taskId) {
		return this.jdbcTemplate.queryForObject(TASK_ROUTE_GET_ROUTE_ID, new Object[] { taskId }, Integer.class);
	}

	@Override
	public void deleteRoute(int taskId) {
		this.jdbcTemplate.update(TASK_ROUTE_UPDATE_DEPENDENCE, ZERO_VALUE, taskId);
	}

	@Override
	public void setAddress(int addressId, int taskId) {
		this.jdbcTemplate.update(TASK_ADDRESS_UPDATE_DEPENDENCE, addressId, taskId);
	}

	@Override
	public int getAddressId(int taskId) {
		return this.jdbcTemplate.queryForObject(TASK_ADDRESS_GET_ADDRESS_ID, new Object[] { taskId }, Integer.class);
	}

	@Override
	public void deleteAddress(int taskId) {
		this.jdbcTemplate.update(TASK_ADDRESS_UPDATE_DEPENDENCE, ZERO_VALUE, taskId);
	}

	@Override
	protected String getAllStatement() {
		return SELECT_ALL_TASKS;
	}

	@Override
	protected String getEntityByIdStatement() {
		return SELECT_TASK_BY_ID;
	}

	@Override
	protected String getEntityListByIdStatement() {
		return SELECT_TASKS_BY_ID_LIST;
	}

	@Override
	protected String deleteStatement() {
		return DELETE_TASK_BY_ID;
	}

	@Override
	protected String addStatement() {
		return INSERT_TASK;
	}

	@Override
	protected String updateStatement() {
		return UPDATE_TASK;
	}

	@Override
	protected MapSqlParameterSource createMapSqlParameterSource(Task entity) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		Timestamp timestamp = new Timestamp(entity.getTime().getTimeInMillis());
		parameters.addValue(TIME, timestamp);
		parameters.addValue(DETAILS, entity.getDetails());
		parameters.addValue(ID, entity.getId());
		return parameters;
	}
}