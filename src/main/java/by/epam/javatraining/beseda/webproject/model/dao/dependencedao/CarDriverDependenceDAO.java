package by.epam.javatraining.beseda.webproject.model.dao.dependencedao;

import by.epam.javatraining.beseda.webproject.model.entity.car.Car;
import by.epam.javatraining.beseda.webproject.model.entity.user.Driver;
import by.epam.javatraining.beseda.webproject.util.wrapperconnector.WrapperConnector;

import static by.epam.javatraining.beseda.webproject.util.database.SQLQuery.CAR_DRIVER_GET_DEPENDENCE_ID;
import static by.epam.javatraining.beseda.webproject.util.database.SQLQuery.CAR_DRIVER_GET_ENTITIES_ID;
import static by.epam.javatraining.beseda.webproject.util.database.SQLQuery.CAR_DRIVER_UPDATE_DEPENDENCE;

public class CarDriverDependenceDAO extends OneToOneDependenceDAO<Car,Driver> {

//    private static CarDriverDependenceDAO instance = new CarDriverDependenceDAO();
//
//    private CarDriverDependenceDAO() {
//        super();
//    }
//
//    public static CarDriverDependenceDAO getDAO() {
//        return instance;
//    }


    public CarDriverDependenceDAO() {
        super();
    }

    public CarDriverDependenceDAO(WrapperConnector connector) {
        super(connector);
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
