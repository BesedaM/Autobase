package by.epam.javatraining.beseda.webproject.service.entitybuilder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.epam.javatraining.beseda.webproject.entity.car.Car;
import by.epam.javatraining.beseda.webproject.entity.route.Address;
import by.epam.javatraining.beseda.webproject.entity.route.Route;
import by.epam.javatraining.beseda.webproject.entity.route.Task;
import by.epam.javatraining.beseda.webproject.service.entityservice.AddressService;
import by.epam.javatraining.beseda.webproject.service.entityservice.CarService;
import by.epam.javatraining.beseda.webproject.service.entityservice.RouteService;
import by.epam.javatraining.beseda.webproject.service.entityservice.TaskService;

@Component
public class RouteBuilder implements EntityBuilder<Route> {

	@Autowired
	private CarService carService;

	@Autowired
	private RouteService routeService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private AddressService addressService;

	public RouteBuilder() {
	}

	public Route getEntity(int routeId) {
		Route route = null;
		if (routeId > 0) {
			route = routeService.getEntityById(routeId);
			if (route != null) {
				List<Integer> tasksId = routeService.getTasksId(route.getId());

				for (int i = 0; i < tasksId.size(); i++) {
					Task task = taskService.getEntityById(tasksId.get(i));

					if (task != null) {
						route.addTask(task);
						int addressId = taskService.getAddressId(task.getId());
						Address address = addressService.getEntityById(addressId);
						if (address != null) {
							task.setAddress(address);
						}
					}
				}
			}
		}
		return route;
	}

	public void addCarList(Route route) {
		List<Integer> carsId = routeService.getCarsId(route.getId());
		for (int i = 0; i < carsId.size(); i++) {
			Car car = carService.getEntityById(carsId.get(i));
			route.addCar(car);
		}
	}

}
