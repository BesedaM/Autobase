package by.epam.javatraining.beseda.webproject.model.service.entitybuilder;

import by.epam.javatraining.beseda.webproject.model.entity.car.Car;
import by.epam.javatraining.beseda.webproject.model.entity.route.Address;
import by.epam.javatraining.beseda.webproject.model.entity.route.Route;
import by.epam.javatraining.beseda.webproject.model.entity.route.Task;
import by.epam.javatraining.beseda.webproject.model.exception.entityexception.task.IllegalAddressException;
import by.epam.javatraining.beseda.webproject.model.service.dependenceservice.CarRouteService;
import by.epam.javatraining.beseda.webproject.model.service.dependenceservice.TaskAddressService;
import by.epam.javatraining.beseda.webproject.model.service.dependenceservice.TaskRouteService;
import by.epam.javatraining.beseda.webproject.model.service.entityservice.AddressService;
import by.epam.javatraining.beseda.webproject.model.service.entityservice.CarService;
import by.epam.javatraining.beseda.webproject.model.service.entityservice.RouteService;
import by.epam.javatraining.beseda.webproject.model.service.entityservice.TaskService;
import by.epam.javatraining.beseda.webproject.model.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.model.service.exception.ServiceLogicException;

public class RouteBuilder extends EntityBuilder<Route> {

    private CarService carService;
    private RouteService routeService;
    private TaskService taskService;
    private AddressService addressService;

    private CarRouteService carRouteService;
    private TaskRouteService taskRouteService;
    private TaskAddressService taskAddressService;

    public RouteBuilder() {
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
                        } catch (IllegalAddressException e) {
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
