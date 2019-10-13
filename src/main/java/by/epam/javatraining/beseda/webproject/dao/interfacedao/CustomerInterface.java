package by.epam.javatraining.beseda.webproject.dao.interfacedao;

import java.util.List;

import by.epam.javatraining.beseda.webproject.entity.user.Customer;

public interface CustomerInterface extends EntityDAO<Customer> {
	
	List<Integer> getRequestsId(int customerId);
}