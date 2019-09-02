package by.epam.javatraining.beseda.webproject.dao.entitydao;

import by.epam.javatraining.beseda.webproject.dao.exception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.dao.util.database.SQLQuery;
import by.epam.javatraining.beseda.webproject.entity.route.Route;
import by.epam.javatraining.beseda.webproject.dao.exception.NotEnoughArgumentsException;
import by.epam.javatraining.beseda.webproject.entity.exception.EntityLogicException;
import by.epam.javatraining.beseda.webproject.dao.util.dataloader.DatabaseEnumLoader;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.epam.javatraining.beseda.webproject.dao.util.database.DBEntityTable.ROUTE_NAME;
import static by.epam.javatraining.beseda.webproject.dao.util.database.DBEnumTable.ROUTE_STATUS;
import static by.epam.javatraining.beseda.webproject.dao.util.database.SQLQuery.*;

public class RouteDAO extends AbstractDAO<Route> {

    RouteDAO() {
        super();
    }

    @Override
    protected Route createEntity(ResultSet result) throws SQLException, EntityLogicException {
        Route route = null;
        if (result != null) {
            route = new Route();
            route.setId(result.getInt(SQLQuery.ROUTE_ID));
            route.setName(result.getString(ROUTE_NAME));
            route.setStatus(result.getString(ROUTE_STATUS));
        }
        return route;
    }

    @Override
    public synchronized int add(Route route) throws DAOLayerException {
        int id = -1;
        if (route != null) {
            PreparedStatement st = null;
            try {
                st = connector.prepareStatement(addStatement());
                setDataOnPreparedStatement(st, route);
                st.setInt(3, route.getId());
                st.executeUpdate();
                id = route.getId();
            } catch (SQLException e) {
                throw new DAOTechnicalException("Error updating database", e);
            } finally {
                closeStatement(st);
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
    protected void setDataOnPreparedStatement(PreparedStatement st, Route route) throws SQLException, NotEnoughArgumentsException {
        if (st != null && route != null) {
            int routeStatusIndex = DatabaseEnumLoader.ROUTE_STATUS_MAP.getKey(route.getStatus());
            st.setString(1, route.getName());
            st.setInt(2, routeStatusIndex);
        } else {
            throw new NotEnoughArgumentsException();
        }
    }


    public synchronized void updateRouteStatus(int id, String status) throws DAOTechnicalException {
        if (id > 0 && status != null) {
            int routeStatusIndex = DatabaseEnumLoader.ROUTE_STATUS_MAP.getKey(status);
            PreparedStatement st = null;
            try {
                st = connector.prepareStatement(UPDATE_ROUTE_STATUS);
                st.setInt(1, routeStatusIndex);
                st.setInt(2, id);
                st.executeUpdate();
            } catch (SQLException e) {
                throw new DAOTechnicalException("Error updating route status", e);
            } finally {
                closeStatement(st);
            }
        }
    }


}
