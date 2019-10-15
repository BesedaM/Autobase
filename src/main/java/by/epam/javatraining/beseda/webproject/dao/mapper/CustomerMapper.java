package by.epam.javatraining.beseda.webproject.dao.mapper;

import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.COMPANY_NAME;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.EMAIL;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.ID;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.LOGIN;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.NAME;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.PASSWORD;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.PHONE;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEntityTable.SURNAME;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.CUSTOMER_TYPE;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.USER_CUSTOMER;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import by.epam.javatraining.beseda.webproject.entity.user.Customer;

public class CustomerMapper implements RowMapper<Customer> {


	@Override
	public Customer mapRow(ResultSet result, int rowNum) throws SQLException {
		Customer customer = null;
		if (result != null) {
			customer = new Customer();
			customer.setRole(USER_CUSTOMER);
			customer.setId(result.getInt(ID));
			customer.setLogin(result.getString(LOGIN));
			customer.setPassword(result.getBytes(PASSWORD));
			customer.setName(result.getString(NAME));
			customer.setSurname(result.getString(SURNAME));
			customer.setPhone(result.getString(PHONE));
			customer.setCustomerType(result.getString(CUSTOMER_TYPE));
			customer.setCompanyName(result.getString(COMPANY_NAME));
			customer.setEmail(result.getString(EMAIL));
		}
		return customer;
	}

}
