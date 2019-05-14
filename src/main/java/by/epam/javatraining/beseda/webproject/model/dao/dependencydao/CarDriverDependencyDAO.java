package by.epam.javatraining.beseda.webproject.model.dao.dependencydao;

import static by.epam.javatraining.beseda.webproject.util.database.DBEntityTableName.DRIVER_ID;
import static by.epam.javatraining.beseda.webproject.util.database.DBEntityTableName.T_CARS;

public class CarDriverDependencyDAO extends DependencyDAO {

    private static CarDriverDependencyDAO instance = new CarDriverDependencyDAO();

    private CarDriverDependencyDAO() {
        super();
        this.tableName = T_CARS;
        this.columnName = DRIVER_ID;
    }

    public static CarDriverDependencyDAO getDAO() {
        return instance;
    }

}
