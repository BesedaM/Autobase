package by.epam.javatraining.beseda.webproject.model.service.dependenceservice;

import by.epam.javatraining.beseda.webproject.model.dao.dependencedao.CarRouteDependenceDAO;
import by.epam.javatraining.beseda.webproject.model.dao.entitydao.CarDAO;
import by.epam.javatraining.beseda.webproject.model.dao.entitydao.RouteDAO;
import by.epam.javatraining.beseda.webproject.model.entity.car.Car;
import by.epam.javatraining.beseda.webproject.model.entity.route.Route;
import by.epam.javatraining.beseda.webproject.model.exception.daoexception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.model.exception.daoexception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.model.exception.serviceexception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.model.exception.serviceexception.ServiceTechnicalException;

import java.util.List;

public class CarRouteService {

    private CarRouteDependenceDAO carRouteDependenceDAO;

    private CarRouteService() {
        carRouteDependenceDAO = CarRouteDependenceDAO.getDAO();
    }

    private static class SingletonHolder {
        public static final CarRouteService instance = new CarRouteService();
    }

    public static CarRouteService getService() {
        return SingletonHolder.instance;
    }


    public boolean addDependence(Car car, Route route) throws ServiceLayerException {
        boolean succeed = false;
        if (car != null && route != null) {
            try {
                carRouteDependenceDAO.setDependence(car, route);
                route.addCar(car);
                succeed = true;
            } catch (DAOTechnicalException e) {
                throw new ServiceTechnicalException(e);
            }
        }
        return succeed;
    }

    public boolean deleteDependence(Car car, Route route) throws ServiceLayerException {
        boolean succeed = false;
        if (car != null && route != null) {
            try {
                carRouteDependenceDAO.deleteDependence(car, route);
                route.removeCar(car);
                succeed = true;
            } catch (DAOTechnicalException e) {
                throw new ServiceTechnicalException();
            }
        }
        return succeed;
    }

    public List<Car> getCars(Route route) throws ServiceLayerException {
        List<Car> carList = null;
        if (route != null) {
            try {
                int[] carsId = carRouteDependenceDAO.getDependencesId(route);
                carList = CarDAO.getDAO().getEntitiesByIdList(carsId);
                route.setCarsList(carList);
            } catch (DAOLayerException e) {
                throw new ServiceLayerException(e);
            }
        }
        return carList;
    }

    public List<Route> getRoutes(Car car) throws ServiceLayerException {
        List<Route> routeList = null;
        if (car != null) {
            try {
                int[] routesId = carRouteDependenceDAO.getDependencesId(car);
                routeList = RouteDAO.getDAO().getEntitiesByIdList(routesId);
            } catch (DAOLayerException e) {
                throw new ServiceLayerException(e);
            }
        }
        return routeList;
    }
}
