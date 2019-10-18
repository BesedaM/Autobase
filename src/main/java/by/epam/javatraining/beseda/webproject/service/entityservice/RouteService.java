package by.epam.javatraining.beseda.webproject.service.entityservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.epam.javatraining.beseda.webproject.dao.entitydao.RouteDAO;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.RouteInterface;
import by.epam.javatraining.beseda.webproject.entity.route.Route;
import by.epam.javatraining.beseda.webproject.service.EnumMap;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceTechnicalException;

import static by.epam.javatraining.beseda.webproject.service.ServiceConstants.ROUTE_STATUS;

@Service
public class RouteService extends AbstractEntityService<Route> {

	@Autowired
	private EnumMap enumMap;
	
	public RouteService() {
		super();
	}

	/**
	 * Creates entity WITHOUT ID with the given data.
	 * 
	 * @param id
	 * @param routeName
	 * @return
	 */
	public Route createRoute(int id, String routeName) {
		Route route = null;
		if (routeName != null) {
			String routeStatus = enumMap.getCollection().get(ROUTE_STATUS).get(1);
			route = new Route(id, routeName, routeStatus);
		}
		return route;
	}

	@Autowired
	public void setDAO(RouteDAO routeDAO) {
		this.entityDAO = routeDAO;
	}

	/**
	 * Updates status of route by route id.
	 * 
	 * @param id     route id
	 * @param status new status value
	 * @throws ServiceTechnicalException
	 */
	public void updateRouteStatus(int id, String status){
		if (id > 0 && status != null) {
			((RouteDAO) entityDAO).updateRouteStatus(id, status);
		}
	}

	public void addCar(int routeId, int carId) {
		((RouteInterface) entityDAO).addCar(routeId, carId);
	}

	public void deleteCar(int routeId, int carId) {
		((RouteInterface) entityDAO).deleteCar(routeId, carId);
	}

	public List<Integer> getCarsId(int routeId) {
		return ((RouteInterface) entityDAO).getCarsId(routeId);
	}

	public List<Integer> getTasksId(int routeId) {
		return ((RouteInterface) entityDAO).getTasksId(routeId);
	}

}