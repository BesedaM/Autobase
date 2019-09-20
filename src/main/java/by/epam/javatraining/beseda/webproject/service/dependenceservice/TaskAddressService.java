package by.epam.javatraining.beseda.webproject.service.dependenceservice;

import by.epam.javatraining.beseda.webproject.entity.route.Address;
import by.epam.javatraining.beseda.webproject.entity.route.Task;

public class TaskAddressService extends ToOneDependenceService<Task, Address> {

	TaskAddressService() {
		super();
		dependenceDAO = daoDependenceFactory.getTaskAddressDAO();
	}

}
