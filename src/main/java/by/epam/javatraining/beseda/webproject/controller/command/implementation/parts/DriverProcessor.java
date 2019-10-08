package by.epam.javatraining.beseda.webproject.controller.command.implementation.parts;

import by.epam.javatraining.beseda.webproject.entity.car.Bus;
import by.epam.javatraining.beseda.webproject.entity.car.Car;
import by.epam.javatraining.beseda.webproject.entity.car.Truck;
import by.epam.javatraining.beseda.webproject.entity.route.Route;
import by.epam.javatraining.beseda.webproject.entity.user.Driver;
import by.epam.javatraining.beseda.webproject.service.EnumService;
import by.epam.javatraining.beseda.webproject.service.dependenceservice.CarDriverService;
import by.epam.javatraining.beseda.webproject.service.dependenceservice.CarRouteService;
import by.epam.javatraining.beseda.webproject.service.entityservice.CarService;
import by.epam.javatraining.beseda.webproject.service.entitybuilder.RouteBuilder;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.util.ReversalHashMap;
import by.epam.javatraining.beseda.webproject.service.entitybuilder.EntityBuilderFactory;
import by.epam.javatraining.beseda.webproject.service.dependenceservice.ServiceDependenceFactory;
import by.epam.javatraining.beseda.webproject.service.entityservice.ServiceEntityFactory;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPSessionAttribute.*;
import static by.epam.javatraining.beseda.webproject.service.ServiceConstants.CAR_STATE;
import static by.epam.javatraining.beseda.webproject.service.ServiceConstants.ROUTE_STATUS;

public class DriverProcessor {

	private static EntityBuilderFactory entityBuilderFactory = EntityBuilderFactory.getFactory();
	private static ServiceDependenceFactory serviceDependenceFactory = ServiceDependenceFactory.getFactory();
	private static ServiceEntityFactory serviceEntityFactory = ServiceEntityFactory.getFactory();
	private static Map<String, ReversalHashMap<Integer, String>> enumCollection = EnumService.getEnumCollection();

	private DriverProcessor() {
	}

	public static void processDriverData(HttpSession session) throws ServiceLayerException {
		CarDriverService carDriverService = serviceDependenceFactory.getCarDriverService();
		CarService carService = serviceEntityFactory.getCarService();

		Driver driver = (Driver) session.getAttribute(USER_DATA);

		int carId = carDriverService.getEntityIdByDependence(driver);
		Car car = carService.getEntityById(carId);
		setCarData(car, session);

		List<Route> routes = getActivePlannedRoutes(car);
		session.setAttribute(ROUTE_LIST, routes);

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