package by.epam.javatraining.beseda.webproject.dao.entitybuilder;

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

import by.epam.javatraining.beseda.webproject.entity.exception.EntityLogicException;
import by.epam.javatraining.beseda.webproject.entity.user.Customer;

public class CustomerBuilder implements EntityBuilder<Customer>{

	protected CustomerBuilder() {}
	
	@Override
	public Customer buildEntity(ResultSet result) throws SQLException, EntityLogicException{
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
