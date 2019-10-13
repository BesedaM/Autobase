package by.epam.javatraining.beseda.webproject.dao.interfacedao;

import by.epam.javatraining.beseda.webproject.entity.route.Task;

public interface TaskInterface extends EntityDAO<Task> {
	
	void setRoute(int routeId, int taskId);

	int getRouteId(int taskId);

	void deleteRoute(int taskId);

	void setAddress(int addressId, int taskId);

	int getAddressId(int taskId);

	void deleteAddress(int taskId);
}