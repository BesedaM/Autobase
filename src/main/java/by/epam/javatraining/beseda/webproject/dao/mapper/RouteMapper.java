package by.epam.javatraining.beseda.webproject.dao.mapper;

import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.ROUTE_NAME;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.ID;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.ROUTE_STATUS;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import by.epam.javatraining.beseda.webproject.entity.exception.EntityLogicException;
import by.epam.javatraining.beseda.webproject.entity.route.Route;

public class RouteMapper implements RowMapper<Route> {

//	private Logger log = Logger.getLogger(ERROR_LOGGER);

	@Override
	public Route mapRow(ResultSet result, int rowNum) throws SQLException {
		Route route = null;
		try {
	        if (result != null) {
	            route = new Route();
	            route.setId(result.getInt(ID));
	            route.setName(result.getString(ROUTE_NAME));
	            route.setStatus(result.getString(ROUTE_STATUS));
	        }
		} catch (EntityLogicException e) {
//			log.error(e);
		}
		return route;
	}

}