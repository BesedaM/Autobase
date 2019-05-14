package by.epam.javatraining.beseda.webproject.model.dao.dependencydao;

import static by.epam.javatraining.beseda.webproject.util.database.DBEntityTableName.ROUTE_ID_REQUESTS;
import static by.epam.javatraining.beseda.webproject.util.database.DBEntityTableName.T_REQUESTS;

public class RequestRouteDependencyDAO extends DependencyDAO {
    private static RequestRouteDependencyDAO ourInstance = new RequestRouteDependencyDAO();

    private RequestRouteDependencyDAO() {
        super();
        this.tableName = T_REQUESTS;
        this.columnName = ROUTE_ID_REQUESTS;
    }

    public static RequestRouteDependencyDAO getInstance() {
        return ourInstance;
    }
}
