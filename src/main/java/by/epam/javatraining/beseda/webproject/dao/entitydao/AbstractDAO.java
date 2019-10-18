package by.epam.javatraining.beseda.webproject.dao.entitydao;

import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBConstants.CLOSING_SQUARE_BRACKET;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBConstants.EMPTY_CHARACTER;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBConstants.OPENING_SQUARE_BRACKET;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBConstants.QUESTION_MARK;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBConstants.SPACE;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.ID;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import by.epam.javatraining.beseda.webproject.dao.exception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.EntityDAO;
import by.epam.javatraining.beseda.webproject.entity.EntityBase;

/**
 * Abstract class, containing generic methods for entity DAO.
 *
 * @param <E> parameter type
 */
public abstract class AbstractDAO<E extends EntityBase> implements EntityDAO<E> {

	@Autowired
	protected JdbcTemplate jdbcTemplate;

	protected RowMapper<E> rowMapper;

	protected ResultSetExtractor<E> rsExtractor;

	protected Object[] entityParam;

	protected AbstractDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	protected AbstractDAO() {
		this.jdbcTemplate = null;
	}

	protected abstract void setRowMapper(RowMapper<E> rowMapper);

	protected abstract void setResultSetExtractor(ResultSetExtractor<E> rsExtractor);

	@Override
	public List<E> getAll() {
		return jdbcTemplate.query(getAllStatement(), rowMapper);
	}

	@Override
	public E getEntityById(int id) {
		return jdbcTemplate.query(getEntityByIdStatement(), new Object[] { id }, rsExtractor);
	}

	@Override
	public int add(E entity) throws DAOLayerException {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		NamedParameterJdbcTemplate namedJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
		MapSqlParameterSource parameters = createMapSqlParameterSource(entity);
		int rowsNum = namedJdbcTemplate.update(addStatement(), parameters, keyHolder, new String[] { ID });
		if (rowsNum != 1) {
			throw new DAOLayerException("Error adding entity to database");
		}
		int id = keyHolder.getKey().intValue();
		entity.setId(id);
		return id;
	}

	@Override
	public void update(E entity) throws DAOLayerException {
		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);
		npjt.update(updateStatement(), createMapSqlParameterSource(entity));
	}

	@Override
	public void delete(int id) {
		jdbcTemplate.update(deleteStatement(), id);
	}

	@Override
	public List<E> getEntitiesByIdList(int[] idArr) {
		String array = Arrays.toString(idArr);
		String newArr = array.replace(OPENING_SQUARE_BRACKET, SPACE).replace(CLOSING_SQUARE_BRACKET, SPACE);
		String modifiedStatement = getEntityListByIdStatement().replace(QUESTION_MARK, newArr);
		return jdbcTemplate.query(modifiedStatement, rowMapper);
	}

	/**
	 * Return string representation of SQL 'select all' query.
	 */
	protected abstract String getAllStatement();

	protected abstract String getEntityListByIdStatement();

	/**
	 * Return string representation of SQL 'select by id' query.
	 */
	protected abstract String getEntityByIdStatement();

	/**
	 * Return string representation of SQL 'delete entity' query.
	 */
	protected abstract String deleteStatement();

	public void delete(E entity) {
		delete(entity.getId());
	}

	/**
	 * Sets data on prepared statement object.
	 *
	 * @param st     prepared statement
	 * @param entity source of data
	 * @throws SQLException
	 * @throws DAOTechnicalException
	 */
//	protected abstract void setDataOnPreparedStatement(PreparedStatement st, E entity)
//			throws SQLException, DAOLayerException;

	/**
	 * Returns string representation of SQL 'add entity service' query.
	 */
	protected abstract String addStatement();

	/**
	 * Method used in ADD method for inserting values to MapSqlParameterSource
	 * 
	 * @param entity source of values
	 * @return configured MapSqlParameterSource
	 */
	protected abstract MapSqlParameterSource createMapSqlParameterSource(E entity);

	/**
	 * Returns string representation of SQL 'update entity' query.
	 */
	protected abstract String updateStatement();

//	public void close() {
//		connector.closeConnector();
//	}
}