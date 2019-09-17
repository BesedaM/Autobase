package by.epam.javatraining.beseda.webproject.service.entitybuilder;

import by.epam.javatraining.beseda.webproject.entity.car.Car;
import by.epam.javatraining.beseda.webproject.entity.route.Address;
import by.epam.javatraining.beseda.webproject.entity.route.Route;
import by.epam.javatraining.beseda.webproject.entity.route.Task;
import by.epam.javatraining.beseda.webproject.entity.exception.TaskException;
import by.epam.javatraining.beseda.webproject.service.dependenceservice.CarRouteService;
import by.epam.javatraining.beseda.webproject.service.dependenceservice.TaskAddressService;
import by.epam.javatraining.beseda.webproject.service.dependenceservice.TaskRouteService;
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

    private static CarRouteService carRouteService;
    private static TaskRouteService taskRouteService;
    private static TaskAddressService taskAddressService;

    RouteBuilder() {
        carService = serviceEntityFactory.getCarService();
        routeService = serviceEntityFactory.getRouteService();
        taskService = serviceEntityFactory.getTaskService();
        addressService =  serviceEntityFactory.getAddressService();
        carRouteService = serviceDependenceFactory.getCarRouteService();
        taskRouteService = serviceDependenceFactory.getTaskRouteService();
        taskAddressService = serviceDependenceFactory.getTaskAddressService();
    }

    public Route getEntity(int routeId) throws ServiceLayerException {
        Route route = null;
        if (routeId > 0) {
            route = routeService.getEntityById(routeId);
            if (route != null) {
                int[] taskIds = taskRouteService.getEntitiesIdByDependenceId(route);

                for (int i = 0; i < taskIds.length; i++) {
                    Task task = taskService.getEntityById(taskIds[i]);

                    if (task != null) {
                        route.addTask(task);
                        int addressId = taskAddressService.getEntity02Id(task);
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
        int[] carIds = carRouteService.getEntities01Id(route);
        for (int i = 0; i < carIds.length; i++) {
            Car car = carService.getEntityById(carIds[i]);
            route.addCar(car);
        }
    }

}
