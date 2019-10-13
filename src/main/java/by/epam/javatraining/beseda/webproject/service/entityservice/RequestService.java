package by.epam.javatraining.beseda.webproject.service.entityservice;

import static by.epam.javatraining.beseda.webproject.dao.util.dataloader.DatabaseEnumLoader.REQUEST_STATUS_MAP;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.epam.javatraining.beseda.webproject.dao.entitydao.RequestDAO;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.RequestInterface;
import by.epam.javatraining.beseda.webproject.entity.Request;
import by.epam.javatraining.beseda.webproject.entity.user.Customer;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;

@Service
public class RequestService extends AbstractEntityService<Request> {

	public RequestService() {
		super();
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
	public List<Request> selectActiveCustomerRequests(int customerId) throws ServiceLayerException {
		List<Request> list = null;
		if (customerId > 0) {
			try {
				list = ((RequestDAO) entityDAO).selectActiveCustomerRequests(customerId);
			} catch (DAOTechnicalException e) {
				throw new ServiceLayerException(e + " Impossible to get info on active customer requests");
			}
		}
		return list;
	}

	@Autowired
	public void setDAO(RequestDAO requestDAO) {
		this.entityDAO = requestDAO;
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
	public List<Request> getCurrentRequests() throws ServiceLayerException {
		List<Request> list;
		try {
			list = ((RequestDAO) entityDAO).getCurrentRequests();
		} catch (DAOLayerException e) {
			throw new ServiceLayerException(e);
		}
		return list;
	}

	/**
	 * Returns an array of fulfilled requests id.
	 * 
	 * @return array of fulfilled requests id
	 * @throws ServiceLayerException
	 */
	public List<Request> getFulfilledRequests() throws ServiceLayerException {
		List<Request> list;
		try {
			list = ((RequestDAO) entityDAO).getFulfilledRequests();
		} catch (DAOLayerException e) {
			throw new ServiceLayerException(e);
		}
		return list;
	}

	/**
	 * Returns an array of rejected requests id.
	 * 
	 * @return array of rejected requests id
	 * @throws ServiceLayerException
	 */
	public List<Request> getRejectedRequests() throws ServiceLayerException {
		List<Request> list;
		try {
			list = ((RequestDAO) entityDAO).getRejectedRequests();
		} catch (DAOLayerException e) {
			throw new ServiceLayerException(e);
		}
		return list;
	}

	public void setCustomer(int customerId, int requestId) {
		((RequestInterface) entityDAO).setCustomer(customerId, requestId);
	}

	public int getCustomerId(int requestId) {
		return ((RequestInterface) entityDAO).getCustomerId(requestId);
	}

	public void deleteCustomer(int customerId, int requestId) {
		((RequestInterface) entityDAO).deleteCustomer(customerId, requestId);
	}

}