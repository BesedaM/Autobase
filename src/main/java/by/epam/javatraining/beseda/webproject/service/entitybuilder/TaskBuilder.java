package by.epam.javatraining.beseda.webproject.service.entitybuilder;

import by.epam.javatraining.beseda.webproject.entity.route.Task;
import by.epam.javatraining.beseda.webproject.service.dependenceservice.TaskAddressService;
import by.epam.javatraining.beseda.webproject.service.dependenceservice.TaskRouteService;
import by.epam.javatraining.beseda.webproject.service.entityservice.AddressService;
import by.epam.javatraining.beseda.webproject.service.entityservice.TaskService;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;

public class TaskBuilder extends EntityBuilder<Task> {

    private static TaskService taskService=serviceEntityFactory.getTaskService();
    private static AddressService addressService=serviceEntityFactory.getAddressService();
    private static TaskAddressService taskAddressService=serviceDependenceFactory.getTaskAddressService();
    private static TaskRouteService taskRouteService=serviceDependenceFactory.getTaskRouteService();

    TaskBuilder() {
    }

    @Override
    public Task getEntity(int entityId) throws ServiceLayerException {
        return null;
    }
}
