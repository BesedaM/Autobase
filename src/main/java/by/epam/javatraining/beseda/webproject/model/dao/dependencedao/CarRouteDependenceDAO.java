package by.epam.javatraining.beseda.webproject.model.dao.dependencedao;

import by.epam.javatraining.beseda.webproject.model.entity.BaseEntity;
import by.epam.javatraining.beseda.webproject.model.entity.car.Car;
import by.epam.javatraining.beseda.webproject.model.entity.route.Route;

import static by.epam.javatraining.beseda.webproject.util.database.SQLQuery.*;

public class CarRouteDependenceDAO extends ManyToManyDependenceDAO<Car, Route> {

    private CarRouteDependenceDAO() {
        super();
    }

    private static class SingletonHolder {
        public static final CarRouteDependenceDAO instance = new CarRouteDependenceDAO();
    }

    public static CarRouteDependenceDAO getDAO() {
        return SingletonHolder.instance;
    }

    @Override
    protected String getDependencesStatement(BaseEntity entity) {
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
