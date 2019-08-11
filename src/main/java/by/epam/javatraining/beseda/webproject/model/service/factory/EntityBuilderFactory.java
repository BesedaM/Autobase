package by.epam.javatraining.beseda.webproject.model.service.factory;

import by.epam.javatraining.beseda.webproject.model.service.entitybuilder.RequestBuilder;
import by.epam.javatraining.beseda.webproject.model.service.entitybuilder.RouteBuilder;

public class EntityBuilderFactory {

    private static class SingletonHolder {
        public static EntityBuilderFactory instance = new EntityBuilderFactory();
    }

    private final RequestBuilder requestBuilder;
    private final RouteBuilder routeBuilder;

    private EntityBuilderFactory() {
        requestBuilder = new RequestBuilder();
        routeBuilder = new RouteBuilder();
    }

    public static EntityBuilderFactory getFactory(){
        return SingletonHolder.instance;
    }

    public RequestBuilder getRequestBuilder(){
        return requestBuilder;
    }

    public RouteBuilder getRouteBuilder() {
        return routeBuilder;
    }
}
