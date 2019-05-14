package by.epam.javatraining.beseda.webproject.model.dao.entitydao;

import by.epam.javatraining.beseda.webproject.model.entity.user.Customer;
import by.epam.javatraining.beseda.webproject.model.exception.DAOexception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.model.exception.DAOexception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.util.database.DBEntityTableName;
import by.epam.javatraining.beseda.webproject.util.resourceloader.DatabaseEnumLoader;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static by.epam.javatraining.beseda.webproject.util.database.DBEntityTableName.*;
import static by.epam.javatraining.beseda.webproject.util.database.DBEnumTable.CUSTOMER_TYPE;
import static by.epam.javatraining.beseda.webproject.util.database.DBEnumTable.USER_CUSTOMER;
import static by.epam.javatraining.beseda.webproject.util.database.SQLQuery.*;
import static by.epam.javatraining.beseda.webproject.util.database.SQLQuery.CUSTOMER_ID;

public class CustomerDAO extends AbstractDAO<Customer> {

    private Logger log = Logger.getLogger(CustomerDAO.class.getSimpleName());
    private static CustomerDAO instance = null;


    private CustomerDAO() {
        super();
        this.dbTableName = DBEntityTableName.T_CUSTOMERS;
    }

    public static CustomerDAO getDAO() {
        if (instance == null) {
            instance = new CustomerDAO();
        }
        return instance;
    }

    @Override
    public List<Customer> getAll() throws DAOLayerException {
        List<Customer> list = new ArrayList<>();
        Statement st = null;
        try {
            st = connector.createStatement();
            ResultSet result = st.executeQuery(SELECT_ALL_CUSTOMERS + END_OF_STATEMENT);
            while (result.next()) {
                Customer customer = createCustomer(result);
                list.add(customer);
            }
        } catch (SQLException e) {
            throw new DAOTechnicalException("Error retrieving data from database", e);
        } finally {
            closeStatement(st);
        }
        return list;
    }

    @Override
    public Customer findEntityById(int id) throws DAOTechnicalException {
        PreparedStatement st = null;
        Customer customer;
        try {
            st = connector.prepareStatement(SELECT_CUSTOMER_BY_ID);
            st.setInt(1, id);
            ResultSet res = st.executeQuery();
            res.first();
            customer = createCustomer(res);
        } catch (SQLException e) {
            throw new DAOTechnicalException("Error retrieving data from database", e);
        } finally {
            closeStatement(st);
        }
        return customer;
    }


    protected Customer createCustomer(ResultSet result) throws SQLException {
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
    protected String setDeleteStetement(){
        return DELETE_CUSTOMER_BY_ID;
    }

    @Override
    protected String setAddStetement() {
        return ADD_NEW_CUSTOMER;
    }

    @Override
    public void update(Customer customer) throws DAOLayerException {
        PreparedStatement st = null;
        try {
            st = connector.prepareStatement(UPDATE_USER);
            setDataOnPreparedStatement(st, customer);
            st.setInt(7,customer.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOTechnicalException("Error updating database", e);
        } finally {
            closeStatement(st);
        }
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
