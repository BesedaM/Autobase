package by.epam.javatraining.beseda.webproject.model.service.factory;

import by.epam.javatraining.beseda.webproject.model.service.dependence.*;

public class ServiceDependenceFactory {

    private static class SingletonHolder {
        public static ServiceDependenceFactory instance = new ServiceDependenceFactory();
    }

    private final CarDriverService carDriverService;
    private final CarRouteService carRouteService;
    private final RequestCustomerService requestCustomerService;
    private final RequestRouteService requestRouteService;
    private final TaskAddressService taskAddressService;
    private final TaskRouteService taskRouteService;


    private ServiceDependenceFactory() {
        carDriverService = new CarDriverService();
        carRouteService = new CarRouteService();
        requestCustomerService = new RequestCustomerService();
        requestRouteService = new RequestRouteService();
        taskAddressService = new TaskAddressService();
        taskRouteService = new TaskRouteService();
    }

    public static ServiceDependenceFactory getFactory() {
        return SingletonHolder.instance;
    }

    public CarDriverService getCarDriverService() {
        return carDriverService;
    }

    public CarRouteService getCarRouteService() {
        return carRouteService;
    }

    public RequestCustomerService getRequestCustomerService() {
        return requestCustomerService;
    }

    public RequestRouteService getRequestRouteService() {
        return requestRouteService;
    }

    public TaskAddressService getTaskAddressService() {
        return taskAddressService;
    }

    public TaskRouteService getTaskRouteService() {
        return taskRouteService;
    }
}
