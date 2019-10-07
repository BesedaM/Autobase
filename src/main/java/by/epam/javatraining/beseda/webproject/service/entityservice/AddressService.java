package by.epam.javatraining.beseda.webproject.service.entityservice;

import by.epam.javatraining.beseda.webproject.entity.DefaultValue;
import by.epam.javatraining.beseda.webproject.entity.route.Address;

public class AddressService extends AbstractEntityService<Address> {

	AddressService() {
		super();
		entityDAO = mySQLDAOEntityFactory.getAddressDAO();
	}

	/**
	 * Creates entity WITHOUT ID with the given data.
	 * 
	 * @param country
	 * @param district
	 * @param city
	 * @param street
	 * @param house
	 * @param building
	 * @return
	 */
	public Address createAddress(String country, String district, String city, String street, int house,
			String building) {
		Address addr = null;
		if (country != null && district != null && city != null && street != null && house >= 0 && building != null) {
			if (country.length() == 0) {
				country = DefaultValue.COUNTRY;
			}
			if (district.length() == 0) {
				district = DefaultValue.DISTRICT;
			}
			addr = new Address(country, district, city, street, house, building);
		}
		return addr;
	}
}