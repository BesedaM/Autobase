package by.epam.javatraining.beseda.webproject.model.service.entity;

import by.epam.javatraining.beseda.webproject.model.entity.user.Customer;
import by.epam.javatraining.beseda.webproject.model.entity.user.User;
import by.epam.javatraining.beseda.webproject.model.dao.exception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.model.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.model.service.exception.ServiceLogicException;

public class CustomerService extends AbstractEntityService<Customer> {

    public CustomerService() {
        super();
        entityDAO = daoEntityFactory.getCustomerDAO();
    }

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
