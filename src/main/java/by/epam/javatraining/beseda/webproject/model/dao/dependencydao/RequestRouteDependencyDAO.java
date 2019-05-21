package by.epam.javatraining.beseda.webproject.model.dao.dependencydao;

import by.epam.javatraining.beseda.webproject.model.entity.Request;
import by.epam.javatraining.beseda.webproject.model.entity.route.Route;

import static by.epam.javatraining.beseda.webproject.util.database.DBEntityTableName.ROUTE_ID_REQUESTS;
import static by.epam.javatraining.beseda.webproject.util.database.DBEntityTableName.T_REQUESTS;

public class RequestRouteDependencyDAO extends DependencyDAO<Request,Route> {
    private static RequestRouteDependencyDAO ourInstance = new RequestRouteDependencyDAO();

    private RequestRouteDependencyDAO() {
        super();
        this.tableName = T_REQUESTS;
        this.columnName = ROUTE_ID_REQUESTS;
    }

    public static RequestRouteDependencyDAO getDAO() {
        return ourInstance;
    }
}
