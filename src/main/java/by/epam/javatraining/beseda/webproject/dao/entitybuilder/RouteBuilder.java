package by.epam.javatraining.beseda.webproject.dao.entitybuilder;

import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.ROUTE_NAME;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.ID;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.ROUTE_STATUS;

import java.sql.ResultSet;
import java.sql.SQLException;

import by.epam.javatraining.beseda.webproject.entity.exception.EntityLogicException;
import by.epam.javatraining.beseda.webproject.entity.route.Route;

public class RouteBuilder implements EntityBuilder<Route>{

	protected RouteBuilder() {}
	
	@Override
	public Route buildEntity(ResultSet result) throws SQLException, EntityLogicException{
        Route route = null;
        if (result != null) {
            route = new Route();
            route.setId(result.getInt(ID));
            route.setName(result.getString(ROUTE_NAME));
            route.setStatus(result.getString(ROUTE_STATUS));
        }
        return route;
	}
	

}
