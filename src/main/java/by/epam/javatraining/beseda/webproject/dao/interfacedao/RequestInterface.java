package by.epam.javatraining.beseda.webproject.dao.interfacedao;

import by.epam.javatraining.beseda.webproject.dao.exception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.entity.Request;

import java.util.List;

public interface RequestInterface extends EntityDAO<Request> {

	int[] selectActiveCustomerRequestsId(int customerId) throws DAOTechnicalException;

	List<Request> getNewRequests() throws DAOLayerException;

	int[] getCurrentRequestsId() throws DAOLayerException;

	int[] getFulfilledRequestsId() throws DAOLayerException;

	int[] getRejectedRequestsId() throws DAOLayerException;

}