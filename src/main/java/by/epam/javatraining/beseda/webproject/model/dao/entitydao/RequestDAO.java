package by.epam.javatraining.beseda.webproject.model.dao.entitydao;

import by.epam.javatraining.beseda.webproject.model.entity.Request;
import by.epam.javatraining.beseda.webproject.model.exception.EntityException.EntityLogicException;
import by.epam.javatraining.beseda.webproject.util.resourceloader.DatabaseEnumLoader;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.epam.javatraining.beseda.webproject.util.database.DBEntityTableName.COMMENT;
import static by.epam.javatraining.beseda.webproject.util.database.DBEnumTable.REQUEST_STATUS;
import static by.epam.javatraining.beseda.webproject.util.database.SQLQuery.*;

public class RequestDAO extends AbstractDAO<Request> {

    private static RequestDAO instance = null;

    private RequestDAO() {
        super();
    }

    public static RequestDAO getDAO() {
        if (instance == null) {
            instance = new RequestDAO();
        }
        return instance;
    }

    @Override
    protected Request createEntity(ResultSet res) throws SQLException, EntityLogicException {
        Request request = new Request();
        request.setId(res.getInt(REQUEST_ID));
        request.setStatus(res.getString(REQUEST_STATUS));
        request.setComment(res.getString(COMMENT));
        return request;
    }

    @Override
    protected String getAllStatement() {
        return SELECT_ALL_REQUESTS;
    }

    @Override
    protected String findEntityByIdStatement() {
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
    protected void setDataOnPreparedStatement(PreparedStatement st, Request request) throws SQLException {
        int statusId = DatabaseEnumLoader.REQUEST_STATUS_MAP.getKey(request.getStatus());
        st.setInt(1, statusId);
        st.setString(2, request.getComment());
    }
}
