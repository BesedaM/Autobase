package by.epam.javatraining.beseda.webproject.service.entityservice;

import by.epam.javatraining.beseda.webproject.dao.entitydao.RequestDAO;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.entity.Request;
import by.epam.javatraining.beseda.webproject.entity.user.Customer;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static by.epam.javatraining.beseda.webproject.dao.util.dataloader.DatabaseEnumLoader.REQUEST_STATUS_MAP;

public class RequestService extends AbstractEntityService<Request> {

    RequestService() {
        super();
        entityDAO = daoEntityFactory.getRequestDAO();
    }

    /**
     * Creates entityservice WITHOUT ID with the given data
     */
    public Request createRequest(Customer customer, String comment) {
        Request req = null;
        if (comment != null) {
            String status = REQUEST_STATUS_MAP.get(1);
            req = new Request(customer, comment, status);
        }
        return req;
    }

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

    public List<Request> getNewRequests() throws ServiceLayerException {
        List<Request> list = null;
        try {
            list = ((RequestDAO)entityDAO).getNewRequests();
            Collections.sort(list);
        } catch (DAOLayerException e) {
            throw new ServiceLayerException(e);
        }
        return list;
    }

    public int[] getCurrentRequestsId() throws ServiceLayerException {
        int[] array;
        try {
            array=((RequestDAO)entityDAO).getCurrentRequestsId();
        } catch (DAOLayerException e) {
            throw new ServiceLayerException(e);
        }
        return array;
    }

    public int[] getFulfilledRequestsId() throws ServiceLayerException {
        int[] array;
        try {
            array=((RequestDAO)entityDAO).getFulfilledRequestsId();
        } catch (DAOLayerException e) {
            throw new ServiceLayerException(e);
        }
        return array;
    }

}
