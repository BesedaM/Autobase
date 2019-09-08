package by.epam.javatraining.beseda.webproject.dao.entitydao;

import by.epam.javatraining.beseda.webproject.dao.exception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.RequestInterface;
import by.epam.javatraining.beseda.webproject.dao.util.database.SQLQuery;
import by.epam.javatraining.beseda.webproject.entity.Request;
import by.epam.javatraining.beseda.webproject.dao.exception.NotEnoughArgumentsException;
import by.epam.javatraining.beseda.webproject.entity.exception.EntityLogicException;
import by.epam.javatraining.beseda.webproject.dao.util.dataloader.DatabaseEnumLoader;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static by.epam.javatraining.beseda.webproject.dao.util.database.DBEntityTable.COMMENT;
import static by.epam.javatraining.beseda.webproject.dao.util.database.DBEntityTable.REQUEST_DATE;
import static by.epam.javatraining.beseda.webproject.dao.util.database.DBEnumTable.REQUEST_STATUS;
import static by.epam.javatraining.beseda.webproject.dao.util.database.SQLQuery.*;

public class RequestDAO extends AbstractDAO<Request> implements RequestInterface {

    RequestDAO() {
        super();
    }

    @Override
    protected Request buildEntity(ResultSet res) throws SQLException, EntityLogicException {
        Request request = null;
        if (res != null) {
            request = new Request();
            request.setId(res.getInt(SQLQuery.REQUEST_ID));
            request.setStatus(res.getString(REQUEST_STATUS));

            String comment = res.getString(COMMENT);
            if (comment != null) {
                request.setComment(res.getString(COMMENT));
            }
            GregorianCalendar time = new GregorianCalendar();
            time.setTimeInMillis(res.getDate(REQUEST_DATE).getTime());

            request.setCreationTime(time);
        }
        return request;
    }

    @Override
    protected String getAllStatement() {
        return SELECT_ALL_REQUESTS;
    }

    @Override
    protected String getEntityByIdStatement() {
        return SELECT_REQUEST_BY_ID;
    }

    @Override
    protected String getEntityListByIdStatement() {
        return SELECT_REQUESTS_BY_ID_LIST;
    }

    @Override
    protected String deleteStatement() {
        return DELETE_REQUEST_BY_ID;
    }

    @Override
    protected String addStatement() {
        return ADD_NEW_REQUEST;
    }

    @Override
    protected String updateStatement() {
        return UPDATE_REQUEST;
    }

    @Override
    protected int updateIdParameterNumber() {
        return 0;
    }

    @Override
    protected void setDataOnPreparedStatement(PreparedStatement st, Request request) throws SQLException, NotEnoughArgumentsException {
        if (st != null && request != null) {
            int statusId = DatabaseEnumLoader.REQUEST_STATUS_MAP.getKey(request.getStatus());
            st.setInt(1, statusId);
            st.setString(2, request.getComment());
            st.setInt(3, request.getCustomer().getId());
        } else {
            throw new NotEnoughArgumentsException();
        }
    }

    @Override
    public int[] selectActiveCustomerRequestsId(int customerId) throws DAOTechnicalException {
        int[] array = null;
        if (customerId > 0) {
            PreparedStatement st = null;
            try {
                lock.lock();
                st = connector.prepareStatement(SELECT_ACTIVE_CUSTOMER_REQUESTS_ID);
                st.setInt(1, customerId);
                ResultSet res = st.executeQuery();
                res.last();
                array = new int[res.getRow()];
                res.first();
                for (int i = 0; i < array.length; i++) {
                    array[i] = res.getInt(1);
                    res.next();
                }
            } catch (SQLException e) {
                throw new DAOTechnicalException("Error retrieving data from database", e);
            } finally {
                connector.closeStatement(st);
                lock.unlock();
            }
        }
        return array;
    }

    @Override
    public void update(Request request) throws DAOLayerException {
        if (request != null) {
            PreparedStatement st = null;
            try {
                lock.lock();
                st = connector.prepareStatement(updateStatement());
                int statusId = DatabaseEnumLoader.REQUEST_STATUS_MAP.getKey(request.getStatus());
                st.setInt(1, statusId);
                st.setString(2, request.getComment());
                st.setInt(3, request.getId());
                st.executeUpdate();
            } catch (SQLException e) {
                throw new DAOTechnicalException("Error updating database", e);
            } finally {
                connector.closeStatement(st);
                lock.unlock();
            }
        }
    }


    @Override
    public List<Request> getNewRequests() throws DAOLayerException {
        List<Request> list = new ArrayList<>();
        Statement st = null;
        Request entity = null;
        try {
            lock.lock();
            st = connector.createStatement();
            ResultSet result = st.executeQuery(SELECT_NEW_REQUESTS + END_OF_STATEMENT);
            while (result.next()) {
                entity = buildEntity(result);
                list.add(entity);
            }
        } catch (SQLException e) {
            throw new DAOTechnicalException("Error retrieving data from database", e);
        } catch (EntityLogicException e) {
            throw new DAOTechnicalException("Error creating entity", e);
        } finally {
            connector.closeStatement(st);
            lock.unlock();
        }
        return list;
    }

    private int[] getSpecifiedRequestsId(String sqlStatement) throws DAOTechnicalException {
        int[] array;
        Statement st = null;
        try {
            lock.lock();
            st = connector.createStatement();
            ResultSet result = st.executeQuery(sqlStatement + END_OF_STATEMENT);
            result.last();
            array = new int[result.getRow()];
            result.first();
            for (int i = 0; i < array.length; i++) {
                array[i] = result.getInt(1);
                result.next();
            }
        } catch (SQLException e) {
            throw new DAOTechnicalException("Error retrieving data from database", e);
        } finally {
            connector.closeStatement(st);
            lock.unlock();
        }
        return array;
    }

    @Override
    public int[] getCurrentRequestsId() throws DAOLayerException {
        return getSpecifiedRequestsId(SELECT_CURRENT_REQUESTS_ID);
    }

    @Override
    public int[] getFulfilledRequestsId() throws DAOLayerException {
        return getSpecifiedRequestsId(SELECT_FULFILLED_REQUESTS_ID);
    }

    @Override
    public int[] getRejectedRequestsId() throws DAOLayerException {
        return getSpecifiedRequestsId(SELECT_REJECTED_REQUESTS_ID);
    }

}
