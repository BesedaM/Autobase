package by.epam.javatraining.beseda.webproject.model.dao.entitydao;

import by.epam.javatraining.beseda.webproject.model.entity.route.Route;
import by.epam.javatraining.beseda.webproject.util.resourceloader.DatabaseEnumLoader;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.epam.javatraining.beseda.webproject.util.database.DBEntityTableName.ROUTE_NAME;
import static by.epam.javatraining.beseda.webproject.util.database.DBEnumTable.ROUTE_STATUS;
import static by.epam.javatraining.beseda.webproject.util.database.SQLQuery.*;

public class RouteDAO extends AbstractDAO<Route>{

    private static RouteDAO instance = null;

    private RouteDAO() {
        super();
    }

    public static RouteDAO getDAO() {
        if (instance == null) {
            instance = new RouteDAO();
        }
        return instance;
    }

    @Override
    protected Route createEntity(ResultSet result) throws SQLException{
        Route route=new Route();
        route.setId(result.getInt(ROUTE_ID));
        route.setName(result.getString(ROUTE_NAME));
        route.setStatus(result.getString(ROUTE_STATUS));
        return route;
    }

    @Override
    protected String getAllStatement() {
        return SELECT_ALL_ROUTES;
    }

    @Override
    protected String findEntityByIdStatement() {
        return SELECT_ROUTE_BY_ID;
    }

    @Override
    protected String deleteStatement(){
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
    protected void setDataOnPreparedStatement(PreparedStatement st, Route route) throws SQLException {
        int routeStatusIndex=DatabaseEnumLoader.ROUTE_STATUS_MAP.getKey(route.getStatus());
        st.setString(1, route.getName());
        st.setInt(2, routeStatusIndex);
    }
}
