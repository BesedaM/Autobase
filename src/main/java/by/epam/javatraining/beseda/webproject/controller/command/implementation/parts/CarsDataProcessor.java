package by.epam.javatraining.beseda.webproject.controller.command.implementation.parts;

import by.epam.javatraining.beseda.webproject.entity.car.Car;
import by.epam.javatraining.beseda.webproject.entity.route.Route;
import by.epam.javatraining.beseda.webproject.entity.route.Task;
import by.epam.javatraining.beseda.webproject.service.dependenceservice.CarRouteService;
import by.epam.javatraining.beseda.webproject.service.dependenceservice.ServiceDependenceFactory;
import by.epam.javatraining.beseda.webproject.service.entitybuilder.EntityBuilderFactory;
import by.epam.javatraining.beseda.webproject.service.entitybuilder.RouteBuilder;
import by.epam.javatraining.beseda.webproject.service.entityservice.CarService;
import by.epam.javatraining.beseda.webproject.service.entityservice.ServiceEntityFactory;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import java.util.*;

import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPSessionAttribute.CAR_BUSY_DATES;
import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;

public class CarsDataProcessor {

	private static Logger log = Logger.getLogger(ERROR_LOGGER);
	private static ServiceEntityFactory serviceEntityFactory = ServiceEntityFactory.getFactory();
	private static ServiceDependenceFactory serviceDependenceFactory = ServiceDependenceFactory.getFactory();

	private CarsDataProcessor() {}
	
	public static void processCarsData(HttpSession session) {
		CarService carService = serviceEntityFactory.getCarService();

		Map<Car, SortedSet<Date>> carSetMap = new TreeMap<>();

		try {
			List<Car> carList = carService.getAll();
			for (int i = 0; i < carList.size(); i++) {
				SortedSet<Date> dates = getCarBusyDates(carList.get(i));
				carSetMap.put(carList.get(i), dates);
			}
			session.setAttribute(CAR_BUSY_DATES, carSetMap);
		} catch (ServiceLayerException e) {
			log.error(e);
		}
	}

	private static SortedSet<Date> getCarBusyDates(Car car) throws ServiceLayerException {
		CarRouteService carRouteService = serviceDependenceFactory.getCarRouteService();
		RouteBuilder routeBuilder = EntityBuilderFactory.getFactory().getRouteBuilder();
		SortedSet<Date> dates = new TreeSet<>();

		int[] routesId = carRouteService.getActivePlannedRoutesId(car);

		for (int i = 0; i < routesId.length; i++) {
			Route route = routeBuilder.getEntity(routesId[i]);
			List<Task> taskList = route.getTasksList();
			for (int j = 0; j < taskList.size(); j++) {
				GregorianCalendar gregorianCalendar = taskList.get(j).getTime();
				gregorianCalendar.set(Calendar.AM_PM, 0);
				gregorianCalendar.set(Calendar.HOUR, 0);
				gregorianCalendar.set(Calendar.MINUTE, 0);
				gregorianCalendar.set(Calendar.SECOND, 0);
				gregorianCalendar.set(Calendar.MILLISECOND, 0);
				Date date = gregorianCalendar.getTime();
				dates.add(date);
			}
		}
		return dates;
	}
}