package by.epam.javatraining.beseda.webproject.model.dao.entitydao;

import by.epam.javatraining.beseda.webproject.model.dao.util.database.SQLQuery;
import by.epam.javatraining.beseda.webproject.model.entity.user.Customer;
import by.epam.javatraining.beseda.webproject.model.dao.exception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.model.dao.exception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.model.dao.exception.NotEnoughArgumentsException;
import by.epam.javatraining.beseda.webproject.model.exception.entityexception.EntityLogicException;
import by.epam.javatraining.beseda.webproject.model.dao.util.dataloader.DatabaseEnumLoader;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.epam.javatraining.beseda.webproject.model.dao.util.database.DBEntityTable.*;
import static by.epam.javatraining.beseda.webproject.model.dao.util.database.DBEnumTable.CUSTOMER_TYPE;
import static by.epam.javatraining.beseda.webproject.model.dao.util.database.DBEnumTable.USER_CUSTOMER;
import static by.epam.javatraining.beseda.webproject.model.dao.util.database.SQLQuery.*;

public class CustomerDAO extends AbstractDAO<Customer> {

    public CustomerDAO() {
        super();
    }


    @Override
    public synchronized int add(Customer user) throws DAOLayerException {
        int id = -1;
        if (user != null) {
            PreparedStatement st = null;
            try {
                st = connector.prepareStatement(addStatement());
                setDataOnPreparedStatement(st, user);
                id = user.getId();
                st.setInt(7, id);
                st.executeUpdate();
            } catch (SQLException e) {
                throw new DAOTechnicalException("Error updating database", e);
            } finally {
                closeStatement(st);
            }
        }
        return id;
    }

    @Override
    protected Customer createEntity(ResultSet result) throws SQLException, EntityLogicException {
        Customer customer = null;
        if (result != null) {
            customer = new Customer();
            customer.setRole(USER_CUSTOMER);
            customer.setId(result.getInt(SQLQuery.CUSTOMER_ID));
            customer.setLogin(result.getString(LOGIN));
            customer.setPassword(result.getBytes(PASSWORD));
            customer.setName(result.getString(NAME));
            customer.setSurname(result.getString(SURNAME));
            customer.setPhone(result.getString(PHONE));
            customer.setCustomerType(result.getString(CUSTOMER_TYPE));
            customer.setCompanyName(result.getString(COMPANY_NAME));
            customer.setEmail(result.getString(EMAIL));
        }
        return customer;
    }

    @Override
    protected String getAllStatement() {
        return SELECT_ALL_CUSTOMERS;
    }

    @Override
    protected String getEntityByIdStatement() {
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
    protected void setDataOnPreparedStatement(PreparedStatement st, Customer user) throws SQLException, NotEnoughArgumentsException {
        if (st != null && user != null) {
            int typeIndex = DatabaseEnumLoader.CUSTOMER_TYPE_MAP.getKey(user.getCustomerType());
            st.setInt(1, typeIndex);
            st.setString(2, user.getName());
            st.setString(3, user.getSurname());
            st.setString(4, user.getPhone());
            st.setString(5, user.getEmail());
            st.setString(6, user.getCompanyName());
        } else {
            throw new NotEnoughArgumentsException();
        }
    }
}
