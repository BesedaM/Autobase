package by.epam.javatraining.beseda.webproject.model.dao.dependencedao;

import by.epam.javatraining.beseda.webproject.model.entity.car.Car;
import by.epam.javatraining.beseda.webproject.model.entity.user.Driver;

import static by.epam.javatraining.beseda.webproject.util.database.SQLQuery.*;

public class CarDriverDependenceDAO extends OneToOneDependenceDAO<Car,Driver> {

    private CarDriverDependenceDAO() {
        super();
    }

    private static class SingletonHolder {
        public static final CarDriverDependenceDAO instance = new CarDriverDependenceDAO();
    }

    public static CarDriverDependenceDAO getDAO() {
        return SingletonHolder.instance;
    }


    @Override
    protected String getDependenceIdStatement() {
        return CAR_DRIVER_GET_DEPENDENCE_ID;
    }

    @Override
    protected String getEntityByDependenceStatement() {
        return CAR_DRIVER_GET_ENTITIES_ID;
    }

    @Override
    protected String updateDependenceStatement() {
        return CAR_DRIVER_UPDATE_DEPENDENCE;
    }
}
