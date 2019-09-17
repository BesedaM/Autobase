package by.epam.javatraining.beseda.webproject.service.dependenceservice;

import by.epam.javatraining.beseda.webproject.dao.dependencedao.CarRouteDependenceDAO;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.entity.car.Car;
import by.epam.javatraining.beseda.webproject.entity.route.Route;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceTechnicalException;

public class CarRouteService extends ManyToManyDependenceService<Car, Route> {

	CarRouteService() {
		super();
		dependenceDAO = daoDependenceFactory.getCarRouteDAO();
	}

	public int getActiveRouteId(Car car) throws ServiceTechnicalException {
		int id = 0;
		if (car != null) {
			try {
				id = ((CarRouteDependenceDAO) dependenceDAO).getActiveRouteId(car);
			} catch (DAOTechnicalException e) {
				throw new ServiceTechnicalException(e);
			}
		}
		return id;
	}

	public int[] getActivePlannedRoutesId(Car car) throws ServiceTechnicalException {
		int[] ids = null;
		if (car != null) {
			try {
				ids = ((CarRouteDependenceDAO) dependenceDAO).getActivePlannedRoutesId(car);
			} catch (DAOTechnicalException e) {
				throw new ServiceTechnicalException(e);
			}
		}
		return ids;
	}

}