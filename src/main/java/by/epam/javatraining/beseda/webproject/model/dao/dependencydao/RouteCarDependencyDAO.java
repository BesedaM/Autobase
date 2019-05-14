package by.epam.javatraining.beseda.webproject.model.dao.dependencydao;

import by.epam.javatraining.beseda.webproject.model.exception.DAOexception.DAOTechnicalException;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static by.epam.javatraining.beseda.webproject.util.database.DBEntityTableName.CAR_ID_ROUTES;
import static by.epam.javatraining.beseda.webproject.util.database.DBEntityTableName.T_CARS_IN_ROUTES;
import static by.epam.javatraining.beseda.webproject.util.database.SQLQuery.*;

public class RouteCarDependencyDAO extends DependencyDAO{

    private static RouteCarDependencyDAO instance=new RouteCarDependencyDAO();

    public RouteCarDependencyDAO() {
        super();
        this.tableName=T_CARS_IN_ROUTES;
        this.columnName=CAR_ID_ROUTES;
    }

    public static RouteCarDependencyDAO getDAO(){
        return instance;
    }

    @Override
    protected String setSetStatement() {
        return ADD_ROUTE_CAR_DEPENDENCY;
    }

    @Override
    protected String setGetStatement(){
        return FIND_CAR_DEPENDENCY_ID;
    }

    @Override
    public void deleteDependency(int routeId, int carId) throws DAOTechnicalException {
        if (routeId > 0 && carId > 0) {
            PreparedStatement st = null;
            try {
                String statement = DELETE_ROUTE_CAR_DEPENDENCY;
                statement.replace(FIRST_PARAMETER, this.tableName);
                st = connector.prepareStatement(statement);
                st.setInt(1, carId);
                st.setInt(2, routeId);
                st.executeUpdate();
            } catch (SQLException e) {
                throw new DAOTechnicalException("Error retrieving data from database", e);
            } finally {
                connector.closeStatement(st);
            }
        }
    }

}
