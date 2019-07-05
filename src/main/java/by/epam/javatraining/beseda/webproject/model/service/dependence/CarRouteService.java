package by.epam.javatraining.beseda.webproject.model.service.dependence;

import by.epam.javatraining.beseda.webproject.model.entity.car.Car;
import by.epam.javatraining.beseda.webproject.model.entity.route.Route;

public class CarRouteService extends ManyToManyDependenceService<Car, Route> {

    public CarRouteService() {
        super();
        dependenceDAO = DAODependenceFactory.getCarRouteDAO();
    }

//    private static class SingletonHolder {
//        public static final CarRouteService instance = new CarRouteService();
//    }
//
//    public static CarRouteService getService() {
//        return SingletonHolder.instance;
//    }


//    public void addDependence(Car car, Route route) throws ServiceTechnicalException {
////        boolean succeed = false;
//        if (car != null && route != null) {
//            try {
//                carRouteDependenceDAO.setDependence(car, route);
////                route.addCar(car);
////                succeed = true;
//            } catch (DAOTechnicalException e) {
//                throw new ServiceTechnicalException(e);
//            }
//        }
////        return succeed;
//    }

//    public void deleteDependence(Car car, Route route) throws ServiceTechnicalException {
////        boolean succeed = false;
//        if (car != null && route != null) {
//            try {
//                carRouteDependenceDAO.deleteDependence(car, route);
////                route.removeCar(car);
////                succeed = true;
//            } catch (DAOTechnicalException e) {
//                throw new ServiceTechnicalException();
//            }
//        }
////        return succeed;
//    }

//    public List<Car> getCars(Route route) throws ServiceLayerException {
//        List<Car> carList = null;
//        if (route != null) {
//            try {
//                int[] carsId = carRouteDependenceDAO.getDependencesId(route);
//                carList = CarDAO.getDAO().getEntitiesByIdList(carsId);
//                route.setCarsList(carList);
//            } catch (DAOLayerException e) {
//                throw new ServiceLayerException(e);
//            }
//        }
//        return carList;
//    }
//
//    public List<Route> getRoutes(Car car) throws ServiceLayerException {
//        List<Route> routeList = null;
//        if (car != null) {
//            try {
//                int[] routesId = carRouteDependenceDAO.getDependencesId(car);
//                routeList = RouteDAO.getDAO().getEntitiesByIdList(routesId);
//            } catch (DAOLayerException e) {
//                throw new ServiceLayerException(e);
//            }
//        }
//        return routeList;
//    }
}
