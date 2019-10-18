package by.epam.javatraining.beseda.webproject.dao.entitydao;

import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.ADD_NEW_DRIVER;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.CAR_DRIVER_GET_CAR_ID;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.DELETE_DRIVER_BY_ID;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.SELECT_ALL_DRIVERS;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.SELECT_DRIVERS_BY_ID_LIST;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.SELECT_DRIVER_BY_ID;
import static by.epam.javatraining.beseda.webproject.dao.util.SQLQuery.UPDATE_DRIVER;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.ID;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.NAME;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.PHONE;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.SURNAME;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import by.epam.javatraining.beseda.webproject.dao.exception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.DriverInterface;
import by.epam.javatraining.beseda.webproject.entity.user.Driver;

@Repository
public class DriverDAO extends AbstractDAO<Driver> implements DriverInterface {

	DriverDAO() {
		super();
	}

	public DriverDAO(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
	}

	@Autowired
	@Qualifier("driverMapper")
	@Override
	protected void setRowMapper(RowMapper<Driver> rowMapper) {
		this.rowMapper = rowMapper;
	}

	@Autowired
	@Qualifier("driverExtractor")
	@Override
	protected void setResultSetExtractor(ResultSetExtractor<Driver> rsExtractor) {
		this.rsExtractor = rsExtractor;
	}
	
	@Override
	public int getCarId(int driverId) {
		return jdbcTemplate.queryForObject(CAR_DRIVER_GET_CAR_ID, new Object[] { driverId }, Integer.class);
	}

	@Override
	public int add(Driver entity) throws DAOLayerException {
		NamedParameterJdbcTemplate npjt=new NamedParameterJdbcTemplate(jdbcTemplate);
		npjt.update(addStatement(), createMapSqlParameterSource(entity));
		return entity.getId();
	}

//	@Override
//	public void update(Driver entity) throws DAOLayerException {
//		jdbcTemplate.update(updateStatement(), entity.getSurname(), entity.getName(), entity.getPhone(),
//				entity.getId());
//	}

	@Override
	protected String getAllStatement() {
		return SELECT_ALL_DRIVERS;
	}

	@Override
	protected String getEntityByIdStatement() {
		return SELECT_DRIVER_BY_ID;
	}

	@Override
	protected String getEntityListByIdStatement() {
		return SELECT_DRIVERS_BY_ID_LIST;
	}

	@Override
	protected String deleteStatement() {
		return DELETE_DRIVER_BY_ID;
	}

	@Override
	protected String addStatement() {
		return ADD_NEW_DRIVER;
	}

	@Override
	protected String updateStatement() {
		return UPDATE_DRIVER;
	}

	@Override
	protected MapSqlParameterSource createMapSqlParameterSource(Driver entity) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue(SURNAME, entity.getSurname());
		parameters.addValue(NAME, entity.getName());
		parameters.addValue(PHONE, entity.getPhone());
		parameters.addValue(ID, entity.getId());
		return parameters;
	}

}
