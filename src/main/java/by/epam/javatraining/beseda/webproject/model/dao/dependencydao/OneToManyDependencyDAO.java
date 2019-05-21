package by.epam.javatraining.beseda.webproject.model.dao.dependencydao;

import by.epam.javatraining.beseda.webproject.model.entity.BaseEntity;
import by.epam.javatraining.beseda.webproject.model.exception.daoexception.DAOTechnicalException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.epam.javatraining.beseda.webproject.util.database.SQLQuery.FIND_DEPENDENCY_ID;
import static by.epam.javatraining.beseda.webproject.util.database.SQLQuery.FIRST_PARAMETER;
import static by.epam.javatraining.beseda.webproject.util.database.SQLQuery.SECOND_PARAMETER;

public class OneToManyDependencyDAO<M extends BaseEntity, K extends BaseEntity> extends DependencyDAO<M,K>{

    @Override
    public int[] getDependencyId(M entity, K dependency) throws DAOTechnicalException {
        int[] selection = null;
        if (entity != null) {
            PreparedStatement st = null;
            try {
                String str = getStatement();
                str = str.replace(FIRST_PARAMETER, this.tableName);
                str = str.replace(SECOND_PARAMETER, this.columnName);
                st = connector.prepareStatement(str);
                st.setInt(1, entity.getId());

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
     * String representation of sql get statement
     *
     * @return string, containing sql query
     */
    protected String getStatement() {
        return FIND_DEPENDENCY_ID;
    }
}
