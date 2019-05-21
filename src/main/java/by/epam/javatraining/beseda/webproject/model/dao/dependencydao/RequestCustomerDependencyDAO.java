package by.epam.javatraining.beseda.webproject.model.dao.dependencydao;

import by.epam.javatraining.beseda.webproject.model.entity.Request;
import by.epam.javatraining.beseda.webproject.model.entity.user.Customer;

import static by.epam.javatraining.beseda.webproject.util.database.DBEntityTableName.CUSTOMER_ID_REQUESTS;
import static by.epam.javatraining.beseda.webproject.util.database.DBEntityTableName.T_REQUESTS;

public class RequestCustomerDependencyDAO extends DependencyDAO<Request, Customer>{
    private static RequestCustomerDependencyDAO ourInstance = new RequestCustomerDependencyDAO();

    private RequestCustomerDependencyDAO() {
        super();
        this.tableName=T_REQUESTS;
        this.columnName=CUSTOMER_ID_REQUESTS;
    }

    public static RequestCustomerDependencyDAO getDAO() {
        return ourInstance;
    }
}
