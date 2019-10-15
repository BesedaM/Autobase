package by.epam.javatraining.beseda.webproject.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import by.epam.javatraining.beseda.webproject.entity.EnumElement;

public class EnumElementMapper implements RowMapper<EnumElement> {

	@Override
	public EnumElement mapRow(ResultSet result, int rowNum) throws SQLException {
		EnumElement elem = null;
			if (result != null) {
				elem = new EnumElement();
				elem.setNumber(result.getInt(1));
				elem.setValue(result.getString(2));
			}
		return elem;
	}

}
