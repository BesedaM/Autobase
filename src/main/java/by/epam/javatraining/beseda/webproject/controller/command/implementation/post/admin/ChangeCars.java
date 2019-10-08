package by.epam.javatraining.beseda.webproject.controller.command.implementation.post.admin;

import by.epam.javatraining.beseda.webproject.controller.command.ActionCommand;
import by.epam.javatraining.beseda.webproject.controller.command.util.srcontent.SessionRequestContent;
import by.epam.javatraining.beseda.webproject.entity.car.Car;
import by.epam.javatraining.beseda.webproject.entity.route.Route;
import by.epam.javatraining.beseda.webproject.service.dependenceservice.CarRouteService;
import by.epam.javatraining.beseda.webproject.service.dependenceservice.ServiceDependenceFactory;
import by.epam.javatraining.beseda.webproject.service.entityservice.CarService;
import by.epam.javatraining.beseda.webproject.service.entityservice.ServiceEntityFactory;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPParameter.CARS_ID;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPParameter.CURRENT_PAGE;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPSessionAttribute.CHANGE_CAR;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPSessionAttribute.CHANGING_ROUTE;
import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.CommandConstant.CONTEXT_TO_REPLACE;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.CommandConstant.EMPTY_STRING;

public class ChangeCars implements ActionCommand {

	private static Logger log = Logger.getLogger(ERROR_LOGGER);
	private static ServiceDependenceFactory serviceDependenceFactory = ServiceDependenceFactory.getFactory();
	private static ServiceEntityFactory serviceEntityFactory = ServiceEntityFactory.getFactory();

	private CarRouteService carRouteService = serviceDependenceFactory.getCarRouteService();
	private CarService carService = serviceEntityFactory.getCarService();

	@Override
	public String execute(SessionRequestContent content) {
		Map<String, String[]> requestParam = content.requestParameters();
		HttpSession session = content.getSession();

		Route route = (Route) session.getAttribute(CHANGING_ROUTE);

		Set<Integer> previousCars = new HashSet<>();
		try {
			int[] prevCarsArr = carRouteService.getEntities01Id(route);
			for (int i = 0; i < prevCarsArr.length; i++) {
				previousCars.add(prevCarsArr[i]);
			}
		} catch (ServiceLayerException e) {
			log.error(e);
		}

		Set<Integer> newCars = new HashSet<>();

		String[] carsId = requestParam.get(CARS_ID);
		for (int i = 0; i < carsId.length; i++) {
			int carId = Integer.parseInt(carsId[i]);
			newCars.add(carId);
		}

		processCarsData(route, newCars, previousCars);

		session.setAttribute(CHANGE_CAR, null);
		return requestParam.get(CURRENT_PAGE)[0].replace(CONTEXT_TO_REPLACE, EMPTY_STRING);
	}

	private void processCarsData(Route route, Set<Integer> newCars, Set<Integer> previousCars) {
		int routeId = route.getId();
		try {
			for (Integer carId : previousCars) {
				if (!newCars.contains(carId)) {
					carRouteService.deleteDependence(carId, routeId);
					route.removeCar(carId);
				}
			}

			for (Integer carId : newCars) {
				if (!previousCars.contains(carId)) {
					carRouteService.addDependence(carId, routeId);
					Car car = carService.getEntityById(carId);
					route.addCar(car);
				}
			}

		} catch (ServiceLayerException e) {
			log.error(e);
		}

	}
}