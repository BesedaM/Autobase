package by.epam.javatraining.beseda.webproject.model.service.entityservice;

import by.epam.javatraining.beseda.webproject.model.dao.entitydao.RequestDAO;
import by.epam.javatraining.beseda.webproject.model.dao.exception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.model.entity.Request;
import by.epam.javatraining.beseda.webproject.model.entity.user.Customer;
import by.epam.javatraining.beseda.webproject.model.service.exception.ServiceLayerException;

import static by.epam.javatraining.beseda.webproject.model.dao.util.dataloader.DatabaseEnumLoader.REQUEST_STATUS_MAP;

public class RequestService extends AbstractEntityService<Request> {

    public RequestService() {
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

}
