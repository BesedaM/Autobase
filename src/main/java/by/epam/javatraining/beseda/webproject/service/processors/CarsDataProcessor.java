package by.epam.javatraining.beseda.webproject.service.processors;

import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPSessionAttribute.CAR_BUSY_DATES;
import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.epam.javatraining.beseda.webproject.dao.exception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.CarInterface;
import by.epam.javatraining.beseda.webproject.entity.car.Car;
import by.epam.javatraining.beseda.webproject.entity.route.Route;
import by.epam.javatraining.beseda.webproject.entity.route.Task;
import by.epam.javatraining.beseda.webproject.service.entitybuilder.RouteBuilder;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;

@Component
public class CarsDataProcessor {

	private static Logger log = Logger.getLogger(ERROR_LOGGER);

	@Autowired
	private CarInterface carDAO;

	@Autowired
	private RouteBuilder routeBuilder;

	public CarsDataProcessor() {
	}

	public void processCarsData(HttpSession session) {
		Map<Car, SortedSet<Date>> carSetMap = new TreeMap<>();

		try {
			List<Car> carList = carDAO.getAll();
			for (int i = 0; i < carList.size(); i++) {
				SortedSet<Date> dates = getCarBusyDates(carList.get(i));
				carSetMap.put(carList.get(i), dates);
			}
			session.setAttribute(CAR_BUSY_DATES, carSetMap);
		} catch (ServiceLayerException e) {
			log.error(e);
		}
	}

	private SortedSet<Date> getCarBusyDates(Car car) throws ServiceLayerException {
		SortedSet<Date> dates = new TreeSet<>();

		List<Integer> routesId = carDAO.getActivePlannedRoutesId(car.getId());

		for (int i = 0; i < routesId.size(); i++) {
			Route route = routeBuilder.getEntity(routesId.get(i));
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