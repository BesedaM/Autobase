package by.epam.javatraining.beseda.webproject.service.entitybuilder;

public final class EntityBuilderFactory {

    private static class SingletonHolder {
        private static EntityBuilderFactory instance = new EntityBuilderFactory();
    }

    private final RequestBuilder requestBuilder;
    private final RouteBuilder routeBuilder;

    private EntityBuilderFactory() {
        requestBuilder = new RequestBuilder();
        routeBuilder = new RouteBuilder();
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
}