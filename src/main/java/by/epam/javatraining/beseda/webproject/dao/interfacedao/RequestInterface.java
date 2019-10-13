package by.epam.javatraining.beseda.webproject.dao.interfacedao;

import by.epam.javatraining.beseda.webproject.dao.exception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.entity.Request;

import java.util.List;

public interface RequestInterface extends EntityDAO<Request> {

	List<Request> selectActiveCustomerRequests(int customerId) throws DAOTechnicalException;

	List<Request> getNewRequests() throws DAOLayerException;

	List<Request> getCurrentRequests() throws DAOLayerException;

	List<Request> getFulfilledRequests() throws DAOLayerException;

	List<Request> getRejectedRequests() throws DAOLayerException;
	
	void setCustomer(int customerId, int requestId);

	int getCustomerId(int requestId);

	void deleteCustomer(int customerId, int requestId);

}