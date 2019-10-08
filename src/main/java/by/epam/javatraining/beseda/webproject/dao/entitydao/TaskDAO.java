package by.epam.javatraining.beseda.webproject.dao.entitydao;

import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.DELETE_TASK_BY_ID;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.INSERT_TASK;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.SELECT_ALL_TASKS;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.SELECT_TASKS_BY_ID_LIST;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.SELECT_TASK_BY_ID;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.UPDATE_TASK;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.GregorianCalendar;

import by.epam.javatraining.beseda.webproject.connectionpool.ConnectionPool;
import by.epam.javatraining.beseda.webproject.dao.exception.NotEnoughArgumentsException;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.TaskInterface;
import by.epam.javatraining.beseda.webproject.entity.route.Task;

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
	protected int updateIdParameterNumber() {
		return 3;
	}

	@Override
	protected void setDataOnPreparedStatement(PreparedStatement st, Task task)
			throws SQLException, NotEnoughArgumentsException {
		if (st != null && task != null) {
			st.setTimestamp(1, (new Timestamp(task.getTime().getTimeInMillis())), new GregorianCalendar());
			st.setString(2, task.getDetails());
		} else {
			throw new NotEnoughArgumentsException();
		}
	}
}