package by.epam.javatraining.beseda.webproject.dao.dependencedao;

import by.epam.javatraining.beseda.webproject.dao.exception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.entity.EntityBase;
import by.epam.javatraining.beseda.webproject.entity.car.Car;
import by.epam.javatraining.beseda.webproject.entity.route.Route;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.epam.javatraining.beseda.webproject.dao.util.database.SQLQuery.*;

public class CarRouteDependenceDAO extends ManyToManyDependenceDAO<Car, Route> {

    CarRouteDependenceDAO() {
        super();
    }

    public int getActiveRouteId(Car car) throws DAOTechnicalException {
        int id = 0;
        if (car != null) {
            PreparedStatement st = null;
            try {
                String statement = CAR_ROUTE_GET_DEPENDENCE_ACTIVE_ROUTE_ID;
                st = connector.prepareStatement(statement);
                st.setInt(1, car.getId());
                ResultSet res = st.executeQuery();
                id = res.getInt(1);
            } catch (SQLException e) {
                throw new DAOTechnicalException("Error retrieving data from database", e);
            } finally {
                connector.closeStatement(st);
            }
        }
        return id;
    }


    public int[] getActivePlannedRoutesId(Car car) throws DAOTechnicalException {
        int[] ids = null;
        if (car != null) {
            PreparedStatement st = null;
            try {
                String statement = CAR_ROUTE_GET_DEPENDENCE_ACTIVE_PLANNED_ROUTE_ID;
                st = connector.prepareStatement(statement);
                st.setInt(1, car.getId());
                ResultSet res = st.executeQuery();
                res.last();
                ids = new int[res.getRow()];
                res.beforeFirst();
                int i = 0;
                while (res.next()) {
                    ids[i++] = res.getInt(1);
                }
            } catch (SQLException e) {
                throw new DAOTechnicalException("Error retrieving data from database", e);
            } finally {
                connector.closeStatement(st);
            }
        }
        return ids;
    }


    @Override
    protected String getDependenceStatement(EntityBase entity) {
        String statement = null;
        if (entity instanceof Car) {
            statement = CAR_ROUTE_GET_DEPENDENCE_ROUTE_ID;
        } else if (entity instanceof Route) {
            statement = CAR_ROUTE_GET_DEPENDENCE_CAR_ID;
        }
        return statement;
    }

    @Override
    protected String updateDependenceStatement() {
        return CAR_ROUTE_UPDATE_DEPENDENCE;
    }

    @Override
    protected String deleteDependenceStatement() {
        return CAR_ROUTE_DELETE_DEPENDENCE;
    }
}
