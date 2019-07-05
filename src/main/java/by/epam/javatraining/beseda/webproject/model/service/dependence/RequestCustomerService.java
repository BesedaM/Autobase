package by.epam.javatraining.beseda.webproject.model.service.dependence;

import by.epam.javatraining.beseda.webproject.model.entity.Request;
import by.epam.javatraining.beseda.webproject.model.entity.user.Customer;

public class RequestCustomerService extends ToOneDependenceService<Request, Customer>{

    public RequestCustomerService() {
        super();
        dependenceDAO = DAODependenceFactory.getRequestCustomerDAO();
    }

//    private static class SingletonHolder {
//        public static final RequestCustomerService instance = new RequestCustomerService();
//    }

//    public static RequestCustomerService getService() {
//        return SingletonHolder.instance;
//    }

//    public void addDependence(Request request, Customer customer) throws ServiceTechnicalException {
////        boolean succeed = false;
//        if (request != null && customer != null) {
//            try {
//                requestCustomerDependenceDAO.setDependence(request, customer);
////                customer.addRequest(request);
// //               succeed = true;
//            } catch (DAOTechnicalException e) {
//                throw new ServiceTechnicalException(e);
//            }
//        }
////        return succeed;
//    }

//    public void deleteDependence(Request request, Customer customer) throws ServiceTechnicalException {
////        boolean succeed = false;
//        if (request != null && customer != null) {
//            try {
//                requestCustomerDependenceDAO.deleteDependence(request);
////                customer.removeRequest(request);
////                succeed = true;
//            } catch (DAOTechnicalException e) {
//                throw new ServiceTechnicalException(e);
//            }
//        }
////        return succeed;
//    }

//    public Customer getCustomer(Request request) throws ServiceLayerException {
//        Customer customer = null;
//        if (request != null) {
//            try {
//                int customerId = requestCustomerDependenceDAO.getDependenceId(request);
//                customer = CustomerDAO.getDAO().getEntityById(customerId);
//                customer.addRequest(request);
//            } catch (DAOLayerException e) {
//                throw new ServiceLayerException(e);
//            }
//        }
//        return customer;
//    }
//
//    public List<Request> getRequestList(Customer customer) throws ServiceLayerException {
//        List<Request> list = null;
//        if (customer != null) {
//            try {
//                int[] requestsId = requestCustomerDependenceDAO.getEntitiesIdByDependenceId(customer);
//                list = RequestDAO.getDAO().getEntitiesByIdList(requestsId);
//                customer.setRequestList(list);
//            } catch (DAOLayerException e) {
//                throw new ServiceLayerException();
//            }
//        }
//        return list;
//    }

}
