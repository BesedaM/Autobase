package by.epam.javatraining.beseda.webproject.dao.entitydao;

import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.DELETE_TASK_BY_ID;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.INSERT_TASK;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.SELECT_ALL_TASKS;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.SELECT_TASKS_BY_ID_LIST;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.SELECT_TASK_BY_ID;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.UPDATE_TASK;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.DETAILS;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.TIME;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import by.epam.javatraining.beseda.webproject.connectionpool.ConnectionPool;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.TaskInterface;
import by.epam.javatraining.beseda.webproject.entity.route.Task;

@Repository
public class TaskDAO extends AbstractDAO<Task> implements TaskInterface {

	{
		builder = entityBuilderFactory.getTaskBuilder();
	}

	TaskDAO() {
		super();
	}

	TaskDAO(ConnectionPool pool) {
		super(pool);
	}

	@Autowired
	@Qualifier("taskMapper")
	@Override
	protected void setRowMapper(RowMapper<Task> rowMapper) {
		this.rowMapper=rowMapper;
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
		parameters.addValue(TIME, entity.getTime());
		parameters.addValue(DETAILS, entity.getDetails());
		return parameters;
	}

	@Override
	protected Object[] createEntityParamArray(Task entity) {
		Object[] array = new Object[2];
		array[0] = entity.getTime();
		array[1] = entity.getDetails();
		return array;
	}
}