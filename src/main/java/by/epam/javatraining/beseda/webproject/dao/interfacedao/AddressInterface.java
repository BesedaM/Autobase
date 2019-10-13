package by.epam.javatraining.beseda.webproject.dao.interfacedao;

import java.util.List;

import by.epam.javatraining.beseda.webproject.entity.route.Address;

public interface AddressInterface extends EntityDAO<Address> {
	
	List<Integer> getTasksId(int addressId);
}
