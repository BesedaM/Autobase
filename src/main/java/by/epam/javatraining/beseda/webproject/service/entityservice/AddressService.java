package by.epam.javatraining.beseda.webproject.service.entityservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.epam.javatraining.beseda.webproject.dao.entitydao.AddressDAO;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.AddressInterface;
import by.epam.javatraining.beseda.webproject.entity.DefaultValue;
import by.epam.javatraining.beseda.webproject.entity.route.Address;

@Service
public class AddressService extends AbstractEntityService<Address> {

	public AddressService() {
		super();
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

	@Autowired
	public void setDAO(AddressDAO addressDAO) {
		this.entityDAO = addressDAO;
	}

	public List<Integer> getTasksId(int addressId) {
		return ((AddressInterface) entityDAO).getTasksId(addressId);
	}

}