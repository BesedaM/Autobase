package by.epam.javatraining.beseda.webproject.dao.resultsetextractor;

import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.DETAILS;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.TIME;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.ID;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.GregorianCalendar;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import by.epam.javatraining.beseda.webproject.entity.route.Task;

public class TaskExtraсtor implements ResultSetExtractor<Task> {

	@Override
	public Task extractData(ResultSet result) throws SQLException, DataAccessException {
		Task task = null;
		if (result.next()) {
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
