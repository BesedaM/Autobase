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
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.ID;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.LOGIN;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.PASSWORD;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.ROLE_ID_USERS;
import static by.epam.javatraining.beseda.webproject.service.ServiceConstants.REQUEST_STATUS;
import static by.epam.javatraining.beseda.webproject.service.ServiceConstants.USER_ROLE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import by.epam.javatraining.beseda.webproject.dao.exception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.UserInterface;
import by.epam.javatraining.beseda.webproject.entity.Request;
import by.epam.javatraining.beseda.webproject.entity.route.Address;
import by.epam.javatraining.beseda.webproject.entity.user.User;
import by.epam.javatraining.beseda.webproject.service.EnumMap;
import by.epam.javatraining.beseda.webproject.util.ReversalHashMap;

@Repository
public class UserDAO extends AbstractDAO<User> implements UserInterface {

	@Autowired
	@Qualifier("userRoleMap")
	private ReversalHashMap<Integer, String> userRoleMap;
	
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

	@Autowired
	@Qualifier("userExtractor")
	@Override
	protected void setResultSetExtractor(ResultSetExtractor<User> rsExtractor) {
		this.rsExtractor = rsExtractor;
	}
	
	
	@Override
	public User getUserByLoginAndPassword(String login, byte[] password) {
		return jdbcTemplate.query(SELECT_USER_BY_LOGIN_AND_PASSWORD, new Object[] { login, password }, rsExtractor);
	}

	@Override
	public User getUserByLogin(String login){
		return jdbcTemplate.query(SELECT_USER_BY_LOGIN, new Object[] { login }, rsExtractor);
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
		int roleIndex = userRoleMap.getKey(entity.getRole());
		parameters.addValue(LOGIN, entity.getLogin());
		parameters.addValue(PASSWORD, entity.getPassword());
		parameters.addValue(ROLE_ID_USERS, roleIndex);
		parameters.addValue(ID, entity.getId());
		return parameters;
	}

}
