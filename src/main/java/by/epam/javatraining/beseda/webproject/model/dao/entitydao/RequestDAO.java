package by.epam.javatraining.beseda.webproject.model.dao.entitydao;

import by.epam.javatraining.beseda.webproject.model.entity.Request;
import by.epam.javatraining.beseda.webproject.model.exception.daoexception.NotEnoughArgumentsException;
import by.epam.javatraining.beseda.webproject.model.exception.entityexception.EntityLogicException;
import by.epam.javatraining.beseda.webproject.util.resourceloader.DatabaseEnumLoader;
import by.epam.javatraining.beseda.webproject.util.wrapperconnector.WrapperConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.GregorianCalendar;

import static by.epam.javatraining.beseda.webproject.util.database.DBEntityTableName.COMMENT;
import static by.epam.javatraining.beseda.webproject.util.database.DBEntityTableName.REQUEST_DATE;
import static by.epam.javatraining.beseda.webproject.util.database.DBEnumTable.REQUEST_STATUS;
import static by.epam.javatraining.beseda.webproject.util.database.SQLQuery.*;

public class RequestDAO extends AbstractDAO<Request> {

    private RequestDAO() {
        super();
    }

    private static class SingletonHolder {
        public static final RequestDAO instance = new RequestDAO();
    }

    public static RequestDAO getDAO() {
        return SingletonHolder.instance;
    }

    @Override
    protected Request createEntity(ResultSet res) throws SQLException, EntityLogicException {
        Request request = null;
        if (res != null) {
            request = new Request();
            request.setId(res.getInt(REQUEST_ID));
            request.setStatus(res.getString(REQUEST_STATUS));
            request.setComment(res.getString(COMMENT));

            GregorianCalendar time = new GregorianCalendar();
            time.setTimeInMillis(res.getTime(REQUEST_DATE).getTime());

            request.setDate(time);
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
        return 3;
    }

    @Override
    protected void setDataOnPreparedStatement(PreparedStatement st, Request request) throws SQLException, NotEnoughArgumentsException {
        if (st != null && request != null) {
            int statusId = DatabaseEnumLoader.REQUEST_STATUS_MAP.getKey(request.getStatus());
            st.setInt(1, statusId);
            st.setTime(2, new Time(request.getDate().getTimeInMillis()));
            st.setString(3, request.getComment());
        } else {
            throw new NotEnoughArgumentsException();
        }
    }
}
