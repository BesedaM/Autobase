package by.epam.javatraining.beseda.webproject.model.dao.entitydao;

import by.epam.javatraining.beseda.webproject.model.entity.user.Customer;
import by.epam.javatraining.beseda.webproject.model.exception.DAOexception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.model.exception.DAOexception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.util.resourceloader.DatabaseEnumLoader;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.epam.javatraining.beseda.webproject.util.database.DBEntityTableName.*;
import static by.epam.javatraining.beseda.webproject.util.database.DBEnumTable.CUSTOMER_TYPE;
import static by.epam.javatraining.beseda.webproject.util.database.DBEnumTable.USER_CUSTOMER;
import static by.epam.javatraining.beseda.webproject.util.database.SQLQuery.*;

public class CustomerDAO extends AbstractDAO<Customer> {

    private static CustomerDAO instance = null;

    private CustomerDAO() {
        super();
    }

    public static CustomerDAO getDAO() {
        if (instance == null) {
            instance = new CustomerDAO();
        }
        return instance;
    }

    @Override
    public int add(Customer user) throws DAOLayerException {
        PreparedStatement st = null;
        int id = -1;
        try {
            st = connector.prepareStatement(addStatement());
            setDataOnPreparedStatement(st, user);
            st.setInt(7, user.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOTechnicalException("Error updating database", e);
        } finally {
            closeStatement(st);
        }
        return id;
    }

    @Override
    protected Customer createEntity(ResultSet result) throws SQLException {
        Customer customer = new Customer();
        customer.setRole(USER_CUSTOMER);
        customer.setId(result.getInt(CUSTOMER_ID));
        customer.setLogin(result.getString(LOGIN));
        customer.setPassword(result.getBytes(PASSWORD));
        customer.setCustomerType(result.getString(CUSTOMER_TYPE));
        customer.setCompanyName(result.getString(COMPANY_NAME));
        customer.setEmail(result.getString(EMAIL));
        return customer;
    }

    @Override
    protected String getAllStatement() {
        return SELECT_ALL_CUSTOMERS;
    }

    @Override
    protected String findEntityByIdStatement() {
        return SELECT_CUSTOMER_BY_ID;
    }

    @Override
    protected String deleteStatement() {
        return DELETE_CUSTOMER_BY_ID;
    }

    @Override
    protected String addStatement() {
        return ADD_NEW_CUSTOMER;
    }

    @Override
    protected String updateStatement() {
        return UPDATE_CUSTOMER;
    }

    @Override
    protected int updateIdParameterNumber() {
        return 7;
    }

    @Override
    protected void setDataOnPreparedStatement(PreparedStatement st, Customer user) throws SQLException {
        int roleIndex = DatabaseEnumLoader.USER_ROLE_MAP.getKey(user.getRole());
        st.setInt(1, roleIndex);
        st.setString(2, user.getName());
        st.setString(3, user.getSurname());
        st.setString(4, user.getPhone());
        st.setString(5, user.getEmail());
        st.setString(6, user.getCompanyName());
    }
}
