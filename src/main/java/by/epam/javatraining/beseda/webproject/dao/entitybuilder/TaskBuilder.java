package by.epam.javatraining.beseda.webproject.dao.entitybuilder;

import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.DETAILS;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.TIME;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.ID;

import java.sql.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;

import by.epam.javatraining.beseda.webproject.entity.exception.EntityLogicException;
import by.epam.javatraining.beseda.webproject.entity.route.Task;

public class TaskBuilder implements EntityBuilder<Task>{

	protected TaskBuilder() {}
	
	@Override
	public Task buildEntity(ResultSet result) throws SQLException, EntityLogicException{
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
