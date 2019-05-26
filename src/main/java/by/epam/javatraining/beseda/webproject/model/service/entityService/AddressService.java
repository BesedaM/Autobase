package by.epam.javatraining.beseda.webproject.model.service.entityService;

import by.epam.javatraining.beseda.webproject.model.dao.entitydao.AddressDAO;
import by.epam.javatraining.beseda.webproject.model.entity.route.Address;

public class AddressService extends AbstractService<Address> {

    private AddressService() {
        entityDAO = AddressDAO.getDAO();
    }

    private static class SingletonHolder {
        public static final AddressService instance = new AddressService();
    }

    public static AddressService getService() {
        return SingletonHolder.instance;
    }

    /**
     * Creates entity WITHOUT ID with the given data
     */
    public Address createAddress(String country, String district, String city, String street, int house, String building) {
        Address addr = null;
        if (country != null && district != null && city != null
                && street != null && house >= 0 && building != null) {
            addr = new Address(country, district, city, street, house, building);
        }
        return addr;
    }
}
