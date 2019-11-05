package by.epam.javatraining.beseda.webproject.service.entityservice;

import java.util.GregorianCalendar;

import by.epam.javatraining.beseda.webproject.dao.interfacedao.EntityDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import by.epam.javatraining.beseda.webproject.dao.entitydao.TaskDAO;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.TaskInterface;
import by.epam.javatraining.beseda.webproject.entity.route.Task;

@Service
public class TaskService extends AbstractEntityService<Task> {

	public TaskService() {
		super();
	}

	/**
	 * Creates entity WITHOUT ID with the given data.
	 * 
	 * @param time
	 * @param details
	 * @return
	 */
	public Task createTask(GregorianCalendar time, String details) {
		Task task = null;
		if (time != null && details != null) {
			task = new Task(time, details);
		}
		return task;
	}

	@Qualifier("taskDAO")
	@Autowired
	@Override
	public void setEntityDAO(EntityDAO<Task> entityDAO) {
		this.entityDAO=entityDAO;
	}

	public void setRoute(int routeId, int taskId) {
		((TaskInterface) entityDAO).setRoute(routeId, taskId);
	}

	public int getRouteId(int taskId) {
		return ((TaskInterface) entityDAO).getRouteId(taskId);
	}

	public void deleteRoute(int taskId) {
		((TaskInterface) entityDAO).deleteRoute(taskId);
	}

	public void setAddress(int addressId, int taskId) {
		((TaskInterface) entityDAO).setAddress(addressId, taskId);
	}

	public int getAddressId(int taskId) {
		return ((TaskInterface) entityDAO).getAddressId(taskId);
	}

	public void deleteAddress(int taskId) {
		((TaskInterface) entityDAO).deleteAddress(taskId);
	}
}