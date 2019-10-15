package by.epam.javatraining.beseda.webproject.dao.interfacedao;

import java.util.List;

import by.epam.javatraining.beseda.webproject.entity.Request;

public interface RequestInterface extends EntityDAO<Request> {

	List<Request> selectActiveCustomerRequests(int customerId);

	List<Request> getNewRequests();

	List<Request> getCurrentRequests();

	List<Request> getFulfilledRequests();

	List<Request> getRejectedRequests();
	
	void setCustomer(int customerId, int requestId);

	int getCustomerId(int requestId);

	void deleteCustomer(int customerId, int requestId);

}