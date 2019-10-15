package by.epam.javatraining.beseda.webproject.dao.mapper;

import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.DETAILS;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.TIME;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.ID;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.GregorianCalendar;

import org.springframework.jdbc.core.RowMapper;

import by.epam.javatraining.beseda.webproject.entity.route.Task;

public class TaskMapper implements RowMapper<Task> {

	@Override
	public Task mapRow(ResultSet result, int rowNum) throws SQLException {
		Task task = null;
		if (result != null) {
			task = new Task();
			GregorianCalendar time = new GregorianCalendar();
			Timestamp ts = result.getTimestamp(TIME, new GregorianCalendar());
			time.setTimeInMillis(ts.getTime());
			task.setTime(time);
			task.setId(result.getInt(ID));
			task.setDetails(result.getString(DETAILS));
		}
		return task;
	}

}
