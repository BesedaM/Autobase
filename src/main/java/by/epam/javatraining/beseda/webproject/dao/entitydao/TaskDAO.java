package by.epam.javatraining.beseda.webproject.dao.entitydao;

import static by.epam.javatraining.beseda.webproject.dao.util.database.DBEntityTable.DETAILS;
import static by.epam.javatraining.beseda.webproject.dao.util.database.DBEntityTable.TIME;
import static by.epam.javatraining.beseda.webproject.dao.util.database.SQLQuery.DELETE_TASK_BY_ID;
import static by.epam.javatraining.beseda.webproject.dao.util.database.SQLQuery.INSERT_TASK;
import static by.epam.javatraining.beseda.webproject.dao.util.database.SQLQuery.SELECT_ALL_TASKS;
import static by.epam.javatraining.beseda.webproject.dao.util.database.SQLQuery.SELECT_TASKS_BY_ID_LIST;
import static by.epam.javatraining.beseda.webproject.dao.util.database.SQLQuery.SELECT_TASK_BY_ID;
import static by.epam.javatraining.beseda.webproject.dao.util.database.SQLQuery.UPDATE_TASK;
import static by.epam.javatraining.beseda.webproject.dao.util.database.DBConstants.TIME_ZONE_OFFSET_IN_MILLISECONDS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.GregorianCalendar;

import by.epam.javatraining.beseda.webproject.dao.exception.NotEnoughArgumentsException;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.TaskInterface;
import by.epam.javatraining.beseda.webproject.entity.exception.EntityLogicException;
import by.epam.javatraining.beseda.webproject.entity.route.Task;
import static by.epam.javatraining.beseda.webproject.dao.util.database.DBEnumTable.ID;

public class TaskDAO extends AbstractDAO<Task> implements TaskInterface {

	TaskDAO() {
		super();
	}

	@Override
	protected Task buildEntity(ResultSet result) throws SQLException, EntityLogicException {
		Task task = null;
		if (result != null) {
			task = new Task();
			GregorianCalendar time = new GregorianCalendar();
			time.setTimeInMillis(
					result.getTime(TIME).getTime() + result.getDate(TIME).getTime() 
					+ TIME_ZONE_OFFSET_IN_MILLISECONDS);
			task.setTime(time);
			task.setId(result.getInt(ID));
			task.setDetails(result.getString(DETAILS));
		}
		return task;
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