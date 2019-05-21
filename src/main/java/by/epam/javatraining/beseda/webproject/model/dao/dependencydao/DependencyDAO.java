package by.epam.javatraining.beseda.webproject.model.dao.dependencydao;

import by.epam.javatraining.beseda.webproject.model.entity.BaseEntity;
import by.epam.javatraining.beseda.webproject.model.exception.daoexception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.util.wrapperconnector.WrapperConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.epam.javatraining.beseda.webproject.util.database.SQLQuery.*;

public abstract class DependencyDAO<M extends BaseEntity, K extends BaseEntity> implements Dependency<M, K> {

    protected volatile WrapperConnector connector;
    protected String tableName;
    protected String columnName;

    protected DependencyDAO() {
        this.connector = new WrapperConnector();
    }

    @Override
    public void setDependency(M entity, K dependency) throws DAOTechnicalException {
        if (entity != null && dependency != null) {
            PreparedStatement st = null;
            try {
                String str = setStatement();
                str = str.replace(FIRST_PARAMETER, this.tableName);
                str = str.replace(SECOND_PARAMETER, this.columnName);
                st = connector.prepareStatement(str);
                st.setInt(1, entity.getId());
                st.setInt(2, dependency.getId());
                st.executeUpdate();
            } catch (SQLException e) {
                throw new DAOTechnicalException("Error retrieving data from database", e);
            } finally {
                connector.closeStatement(st);
            }
        }
    }

    /**
     * String representation of sql set statement
     *
     * @return string, containing sql query
     */
    protected String setStatement() {
        return UPDATE_DEPENDENCY;
    }

    public void deleteDependency(M entity, K dependency) throws DAOTechnicalException {
        if (entity != null && dependency != null) {
            PreparedStatement st = null;
            try {
                String str = setStatement();
                str = str.replace(FIRST_PARAMETER, this.tableName);
                str = str.replace(SECOND_PARAMETER, this.columnName);
                st = connector.prepareStatement(str);
                st.setInt(1, NULL);
                st.setInt(2, dependency.getId());
                st.executeUpdate();
            } catch (SQLException e) {
                throw new DAOTechnicalException("Error retrieving data from database", e);
            } finally {
                connector.closeStatement(st);
            }
        }
    }

    /**
     * Closes the Wrapper Connection instance
     */
    public void closeConnection() {
        connector.closeConnection();
    }
}
