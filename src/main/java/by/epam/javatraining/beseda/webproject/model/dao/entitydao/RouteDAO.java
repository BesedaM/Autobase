package by.epam.javatraining.beseda.webproject.model.dao.entitydao;

import by.epam.javatraining.beseda.webproject.model.entity.route.Route;
import by.epam.javatraining.beseda.webproject.model.exception.DAOexception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.model.exception.DAOexception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.util.database.DBEntityTableName;
import by.epam.javatraining.beseda.webproject.util.resourceloader.DatabaseEnumLoader;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static by.epam.javatraining.beseda.webproject.util.database.DBEntityTableName.ROUTE_NAME;
import static by.epam.javatraining.beseda.webproject.util.database.DBEnumTable.ROUTE_STATUS;
import static by.epam.javatraining.beseda.webproject.util.database.SQLQuery.*;

public class RouteDAO extends AbstractDAO<Route>{

    private Logger log = Logger.getLogger(RouteDAO.class.getSimpleName());
    private static RouteDAO instance = null;

    private RouteDAO() {
        super();
        this.dbTableName = DBEntityTableName.T_ROUTES;
    }

    public static RouteDAO getDAO() {
        if (instance == null) {
            instance = new RouteDAO();
        }
        return instance;
    }


    @Override
    public List<Route> getAll() throws DAOLayerException {
        List<Route> list = new ArrayList<>();
        Statement st = null;
        try {
            st = connector.createStatement();
            ResultSet result = st.executeQuery(SELECT_ALL_ROUTES + END_OF_STATEMENT);
            while (result.next()) {
                Route route = createRoute(result);
                list.add(route);
            }
        } catch (SQLException e) {
            throw new DAOTechnicalException("Error retrieving data from database", e);
        } finally {
            closeStatement(st);
        }
        return list;
    }

    private Route createRoute(ResultSet result) throws SQLException{
        Route route=new Route();
        route.setId(result.getInt(ROUTE_ID));
        route.setName(result.getString(ROUTE_NAME));
        route.setStatus(result.getString(ROUTE_STATUS));
        return route;
    }

    @Override
    public Route findEntityById(int id) throws DAOTechnicalException {
        PreparedStatement st = null;
        Route route = null;
        try {
            st = connector.prepareStatement(SELECT_ROUTE_BY_ID);
            st.setInt(1, id);
            ResultSet res = st.executeQuery();
            route = createRoute(res);
        } catch (SQLException e) {
            throw new DAOTechnicalException("Error retrieving data from database", e);
        } finally {
            closeStatement(st);
        }
        return route;
    }

    @Override
    protected String setDeleteStetement(){
        return DELETE_ROUTE_BY_ID;
    }

    @Override
    protected String setAddStetement() {
        return INSERT_ROUTE;
    }

    @Override
    public void update(Route route) throws DAOLayerException {
        PreparedStatement st = null;
        try {
            st = connector.prepareStatement(UPDATE_ROUTE);
            setDataOnPreparedStatement(st,route);
            st.setInt(3,route.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOTechnicalException("Error updating database", e);
        } finally {
            closeStatement(st);
        }
    }

    @Override
    protected void setDataOnPreparedStatement(PreparedStatement st, Route route) throws SQLException {
        int routeStatusIndex=DatabaseEnumLoader.ROUTE_STATUS_MAP.getKey(route.getStatus());
        st.setString(1, route.getName());
        st.setInt(2, routeStatusIndex);
    }
}
