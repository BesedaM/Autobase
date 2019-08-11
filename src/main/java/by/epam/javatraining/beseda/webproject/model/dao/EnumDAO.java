package by.epam.javatraining.beseda.webproject.model.dao;

import by.epam.javatraining.beseda.webproject.model.dao.exception.NotEnoughArgumentsException;
import by.epam.javatraining.beseda.webproject.util.LoggerName;
import by.epam.javatraining.beseda.webproject.util.ReversableHashMap;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static by.epam.javatraining.beseda.webproject.model.dao.util.database.SQLQuery.END_OF_STATEMENT;
import static by.epam.javatraining.beseda.webproject.model.dao.util.database.SQLQuery.SELECT_ENUM;

/**
 * Class for getting data from tables, representing enum
 *
 * @author Beseda
 */
public class EnumDAO {

    private static Logger log = Logger.getLogger(LoggerName.ERROR_LOGGER);

    /**
     * Method retrieves data from table, containing enum constants, and returns the ReversableHashMap representation of it
     *
     * @param statement statement object
     * @param tableName name of the table from where the data will be retrieved
     * @return ReversableHashMap<Integer , String> object with table data
     */
    public static ReversableHashMap<Integer, String> getEnumMap(Statement statement, String tableName) {
        ReversableHashMap<Integer, String> map = null;
        if (statement != null && tableName != null) {
            map = new ReversableHashMap<>();
            try {
                ResultSet result = statement.executeQuery(SELECT_ENUM + tableName + END_OF_STATEMENT);
                while (result.next()) {
                    map.put(result.getInt(1), result.getString(2));
                }
            } catch (SQLException e) {
                log.error("SQL exception. Error retrieving data from database: " + e);
            }
        } else {
            log.error(new NotEnoughArgumentsException());
        }
        return map;
    }

}
