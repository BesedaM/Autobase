package by.epam.javatraining.beseda.webproject.model.dao.entitydao;

import by.epam.javatraining.beseda.webproject.model.entity.route.Task;
import by.epam.javatraining.beseda.webproject.model.exception.DAOexception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.util.database.SQLQuery;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.GregorianCalendar;

import static by.epam.javatraining.beseda.webproject.util.database.DBEntityTableName.DETAIL;
import static by.epam.javatraining.beseda.webproject.util.database.DBEntityTableName.TIME;
import static by.epam.javatraining.beseda.webproject.util.database.SQLQuery.*;

public class TaskDAO extends AbstractDAO<Task> {

    private static TaskDAO instance = null;

    private TaskDAO() {
        super();
    }

    public static TaskDAO getDAO() {
        if (instance == null) {
            instance = new TaskDAO();
        }
        return instance;
    }

    @Override
    protected Task createEntity(ResultSet result) throws SQLException, DAOTechnicalException {
        Task task = new Task();

        GregorianCalendar time = new GregorianCalendar();
        time.setTimeInMillis(result.getTime(TIME).getTime());

        task.setTime(time);
        task.setId(result.getInt(SQLQuery.TASK_ID));
        task.setDetail(result.getString(DETAIL));
        return task;
    }

    @Override
    protected String getAllStatement() {
        return SELECT_ALL_TASKS;
    }

    @Override
    protected String findEntityByIdStatement() {
        return SELECT_TASK_BY_ID;
    }

    @Override
    protected String deleteStatement(){
        return DELETE_TASK_BY_ID;
    }

    @Override
    protected String addStatement() {
        return INSERT_TASK;
    }

    @Override
    protected String updateStatement() {
        return UPDATE_TASK;
    }

    @Override
    protected int updateIdParameterNumber() {
        return 3;
    }

    @Override
    protected void setDataOnPreparedStatement(PreparedStatement st, Task task) throws SQLException {
        st.setTime(1, new Time(task.getTime().getTimeInMillis()));
        st.setString(2, task.getDetail());
    }
}
