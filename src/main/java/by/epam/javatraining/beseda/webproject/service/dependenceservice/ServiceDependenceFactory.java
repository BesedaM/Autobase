package by.epam.javatraining.beseda.webproject.service.dependenceservice;

public class ServiceDependenceFactory {

    private static class SingletonHolder {
        static ServiceDependenceFactory instance = new ServiceDependenceFactory();
    }

    private final CarDriverService carDriverService;
    private final CarRouteService carRouteService;
    private final RequestCustomerService requestCustomerService;
    private final TaskAddressService taskAddressService;
    private final TaskRouteService taskRouteService;


    private ServiceDependenceFactory() {
        carDriverService = new CarDriverService();
        carRouteService = new CarRouteService();
        requestCustomerService = new RequestCustomerService();
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

    public TaskAddressService getTaskAddressService() {
        return taskAddressService;
    }

    public TaskRouteService getTaskRouteService() {
        return taskRouteService;
    }
}
