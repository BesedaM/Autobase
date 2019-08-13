package by.epam.javatraining.beseda.webproject.model.service.dependenceservice;

import by.epam.javatraining.beseda.webproject.model.dao.dependencedao.CarRouteDependenceDAO;
import by.epam.javatraining.beseda.webproject.model.dao.exception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.model.entity.car.Car;
import by.epam.javatraining.beseda.webproject.model.entity.route.Route;
import by.epam.javatraining.beseda.webproject.model.service.exception.ServiceTechnicalException;

public class CarRouteService extends ManyToManyDependenceService<Car, Route> {

    public CarRouteService() {
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
