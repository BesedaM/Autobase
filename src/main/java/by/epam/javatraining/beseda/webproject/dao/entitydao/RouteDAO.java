package by.epam.javatraining.beseda.webproject.dao.entitydao;

import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.DELETE_ROUTE_BY_ID;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.INSERT_ROUTE;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.SELECT_ALL_ROUTES;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.SELECT_ROUTES_BY_ID_LIST;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.SELECT_ROUTE_BY_ID;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.UPDATE_ROUTE;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.UPDATE_ROUTE_STATUS;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.ID;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.ROUTE_NAME;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.STATUS_ID_ROUTES;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import by.epam.javatraining.beseda.webproject.connectionpool.ConnectionPool;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.RouteInterface;
import by.epam.javatraining.beseda.webproject.dao.util.dataloader.DatabaseEnumLoader;
import by.epam.javatraining.beseda.webproject.entity.route.Route;
import by.epam.javatraining.beseda.webproject.entity.user.Driver;

@Repository
public class RouteDAO extends AbstractDAO<Route> implements RouteInterface {

	{
		builder = entityBuilderFactory.getRouteBuilder();
	}

	RouteDAO() {
		super();
	}

	RouteDAO(ConnectionPool pool) {
		super(pool);
	}

	@Autowired
	@Qualifier("routeMapper")
	@Override
	protected void setRowMapper(RowMapper<Route> rowMapper) {
		this.rowMapper = rowMapper;
	}

	@Override
	public int add(Route entity) throws DAOLayerException {
		jdbcTemplate.update(addStatement(), createEntityParamArray(entity),entity.getId());
		return entity.getId();
	}
	
	@Override
	protected String getAllStatement() {
		return SELECT_ALL_ROUTES;
	}

	@Override
	protected String getEntityByIdStatement() {
		return SELECT_ROUTE_BY_ID;
	}

	@Override
	protected String getEntityListByIdStatement() {
		return SELECT_ROUTES_BY_ID_LIST;
	}

	@Override
	protected String deleteStatement() {
		return DELETE_ROUTE_BY_ID;
	}

	@Override
	protected String addStatement() {
		return INSERT_ROUTE;
	}

	@Override
	protected String updateStatement() {
		return UPDATE_ROUTE;
	}


	@Override
	public void updateRouteStatus(int id, String status) throws DAOTechnicalException {
		int routeStatusIndex = DatabaseEnumLoader.ROUTE_STATUS_MAP.getKey(status);
		jdbcTemplate.update(UPDATE_ROUTE_STATUS, routeStatusIndex, id);
	}

	@Override
	protected MapSqlParameterSource createMapSqlParameterSource(Route entity) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue(ROUTE_NAME, entity.getName());
		parameters.addValue(STATUS_ID_ROUTES, 1);
		parameters.addValue(ID, entity.getId());
		return parameters;
	}

	@Override
	protected Object[] createEntityParamArray(Route entity) {
		Object[] array = new Object[2];
		int routeStatusIndex = DatabaseEnumLoader.ROUTE_STATUS_MAP.getKey(entity.getStatus());
		array[0] = entity.getName();
		array[1] = routeStatusIndex;
		return array;
	}

}
