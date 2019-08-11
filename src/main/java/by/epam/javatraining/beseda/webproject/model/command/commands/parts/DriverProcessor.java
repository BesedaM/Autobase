package by.epam.javatraining.beseda.webproject.model.command.commands.parts;

import by.epam.javatraining.beseda.webproject.model.entity.car.Bus;
import by.epam.javatraining.beseda.webproject.model.entity.car.Car;
import by.epam.javatraining.beseda.webproject.model.entity.car.Truck;
import by.epam.javatraining.beseda.webproject.model.entity.route.Route;
import by.epam.javatraining.beseda.webproject.model.entity.user.Driver;
import by.epam.javatraining.beseda.webproject.model.entity.user.User;
import by.epam.javatraining.beseda.webproject.model.service.EnumService;
import by.epam.javatraining.beseda.webproject.model.service.dependence.CarDriverService;
import by.epam.javatraining.beseda.webproject.model.service.dependence.CarRouteService;
import by.epam.javatraining.beseda.webproject.model.service.entity.CarService;
import by.epam.javatraining.beseda.webproject.model.service.entity.DriverService;
import by.epam.javatraining.beseda.webproject.model.service.entitybuilder.RouteBuilder;
import by.epam.javatraining.beseda.webproject.model.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.model.service.factory.EntityBuilderFactory;
import by.epam.javatraining.beseda.webproject.model.service.factory.ServiceDependenceFactory;
import by.epam.javatraining.beseda.webproject.model.service.factory.ServiceEntityFactory;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.epam.javatraining.beseda.webproject.model.command.util.jsp.JSPSessionAttribute.*;
import static by.epam.javatraining.beseda.webproject.model.service.ServiceConstants.CAR_STATE;
import static by.epam.javatraining.beseda.webproject.model.service.ServiceConstants.ROUTE_STATUS;

public class DriverProcessor {

    private static EntityBuilderFactory entityBuilderFactory = EntityBuilderFactory.getFactory();
    private static ServiceDependenceFactory serviceDependenceFactory = ServiceDependenceFactory.getFactory();
    private static ServiceEntityFactory serviceEntityFactory = ServiceEntityFactory.getFactory();


    public static void processDriverData(User user, HttpSession session) throws ServiceLayerException {
        CarDriverService carDriverService = serviceDependenceFactory.getCarDriverService();
        DriverService driverService = serviceEntityFactory.getDriverService();
        CarService carService = serviceEntityFactory.getCarService();

        int userId = user.getId();
        Driver driver = driverService.getEntityById(userId);

        int carId = carDriverService.getEntityIdByDependence(driver);
        Car car = carService.getEntityById(carId);
        session.setAttribute(USER_DATA, driver);

        setCarData(car, session);

        List<Route> routes = getActivePlannedRoutes(car);
        session.setAttribute(ROUTE_LIST, routes);

        Map<String, HashMap> enumCollection = EnumService.getEnumCollection();
        session.setAttribute(ROUTE_STATUS, enumCollection.get(ROUTE_STATUS));
        session.setAttribute(CAR_STATE, enumCollection.get(CAR_STATE));
    }


    private static void setCarData(Car car, HttpSession session) throws ServiceLayerException {
        if (car != null) {
            session.setAttribute(CAR, car);
            if (car instanceof Bus) {
                session.setAttribute(CAR_TYPE, CAR_BUS);
            } else if (car instanceof Truck) {
                session.setAttribute(CAR_TYPE, CAR_TRUCK);
            } else {
                session.setAttribute(CAR_TYPE, EMPTY_VALUE);
            }
        }
    }


    private static List<Route> getActivePlannedRoutes(Car car) throws ServiceLayerException {
        CarRouteService carRouteService = serviceDependenceFactory.getCarRouteService();
        RouteBuilder routeBuilder = entityBuilderFactory.getRouteBuilder();
        int[] routesId = carRouteService.getActivePlannedRoutesId(car);

        List<Route> routes = new ArrayList<>();
        for (int i = 0; i < routesId.length; i++) {
            Route route = routeBuilder.getEntity(routesId[i]);
            routes.add(route);
        }
        return routes;
    }
}
