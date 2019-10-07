package by.epam.javatraining.beseda.webproject.dao.dependencedao;

import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.CAR_DRIVER_GET_DEPENDENCE_ID;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.CAR_DRIVER_GET_ENTITIES_ID;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.CAR_DRIVER_UPDATE_DEPENDENCE;

import by.epam.javatraining.beseda.webproject.connectionpool.ConnectionPool;
import by.epam.javatraining.beseda.webproject.entity.car.Car;
import by.epam.javatraining.beseda.webproject.entity.user.Driver;

public class CarDriverDependenceDAO extends OneToOneDependenceDAO<Car,Driver> {

    CarDriverDependenceDAO() {
        super();
    }

    CarDriverDependenceDAO(ConnectionPool pool) {
        super(pool);
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