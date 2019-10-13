package by.epam.javatraining.beseda.webproject.controller.command.implementation.post.admin;

import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.CommandConstant.CONTEXT_TO_REPLACE;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.CommandConstant.EMPTY_STRING;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPParameter.CARS_ID;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPParameter.CURRENT_PAGE;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPSessionAttribute.CHANGE_CAR;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPSessionAttribute.CHANGING_ROUTE;
import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.epam.javatraining.beseda.webproject.controller.command.ActionCommand;
import by.epam.javatraining.beseda.webproject.controller.command.util.srcontent.SessionRequestContent;
import by.epam.javatraining.beseda.webproject.entity.car.Car;
import by.epam.javatraining.beseda.webproject.entity.route.Route;
import by.epam.javatraining.beseda.webproject.service.entityservice.CarService;
import by.epam.javatraining.beseda.webproject.service.entityservice.RouteService;
import by.epam.javatraining.beseda.webproject.service.entityservice.ServiceEntityFactory;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;

public class ChangeCars implements ActionCommand {

	private static Logger log = Logger.getLogger(ERROR_LOGGER);
	private static ServiceEntityFactory serviceEntityFactory = ServiceEntityFactory.getFactory();

	private RouteService routeService = serviceEntityFactory.getRouteService();
	private CarService carService = serviceEntityFactory.getCarService();

	@Override
	public String execute(SessionRequestContent content) {
		Map<String, String[]> requestParam = content.requestParameters();
		HttpSession session = content.getSession();

		Route route = (Route) session.getAttribute(CHANGING_ROUTE);

		List<Integer> previousCars = null;
		try {
			previousCars = routeService.getCarsId(route.getId());
		} catch (ServiceLayerException e) {
			log.error(e);
		}

		List<Integer> newCars = new ArrayList<>();

		String[] carsId = requestParam.get(CARS_ID);
		for (int i = 0; i < carsId.length; i++) {
			int carId = Integer.parseInt(carsId[i]);
			newCars.add(carId);
		}

		processCarsData(route, newCars, previousCars);

		session.setAttribute(CHANGE_CAR, null);
		return requestParam.get(CURRENT_PAGE)[0].replace(CONTEXT_TO_REPLACE, EMPTY_STRING);
	}

	private void processCarsData(Route route, List<Integer> newCars, List<Integer> previousCars) {
		int routeId = route.getId();
		try {
			for (Integer carId : previousCars) {
				if (!newCars.contains(carId)) {
					routeService.deleteCar(routeId,carId);
					route.removeCar(carId);
				}
			}

			for (Integer carId : newCars) {
				if (!previousCars.contains(carId)) {
					routeService.addCar(routeId,carId);
					Car car = carService.getEntityById(carId);
					route.addCar(car);
				}
			}

		} catch (ServiceLayerException e) {
			log.error(e);
		}

	}
}