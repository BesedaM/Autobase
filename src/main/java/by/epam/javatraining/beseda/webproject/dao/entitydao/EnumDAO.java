package by.epam.javatraining.beseda.webproject.dao.entitydao;

import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.SELECT_ENUM;
import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import by.epam.javatraining.beseda.webproject.controller.util.Decoder;
import by.epam.javatraining.beseda.webproject.entity.EnumElement;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.util.ReversalHashMap;

/**
 * Class for getting data from tables, representing enum.
 *
 * @author Beseda
 */
@Repository
public class EnumDAO {

	private Logger log=Logger.getLogger(ERROR_LOGGER);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	@Qualifier("enumMapper")
	private RowMapper<EnumElement> rowMapper;

	public EnumDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * Method retrieves data from table, containing enum constants, and returns the
	 * ReversableHashMap representation of it.
	 *
	 * @param tableName name of the table from where the data will be retrieved
	 * @return ReversableHashMap<Integer , String> object with table data
	 */
	public ReversalHashMap<Integer, String> getEnumMap(String tableName) {
		ReversalHashMap<Integer, String> map = new ReversalHashMap<>();
		String sql = SELECT_ENUM + tableName;
		List<EnumElement> list = jdbcTemplate.query(sql, rowMapper);
		for (int i = 0; i < list.size(); i++) {
			try {
				map.put(list.get(i).getNumber(), Decoder.decode(list.get(i).getValue()));
			} catch (ServiceLayerException e) {
				log.error(e);
			}
		}
		return map;
	}
}