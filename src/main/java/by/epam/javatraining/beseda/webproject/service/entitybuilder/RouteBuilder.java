package by.epam.javatraining.beseda.webproject.service.entitybuilder;

import java.util.List;

import by.epam.javatraining.beseda.webproject.entity.car.Car;
import by.epam.javatraining.beseda.webproject.entity.exception.TaskException;
import by.epam.javatraining.beseda.webproject.entity.route.Address;
import by.epam.javatraining.beseda.webproject.entity.route.Route;
import by.epam.javatraining.beseda.webproject.entity.route.Task;
import by.epam.javatraining.beseda.webproject.service.entityservice.AddressService;
import by.epam.javatraining.beseda.webproject.service.entityservice.CarService;
import by.epam.javatraining.beseda.webproject.service.entityservice.RouteService;
import by.epam.javatraining.beseda.webproject.service.entityservice.TaskService;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLogicException;

public class RouteBuilder extends EntityBuilder<Route> {

    private static CarService carService;
    private static RouteService routeService;
    private static TaskService taskService;
    private static AddressService addressService;

    RouteBuilder() {
        carService = serviceEntityFactory.getCarService();
        routeService = serviceEntityFactory.getRouteService();
        taskService = serviceEntityFactory.getTaskService();
        addressService =  serviceEntityFactory.getAddressService();
    }

    public Route getEntity(int routeId) throws ServiceLayerException {
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

                        try {
                            if (address != null) {
                                task.setAddress(address);
                            }
                        } catch (TaskException e) {
                            throw new ServiceLogicException(e);
                        }
                    }
                }
            }
        }
        return route;
    }

    public void addCarList(Route route) throws ServiceLayerException {
        List<Integer> carsId = routeService.getCarsId(route.getId());
        for (int i = 0; i < carsId.size(); i++) {
            Car car = carService.getEntityById(carsId.get(i));
            route.addCar(car);
        }
    }

}
