package by.epam.javatraining.beseda.webproject.controller.command.implementation.parts;

import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPSessionAttribute.DRIVER_MAP;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPSessionAttribute.REQUEST_LIST;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPSessionAttribute.REQUEST_STATUS_LIST;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPSessionAttribute.USER_DATA;
import static by.epam.javatraining.beseda.webproject.service.ServiceConstants.REQUEST_STATUS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import by.epam.javatraining.beseda.webproject.entity.Request;
import by.epam.javatraining.beseda.webproject.entity.car.Car;
import by.epam.javatraining.beseda.webproject.entity.exception.RequestException;
import by.epam.javatraining.beseda.webproject.entity.route.Route;
import by.epam.javatraining.beseda.webproject.entity.user.Customer;
import by.epam.javatraining.beseda.webproject.entity.user.Driver;
import by.epam.javatraining.beseda.webproject.service.EnumService;
import by.epam.javatraining.beseda.webproject.service.entitybuilder.EntityBuilderFactory;
import by.epam.javatraining.beseda.webproject.service.entitybuilder.RouteBuilder;
import by.epam.javatraining.beseda.webproject.service.entityservice.CarService;
import by.epam.javatraining.beseda.webproject.service.entityservice.DriverService;
import by.epam.javatraining.beseda.webproject.service.entityservice.RequestService;
import by.epam.javatraining.beseda.webproject.service.entityservice.ServiceEntityFactory;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLogicException;
import by.epam.javatraining.beseda.webproject.util.ReversalHashMap;

public class CustomerProcessor {

	private static EntityBuilderFactory entityBuilderFactory = EntityBuilderFactory.getFactory();
	private static ServiceEntityFactory serviceEntityFactory = ServiceEntityFactory.getFactory();
	private static Map<String, ReversalHashMap<Integer, String>> enumCollection = EnumService.getEnumCollection();

	private CustomerProcessor() {}
	
	public static void processCustomerData(HttpSession session) throws ServiceLayerException {
		RequestService requestService = serviceEntityFactory.getRequestService();
		RouteBuilder routeBuilder = entityBuilderFactory.getRouteBuilder();
		Customer customer = (Customer) session.getAttribute(USER_DATA);

		List<Request> requests = requestService.selectActiveCustomerRequests(customer.getId());
		Map<Integer, List<Driver>> driverMap = new HashMap<>();
		for (int i = 0; i < requests.size(); i++) {
			Request req = requests.get(i);		
            Route route = routeBuilder.getEntity(req.getId());
            try {
                if (route != null) {
                    routeBuilder.addCarList(route);
                    req.setRoute(route);
                }
            } catch (RequestException e) {
                throw new ServiceLogicException(e);
            }
			List<Driver> driverList = addDriversData(route);
			driverMap.put(req.getId(), driverList);
		}

		session.setAttribute(REQUEST_LIST, requests);
		session.setAttribute(REQUEST_STATUS_LIST, enumCollection.get(REQUEST_STATUS));
		session.setAttribute(DRIVER_MAP, driverMap);
	}
	
	private static List<Driver> addDriversData(Route route) throws ServiceLayerException {
		List<Driver> driverList = new ArrayList<>();
		if (route != null) {
			CarService carService = serviceEntityFactory.getCarService();
			DriverService driverService = serviceEntityFactory.getDriverService();
			Set<Car> carList = route.getCarsList();

			for (Car car : carList) {
				int driverId = carService.getDriverId(car.getId());
				Driver driver = driverService.getEntityById(driverId);
				driverList.add(driver);
			}
		}
		return driverList;
	}
}