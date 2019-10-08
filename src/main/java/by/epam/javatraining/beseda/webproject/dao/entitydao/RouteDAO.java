package by.epam.javatraining.beseda.webproject.dao.entitydao;

import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.DELETE_ROUTE_BY_ID;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.INSERT_ROUTE;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.SELECT_ALL_ROUTES;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.SELECT_ROUTES_BY_ID_LIST;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.SELECT_ROUTE_BY_ID;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.UPDATE_ROUTE;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.UPDATE_ROUTE_STATUS;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import by.epam.javatraining.beseda.webproject.connectionpool.ConnectionPool;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.dao.exception.NotEnoughArgumentsException;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.RouteInterface;
import by.epam.javatraining.beseda.webproject.dao.util.dataloader.DatabaseEnumLoader;
import by.epam.javatraining.beseda.webproject.entity.route.Route;

public class RouteDAO extends AbstractDAO<Route> implements RouteInterface {

	static Logger log = Logger.getLogger("console");

	{
		builder = entityBuilderFactory.getRouteBuilder();
	}

	RouteDAO() {
		super();
	}

	RouteDAO(ConnectionPool pool) {
		super(pool);
	}

	@Override
	public int add(Route route) throws DAOLayerException {
		int id = -1;
		if (route != null) {
			PreparedStatement st = null;
			try {
				lock.lock();
				st = connector.prepareStatement(addStatement());
				setDataOnPreparedStatement(st, route);
				st.setInt(3, route.getId());
				st.executeUpdate();
				id = route.getId();
			} catch (SQLException e) {
				throw new DAOTechnicalException(e);
			} finally {
				connector.closeStatement(st);
				lock.unlock();
			}
		}
		return id;
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
	protected int updateIdParameterNumber() {
		return 3;
	}

	@Override
	protected void setDataOnPreparedStatement(PreparedStatement st, Route route)
			throws SQLException, NotEnoughArgumentsException, DAOLayerException {
		if (st != null && route != null) {
			try {
				int routeStatusIndex = -1;
				if (DatabaseEnumLoader.ROUTE_STATUS_MAP.getKey(route.getStatus()) != null) {
					routeStatusIndex = DatabaseEnumLoader.ROUTE_STATUS_MAP.getKey(route.getStatus());
				} else {
					log.debug("BEFORE exception thrown");
					throw new DAOLayerException("Incorrect enum value");
				}
				st.setString(1, route.getName());
				st.setInt(2, routeStatusIndex);
			} catch (SQLException e) {
				throw new DAOLayerException(e);
			}
		} else {
			throw new NotEnoughArgumentsException();
		}
	}

	@Override
	public void updateRouteStatus(int id, String status) throws DAOTechnicalException {
		if (id > 0 && status != null) {
			int routeStatusIndex = -1;
			if (DatabaseEnumLoader.ROUTE_STATUS_MAP.getKey(status) != null) {
				routeStatusIndex = DatabaseEnumLoader.ROUTE_STATUS_MAP.getKey(status);
				PreparedStatement st = null;
				try {
					lock.lock();
					st = connector.prepareStatement(UPDATE_ROUTE_STATUS);
					st.setInt(1, routeStatusIndex);
					st.setInt(2, id);
					st.executeUpdate();
				} catch (SQLException e) {
					throw new DAOTechnicalException(e);
				} finally {
					connector.closeStatement(st);
					lock.unlock();
				}
			}
		}
	}

}
