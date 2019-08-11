package by.epam.javatraining.beseda.webproject.model.service.entitybuilder;

import by.epam.javatraining.beseda.webproject.model.entity.car.Car;
import by.epam.javatraining.beseda.webproject.model.entity.route.Address;
import by.epam.javatraining.beseda.webproject.model.entity.route.Route;
import by.epam.javatraining.beseda.webproject.model.entity.route.Task;
import by.epam.javatraining.beseda.webproject.model.exception.entityexception.task.IllegalAddressException;
import by.epam.javatraining.beseda.webproject.model.service.dependence.CarRouteService;
import by.epam.javatraining.beseda.webproject.model.service.dependence.TaskAddressService;
import by.epam.javatraining.beseda.webproject.model.service.dependence.TaskRouteService;
import by.epam.javatraining.beseda.webproject.model.service.entity.AddressService;
import by.epam.javatraining.beseda.webproject.model.service.entity.CarService;
import by.epam.javatraining.beseda.webproject.model.service.entity.RouteService;
import by.epam.javatraining.beseda.webproject.model.service.entity.TaskService;
import by.epam.javatraining.beseda.webproject.model.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.model.service.exception.ServiceLogicException;

import java.util.Arrays;

public class RouteBuilder extends EntityBuider<Route> {

    private static CarService carService = serviceEntityFactory.getCarService();
    private static RouteService routeService = serviceEntityFactory.getRouteService();
    private static TaskService taskService = serviceEntityFactory.getTaskService();
    private static AddressService addressService = serviceEntityFactory.getAddressService();

    private static CarRouteService carRouteService = serviceDependenceFactory.getCarRouteService();
    private static TaskRouteService taskRouteService = serviceDependenceFactory.getTaskRouteService();
    private static TaskAddressService taskAddressService = serviceDependenceFactory.getTaskAddressService();

    public Route getEntity(int routeId) throws ServiceLayerException {
        Route route = null;
        if (routeId > 0) {
            route = routeService.getEntityById(routeId);
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
