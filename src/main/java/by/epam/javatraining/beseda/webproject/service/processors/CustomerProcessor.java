package by.epam.javatraining.beseda.webproject.service.processors;

import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPSessionAttribute.DRIVER_MAP;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPSessionAttribute.REQUEST_LIST;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPSessionAttribute.REQUEST_STATUS_LIST;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPSessionAttribute.USER_DATA;
import static by.epam.javatraining.beseda.webproject.service.ServiceConstants.REQUEST_STATUS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.epam.javatraining.beseda.webproject.dao.exception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.CarInterface;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.DriverInterface;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.RequestInterface;
import by.epam.javatraining.beseda.webproject.entity.Request;
import by.epam.javatraining.beseda.webproject.entity.car.Car;
import by.epam.javatraining.beseda.webproject.entity.route.Route;
import by.epam.javatraining.beseda.webproject.entity.user.Customer;
import by.epam.javatraining.beseda.webproject.entity.user.Driver;
import by.epam.javatraining.beseda.webproject.service.entitybuilder.RouteBuilder;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLogicException;
import by.epam.javatraining.beseda.webproject.util.ReversalHashMap;

@Component
public class CustomerProcessor {

	@Autowired
	private Map<String, ReversalHashMap<Integer, String>> enumCollection;

	@Autowired
	private RequestInterface requestDAO;

	@Autowired
	private RouteBuilder routeBuilder;

	@Autowired
	private CarInterface carDAO;

	@Autowired
	private DriverInterface driverDAO;

	public CustomerProcessor() {
	}

	public void processCustomerData(HttpSession session) throws ServiceLayerException {
		Customer customer = (Customer) session.getAttribute(USER_DATA);
		List<Request> requests = null;
		Map<Integer, List<Driver>> driverMap = null;
		try {
			requests = requestDAO.selectActiveCustomerRequests(customer.getId());
			driverMap = new HashMap<>();
			for (int i = 0; i < requests.size(); i++) {
				Request req = requests.get(i);
				Route route = routeBuilder.getEntity(req.getId());

				if (route != null) {
					routeBuilder.addCarList(route);
					req.setRoute(route);
				}
				List<Driver> driverList = addDriversData(route);
				driverMap.put(req.getId(), driverList);
			}
			
		} catch (DAOLayerException e) {
			throw new ServiceLogicException(e);
		}
		session.setAttribute(REQUEST_LIST, requests);
		session.setAttribute(REQUEST_STATUS_LIST, enumCollection.get(REQUEST_STATUS));
		session.setAttribute(DRIVER_MAP, driverMap);
	}

	private List<Driver> addDriversData(Route route) throws DAOLayerException {
		List<Driver> driverList = new ArrayList<>();
		if (route != null) {
			Set<Car> carList = route.getCarsList();

			for (Car car : carList) {
				int driverId = carDAO.getDriverId(car.getId());
				Driver driver = driverDAO.getEntityById(driverId);
				driverList.add(driver);
			}
		}
		return driverList;
	}
}