package by.epam.javatraining.beseda.webproject.dao.dependencedao;

import by.epam.javatraining.beseda.webproject.entity.car.Car;
import by.epam.javatraining.beseda.webproject.entity.user.Driver;

import static by.epam.javatraining.beseda.webproject.dao.util.database.SQLQuery.*;

public class CarDriverDependenceDAO extends OneToOneDependenceDAO<Car,Driver> {

    CarDriverDependenceDAO() {
        super();
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