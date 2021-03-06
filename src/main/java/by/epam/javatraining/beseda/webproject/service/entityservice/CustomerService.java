package by.epam.javatraining.beseda.webproject.service.entityservice;

import by.epam.javatraining.beseda.webproject.entity.user.Customer;
import by.epam.javatraining.beseda.webproject.entity.user.User;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLogicException;

public class CustomerService extends AbstractEntityService<Customer> {

    CustomerService() {
        super();
        entityDAO = mySQLDAOEntityFactory.getCustomerDAO();
    }

    /**
     * Creates entity with the given data.
     * 
     * @param userData
     * @param name
     * @param surname
     * @param customerType
     * @param phone
     * @param email
     * @param companyName
     * @return
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