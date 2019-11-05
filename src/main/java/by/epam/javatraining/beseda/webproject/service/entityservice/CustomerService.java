package by.epam.javatraining.beseda.webproject.service.entityservice;

import java.util.List;

import by.epam.javatraining.beseda.webproject.dao.interfacedao.EntityDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import by.epam.javatraining.beseda.webproject.dao.entitydao.CustomerDAO;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.CustomerInterface;
import by.epam.javatraining.beseda.webproject.entity.user.Customer;
import by.epam.javatraining.beseda.webproject.entity.user.User;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLogicException;

@Service
public class CustomerService extends AbstractEntityService<Customer> {

	public CustomerService() {
		super();
	}

	/**
	 * Creates entity with the given data.
	 * 
	 * @param userData
	 * @param name
	 * @param surname
	 * @param customerType
	 * @param phone
	 * @param email
	 * @param companyName
	 * @return
	 */
	public Customer createCustomer(User userData, String name, String surname, String customerType, String phone,
			String email, String companyName) {
		Customer customer = null;
		if (userData != null && name != null && surname != null && phone != null && email != null) {
			if (companyName == null) {
				companyName = "";
			}
			customer = new Customer(userData, name, surname, phone, customerType, email, companyName);
		}
		return customer;
	}

	@Qualifier("customerDAO")
	@Autowired
	@Override
	public void setEntityDAO(EntityDAO<Customer> entityDAO) {
		this.entityDAO=entityDAO;
	}

	@Override
	public void add(Customer entity) throws ServiceLayerException {
		if (entity != null) {
			try {
				entityDAO.add(entity);
			} catch (DAOLayerException e) {
				throw new ServiceLogicException(e);
			}
		}
	}

	public List<Integer> getRequestsId(int customerId) {
		return ((CustomerInterface) entityDAO).getRequestsId(customerId);
	}

}