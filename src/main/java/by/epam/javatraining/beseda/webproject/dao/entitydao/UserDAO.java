package by.epam.javatraining.beseda.webproject.dao.entitydao;

import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.ADD_NEW_USER;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.DELETE_USER_BY_ID;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.SELECT_ALL_USERS;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.SELECT_USERS_BY_ID_LIST;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.SELECT_USER_BY_ID;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.SELECT_USER_BY_LOGIN;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.SELECT_USER_BY_LOGIN_AND_PASSWORD;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.UPDATE_USER;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.UPDATE_USER_PASSWORD;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.LOGIN;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.PASSWORD;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.ROLE_ID_USERS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import by.epam.javatraining.beseda.webproject.dao.exception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.UserInterface;
import by.epam.javatraining.beseda.webproject.dao.util.dataloader.DatabaseEnumLoader;
import by.epam.javatraining.beseda.webproject.entity.user.User;

@Repository
public class UserDAO extends AbstractDAO<User> implements UserInterface {

	public UserDAO(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
	}

	UserDAO() {
		super();
	}

	@Override
	@Autowired
	@Qualifier("userMapper")
	protected void setRowMapper(RowMapper<User> rowMapper) {
		this.rowMapper = rowMapper;
	}

//	@Override
//	public List<User> getAll() throws DAOLayerException {
//		return jdbcTemplate.query(getAllStatement(), rowMapper);
//	}
//
//	@Override
//	public User getEntityById(int id) throws DAOLayerException {
//		return jdbcTemplate.queryForObject(getEntityByIdStatement(), rowMapper, id);
//	}
//
//	@Override
//	public int add(User user) throws DAOLayerException {
//		KeyHolder keyHolder = new GeneratedKeyHolder();
//		NamedParameterJdbcTemplate namedJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
//
//		MapSqlParameterSource parameters = new MapSqlParameterSource();
//		int roleIndex = DatabaseEnumLoader.USER_ROLE_MAP.getKey(user.getRole());
//		parameters.addValue(LOGIN, user.getLogin());
//		parameters.addValue(PASSWORD, user.getPassword());
//		parameters.addValue(ROLE_ID_USERS, roleIndex);
//
//		int rowsNum = namedJdbcTemplate.update(addStatement(), parameters, keyHolder, new String[] { "id" });
//		if (rowsNum != 1) {
//			throw new DAOLayerException("Error adding entity to database");
//		}
//		int id = keyHolder.getKey().intValue();
//		try {
//			user.setId(id);
//		} catch (EntityIdException e) {
//			throw new DAOLayerException(e);
//		}
//		return id;
//	}
//
//	@Override
//	public void update(User user) throws DAOLayerException {
//		jdbcTemplate.update(updateStatement(), user.getLogin(), user.getPassword(), user.getRole(), user.getId());
//	}
//
//	@Override
//	public void delete(int id) throws DAOTechnicalException {
//		jdbcTemplate.update(deleteStatement(), id);
//	}
//
//	@Override
//	public List<User> getEntitiesByIdList(int[] idArr) throws DAOLayerException {
//		String array = Arrays.toString(idArr);
//		String newArr = array.replace(OPENING_SQUARE_BRACKET, SPACE).replace(CLOSING_SQUARE_BRACKET, SPACE)
//				.replace(SPACE, EMPTY_CHARACTER);
//		String modifiedStatement = getEntityListByIdStatement().replace(QUESTION_MARK, newArr);
//		return jdbcTemplate.query(modifiedStatement, rowMapper);
//	}

	@Override
	public User getUserByLoginAndPassword(String login, byte[] password) {
		return jdbcTemplate.queryForObject(SELECT_USER_BY_LOGIN_AND_PASSWORD, rowMapper, login, password);
	}

	@Override
	public User getUserByLogin(String login){
		return jdbcTemplate.queryForObject(SELECT_USER_BY_LOGIN, rowMapper, login);
	}

	@Override
	public void updatePassword(int id, byte[] password) {
		jdbcTemplate.update(UPDATE_USER_PASSWORD, password, id);
	}

	@Override
	protected String getAllStatement() {
		return SELECT_ALL_USERS;
	}

	@Override
	protected String getEntityByIdStatement() {
		return SELECT_USER_BY_ID;
	}

	@Override
	protected String getEntityListByIdStatement() {
		return SELECT_USERS_BY_ID_LIST;
	}

	@Override
	protected String deleteStatement() {
		return DELETE_USER_BY_ID;
	}

	@Override
	protected String addStatement() {
		return ADD_NEW_USER;
	}

	@Override
	protected String updateStatement() {
		return UPDATE_USER;
	}

	@Override
	protected MapSqlParameterSource createMapSqlParameterSource(User entity) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		int roleIndex = DatabaseEnumLoader.USER_ROLE_MAP.getKey(entity.getRole());
		parameters.addValue(LOGIN, entity.getLogin());
		parameters.addValue(PASSWORD, entity.getPassword());
		parameters.addValue(ROLE_ID_USERS, roleIndex);
		return parameters;
	}

	@Override
	protected Object[] createEntityParamArray(User entity) {
		Object[] array = new Object[3];
		array[0] = entity.getLogin();
		array[1] = entity.getPassword();
		array[2] = entity.getRole();
		return array;
	}

}
