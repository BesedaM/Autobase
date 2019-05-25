package by.epam.javatraining.beseda.webproject.model.service.entityService;

import by.epam.javatraining.beseda.webproject.model.dao.entitydao.CustomerDAO;
import by.epam.javatraining.beseda.webproject.model.entity.user.Customer;
import by.epam.javatraining.beseda.webproject.model.entity.user.User;
import by.epam.javatraining.beseda.webproject.model.exception.daoexception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.model.exception.serviceexception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.model.exception.serviceexception.ServiceLogicException;

public class CustomerService extends AbstractService<Customer> {

    public CustomerService() {
        entityDAO = CustomerDAO.getDAO();
    }

//    public static void sortCustomers(List<Customer> list, Comparator<? super Customer> comparator) {
//        if (list != null && comparator != null) {
//            list.sort(comparator);
//        }
//    }

    /**
     * Creates entity with the given data
     */
    public Customer createCustomer(User userData, String name, String surname, String customerType,
                                   String phone, String email, String companyName) {
        Customer customer = null;
        if (userData != null
                && name != null && surname != null
                && phone != null && email != null) {
            if (companyName == null) {
                companyName = "";
            }
            customer = new Customer(userData, name, surname, phone, customerType, email, companyName);
        }
        return customer;
    }

    @Override
    public void add(Customer entity) throws ServiceLayerException {
        if (entity != null) {
            try {
                entityDAO.add(entity);
            } catch (DAOLayerException e) {
                throw new ServiceLogicException(e);
            }
        }
    }

}
