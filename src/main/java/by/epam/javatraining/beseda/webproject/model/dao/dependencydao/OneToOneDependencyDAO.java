package by.epam.javatraining.beseda.webproject.model.dao.dependencydao;

import by.epam.javatraining.beseda.webproject.model.entity.BaseEntity;
import by.epam.javatraining.beseda.webproject.model.exception.daoexception.DAOTechnicalException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.epam.javatraining.beseda.webproject.util.database.SQLQuery.*;

public class OneToOneDependencyDAO<M extends BaseEntity, K extends BaseEntity> extends DependencyDAO<M,K>{

    @Override
    public int[] getDependencyId(M entity, K dependency) throws DAOTechnicalException {
        int[] selection = null;
        if (dependency != null) {
            PreparedStatement st = null;
            try {
                String str = getStatement();
                str = str.replace(FIRST_PARAMETER, this.columnName);
                str = str.replace(SECOND_PARAMETER, this.tableName);
                st = connector.prepareStatement(str);
                st.setInt(1, dependency.getId());
                ResultSet res = st.executeQuery();
                selection = new int[]{res.getInt(1)};
                res.first();
            } catch (SQLException e) {
                throw new DAOTechnicalException("Error retrieving data from database", e);
            } finally {
                connector.closeStatement(st);
            }
        }
        return selection;
    }


    /**
     * String representation of sql get statement
     *
     * @return string, containing sql query
     */
    protected String getStatement() {
        return FIND_ONE_TO_ONE_DEPENDENCY_ID;
    }
}