package by.epam.javatraining.beseda.webproject.dao.interfacedao;

import java.util.List;

import by.epam.javatraining.beseda.webproject.dao.exception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.entity.route.Route;

public interface RouteInterface extends EntityDAO<Route> {

    void updateRouteStatus(int id, String status) throws DAOTechnicalException;
    
	void addCar(int routeId, int carId);

	void deleteCar(int routeId, int carId);
	
	List<Integer> getCarsId(int routeId);

	List<Integer> getTasksId(int routeId);
}
