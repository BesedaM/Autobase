package by.epam.javatraining.beseda.webproject.model.dao.dependencydao;

import static by.epam.javatraining.beseda.webproject.util.database.DBEntityTableName.CUSTOMER_ID_REQUESTS;
import static by.epam.javatraining.beseda.webproject.util.database.DBEntityTableName.T_REQUESTS;

public class RequestCustomerDependencyDAO extends DependencyDAO{
    private static RequestCustomerDependencyDAO ourInstance = new RequestCustomerDependencyDAO();

    private RequestCustomerDependencyDAO() {
        super();
        this.tableName=T_REQUESTS;
        this.tableName=CUSTOMER_ID_REQUESTS;
    }

    public static RequestCustomerDependencyDAO getInstance() {
        return ourInstance;
    }
}
