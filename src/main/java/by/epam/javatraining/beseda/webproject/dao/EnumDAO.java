package by.epam.javatraining.beseda.webproject.dao;

import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.SELECT_ENUM;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import by.epam.javatraining.beseda.webproject.entity.EnumElement;
import by.epam.javatraining.beseda.webproject.util.ReversalHashMap;

/**
 * Class for getting data from tables, representing enum.
 *
 * @author Beseda
 */
public class EnumDAO {

	@Autowired
	private static JdbcTemplate jdbcTemplate;

	@Autowired
	@Qualifier("enumMapper")
	private static RowMapper<EnumElement> rowMapper;

	private EnumDAO() {
	}

	/**
	 * Method retrieves data from table, containing enum constants, and returns the
	 * ReversableHashMap representation of it.
	 *
	 * @param statement statement object
	 * @param tableName name of the table from where the data will be retrieved
	 * @return ReversableHashMap<Integer , String> object with table data
	 */
	public static ReversalHashMap<Integer, String> getEnumMap(String tableName) {
		ReversalHashMap<Integer, String> map = new ReversalHashMap<>();
		String sql = SELECT_ENUM + tableName;
		List<EnumElement> list = jdbcTemplate.query(sql, rowMapper);
		for (EnumElement element : list) {
			map.put(element.getNumber(), element.getValue());
		}
		return map;
	}
}