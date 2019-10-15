package by.epam.javatraining.beseda.webproject.dao.mapper;

import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.COMMENT;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.REQUEST_DATE;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.ID;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.REQUEST_STATUS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;

import org.springframework.jdbc.core.RowMapper;

import by.epam.javatraining.beseda.webproject.entity.Request;

public class RequestMapper implements RowMapper<Request> {

	@Override
	public Request mapRow(ResultSet result, int rowNum) throws SQLException {
		Request request = null;
			if (result != null) {
				request = new Request();
				request.setId(result.getInt(ID));
				request.setStatus(result.getString(REQUEST_STATUS));

				String comment = result.getString(COMMENT);
				if (comment != null) {
					request.setComment(result.getString(COMMENT));
				}
				GregorianCalendar time = new GregorianCalendar();
				time.setTimeInMillis(result.getDate(REQUEST_DATE).getTime());

				request.setCreationTime(time);
			}
		return request;
	}

}