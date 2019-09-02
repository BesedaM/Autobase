package by.epam.javatraining.beseda.webproject.service.entitybuilder;

public class EntityBuilderFactory {

    private static class SingletonHolder {
        static EntityBuilderFactory instance = new EntityBuilderFactory();
    }

    private final RequestBuilder requestBuilder;
    private final RouteBuilder routeBuilder;
    private final TaskBuilder taskBuilder;

    private EntityBuilderFactory() {
        requestBuilder = new RequestBuilder();
        routeBuilder = new RouteBuilder();
        taskBuilder = new TaskBuilder();
    }

    public static EntityBuilderFactory getFactory() {
        return SingletonHolder.instance;
    }

    public RequestBuilder getRequestBuilder() {
        return requestBuilder;
    }

    public RouteBuilder getRouteBuilder() {
        return routeBuilder;
    }

    public TaskBuilder getTaskBuilder() {
        return taskBuilder;
    }
}
