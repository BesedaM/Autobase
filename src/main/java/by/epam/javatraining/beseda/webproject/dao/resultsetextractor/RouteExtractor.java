package by.epam.javatraining.beseda.webproject.dao.resultsetextractor;

import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.ROUTE_NAME;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.ID;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.ROUTE_STATUS;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import by.epam.javatraining.beseda.webproject.entity.route.Route;

public class RouteExtractor implements ResultSetExtractor<Route>{

	@Override
	public Route extractData(ResultSet result) throws SQLException, DataAccessException {
		Route route = null;
		if (result.next()) {
			route = new Route();
			route.setId(result.getInt(ID));
			route.setName(result.getString(ROUTE_NAME));
			route.setStatus(result.getString(ROUTE_STATUS));
		}
		return route;
	}

	
	
}
