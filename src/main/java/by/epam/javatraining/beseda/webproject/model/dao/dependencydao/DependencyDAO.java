package by.epam.javatraining.beseda.webproject.model.dao.dependencydao;

import by.epam.javatraining.beseda.webproject.model.exception.DAOexception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.util.wrapperconnector.WrapperConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.epam.javatraining.beseda.webproject.util.database.SQLQuery.*;

public abstract class DependencyDAO implements Dependency {

    protected volatile WrapperConnector connector;
    protected String tableName;
    protected String columnName;

    protected DependencyDAO() {
        this.connector = new WrapperConnector();
    }

    public void setDependency(int entityId, int dependencyId) throws DAOTechnicalException {
        if (entityId > 0 && dependencyId > 0) {
            PreparedStatement st = null;
            try {
                String statement = setStatement();
                statement.replace(FIRST_PARAMETER, this.tableName);
                statement.replace(SECOND_PARAMETER, this.columnName);
                st = connector.prepareStatement(statement);
                st.setInt(1, dependencyId);
                st.setInt(2, entityId);
                st.executeUpdate();
            } catch (SQLException e) {
                throw new DAOTechnicalException("Error retrieving data from database", e);
            } finally {
                connector.closeStatement(st);
            }
        }
    }

    public int[] getDependencyId(int entityId) throws DAOTechnicalException {
        int[] selection = null;
        if (entityId > 0) {
            PreparedStatement st = null;
            try {
                String statement = getStatement();
                statement.replace(FIRST_PARAMETER, this.columnName);
                statement.replace(SECOND_PARAMETER, this.tableName);
                st = connector.prepareStatement(statement);
                st.setInt(1, entityId);

                ResultSet res = st.executeQuery();
                res.last();
                selection = new int[res.getRow()];
                res.first();
                for (int i = 0; i < selection.length; i++) {
                    selection[i] = res.getInt(1);
                    res.next();
                }
            } catch (SQLException e) {
                throw new DAOTechnicalException("Error retrieving data from database", e);
            } finally {
                connector.closeStatement(st);
            }
        }
        return selection;
    }

    /**
     * String representation of sql set statement
     * @return string, containing sql query
     */
    protected String setStatement(){
        return UPDATE_DEPENDENCY;
    }

    /**
     * String representation of sql get statement
     * @return string, containing sql query
     */
    protected String getStatement(){
        return FIND_DEPENDENCY_ID;
    }

    public void deleteDependency(int entityId, int dependencyId) throws DAOTechnicalException {
        if (entityId > 0 && dependencyId > 0) {
            setDependency(entityId, NULL);
        }
    }

    /**
     * Closes the Wrapper Connection instance
     */
    public void closeConnection() {
        connector.closeConnection();
    }
}
