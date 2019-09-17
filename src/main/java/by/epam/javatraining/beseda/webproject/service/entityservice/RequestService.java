package by.epam.javatraining.beseda.webproject.service.entityservice;

import by.epam.javatraining.beseda.webproject.dao.entitydao.RequestDAO;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.entity.Request;
import by.epam.javatraining.beseda.webproject.entity.user.Customer;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;

import java.util.Collections;
import java.util.List;

import static by.epam.javatraining.beseda.webproject.dao.util.dataloader.DatabaseEnumLoader.REQUEST_STATUS_MAP;

public class RequestService extends AbstractEntityService<Request> {

	RequestService() {
		super();
		entityDAO = mySQLDAOEntityFactory.getRequestDAO();
	}

	/**
	 * Creates entity WITHOUT ID with the given data.
	 * 
	 * @param customer
	 * @param comment
	 * @return
	 */
	public Request createRequest(Customer customer, String comment) {
		Request req = null;
		if (comment != null) {
			String status = REQUEST_STATUS_MAP.get(1);
			req = new Request(customer, comment, status);
		}
		return req;
	}

	/**
	 * Return an array, containing requests id for the specified customer.
	 * 
	 * @param customerId customer id
	 * @return array of requests id
	 * @throws ServiceLayerException
	 */
	public int[] selectActiveCustomerRequestsId(int customerId) throws ServiceLayerException {
		int[] array = null;
		if (customerId > 0) {
			try {
				array = ((RequestDAO) entityDAO).selectActiveCustomerRequestsId(customerId);
			} catch (DAOTechnicalException e) {
				throw new ServiceLayerException(e + " Impossible to get info on active customer requests");
			}
		}
		return array;
	}

	/**
	 * Returns list of new requests (which weren't considered or submitted, but left
	 * without route).
	 * 
	 * @return list of new requests
	 * @throws ServiceLayerException
	 */
	public List<Request> getNewRequests() throws ServiceLayerException {
		List<Request> list = null;
		try {
			list = ((RequestDAO) entityDAO).getNewRequests();
			Collections.sort(list);
		} catch (DAOLayerException e) {
			throw new ServiceLayerException(e);
		}
		return list;
	}

	/**
	 * Returns an array of current requests id.
	 * 
	 * @return array of current requests id
	 * @throws ServiceLayerException
	 */
	public int[] getCurrentRequestsId() throws ServiceLayerException {
		int[] array;
		try {
			array = ((RequestDAO) entityDAO).getCurrentRequestsId();
		} catch (DAOLayerException e) {
			throw new ServiceLayerException(e);
		}
		return array;
	}

	/**
	 * Returns an array of fulfilled requests id.
	 * 
	 * @return array of fulfilled requests id
	 * @throws ServiceLayerException
	 */
	public int[] getFulfilledRequestsId() throws ServiceLayerException {
		int[] array;
		try {
			array = ((RequestDAO) entityDAO).getFulfilledRequestsId();
		} catch (DAOLayerException e) {
			throw new ServiceLayerException(e);
		}
		return array;
	}

	/**
	 * Returns an array of rejected requests id.
	 * 
	 * @return array of rejected requests id
	 * @throws ServiceLayerException
	 */
	public int[] getRejectedRequestsId() throws ServiceLayerException {
		int[] array;
		try {
			array = ((RequestDAO) entityDAO).getRejectedRequestsId();
		} catch (DAOLayerException e) {
			throw new ServiceLayerException(e);
		}
		return array;
	}
	
}