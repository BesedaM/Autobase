package by.epam.javatraining.beseda.webproject.model.service.dependenceservice;

import by.epam.javatraining.beseda.webproject.model.dao.dependencedao.RequestCustomerDependenceDAO;
import by.epam.javatraining.beseda.webproject.model.dao.entitydao.CustomerDAO;
import by.epam.javatraining.beseda.webproject.model.dao.entitydao.RequestDAO;
import by.epam.javatraining.beseda.webproject.model.entity.Request;
import by.epam.javatraining.beseda.webproject.model.entity.user.Customer;
import by.epam.javatraining.beseda.webproject.model.exception.daoexception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.model.exception.daoexception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.model.exception.serviceexception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.model.exception.serviceexception.ServiceTechnicalException;

import java.util.List;

public class RequestCustomerService {

    private static RequestCustomerDependenceDAO requestCustomerDependenceDAO = RequestCustomerDependenceDAO.getDAO();

    public static boolean addDependence(Request request, Customer customer) throws ServiceLayerException {
        boolean succeed = false;
        if (request != null && customer != null) {
            try {
                requestCustomerDependenceDAO.setDependence(request, customer);
                customer.addRequest(request);
                succeed = true;
            } catch (DAOTechnicalException e) {
                throw new ServiceTechnicalException(e);
            }
        }
        return succeed;
    }

    public static boolean deleteDependence(Request request, Customer customer) throws ServiceLayerException {
        boolean succeed = false;
        if (request != null && customer != null) {
            try {
                requestCustomerDependenceDAO.deleteDependence(request);
                customer.removeRequest(request);
                succeed = true;
            } catch (DAOTechnicalException e) {
                throw new ServiceTechnicalException(e);
            }
        }
        return succeed;
    }

    public static Customer getCustomer(Request request) throws ServiceLayerException {
        Customer customer = null;
        if (request != null) {
            try {
                int customerId = requestCustomerDependenceDAO.getDependenceId(request);
                customer = CustomerDAO.getDAO().getEntityById(customerId);
                customer.addRequest(request);
            } catch (DAOLayerException e) {
                throw new ServiceLayerException(e);
            }
        }
        return customer;
    }

    public static List<Request> getRequestList(Customer customer) throws ServiceLayerException {
        List<Request> list = null;
        if (customer != null) {
            try {
                int[] requestsId = requestCustomerDependenceDAO.getEntitiesIdByDependenceId(customer);
                list = RequestDAO.getDAO().getEntitiesByIdList(requestsId);
                customer.setRequestList(list);
            } catch (DAOLayerException e) {
                throw new ServiceLayerException();
            }
        }
        return list;
    }

}
