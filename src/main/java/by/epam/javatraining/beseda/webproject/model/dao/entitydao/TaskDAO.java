package by.epam.javatraining.beseda.webproject.model.dao.entitydao;

import by.epam.javatraining.beseda.webproject.model.entity.route.Task;
import by.epam.javatraining.beseda.webproject.model.dao.exception.NotEnoughArgumentsException;
import by.epam.javatraining.beseda.webproject.model.exception.entityexception.EntityLogicException;
import by.epam.javatraining.beseda.webproject.model.dao.util.database.SQLQuery;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.GregorianCalendar;

import static by.epam.javatraining.beseda.webproject.model.dao.util.database.DBEntityTable.DETAIL;
import static by.epam.javatraining.beseda.webproject.model.dao.util.database.DBEntityTable.TIME;
import static by.epam.javatraining.beseda.webproject.model.dao.util.database.SQLQuery.*;

public class TaskDAO extends AbstractDAO<Task> {

    public TaskDAO() {
        super();
    }

//    private static class SingletonHolder {
//        public static final TaskDAO instance = new TaskDAO();
//    }
//
//    public static TaskDAO getDAO() {
//        return SingletonHolder.instance;
//    }

    @Override
    protected Task createEntity(ResultSet result) throws SQLException, EntityLogicException {
        Task task = null;
        if (result != null) {
            task = new Task();
            GregorianCalendar time = new GregorianCalendar();
            time.setTimeInMillis(result.getTime(TIME).getTime());

            task.setTime(time);
            task.setId(result.getInt(SQLQuery.TASK_ID));
            task.setDetails(result.getString(DETAIL));
        }
        return task;
    }

    @Override
    protected String getAllStatement() {
        return SELECT_ALL_TASKS;
    }

    @Override
    protected String getEntityByIdStatement() {
        return SELECT_TASK_BY_ID;
    }

    @Override
    protected String deleteStatement() {
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
    protected void setDataOnPreparedStatement(PreparedStatement st, Task task) throws SQLException, NotEnoughArgumentsException {
        if(st!=null&&task!=null) {
            st.setTime(1, new Time(task.getTime().getTimeInMillis()));
            st.setString(2, task.getDetails());
        }else{
            throw new NotEnoughArgumentsException();
        }
    }
}
