package by.epam.javatraining.beseda.webproject.model.service.dependence;

import by.epam.javatraining.beseda.webproject.model.entity.car.Car;
import by.epam.javatraining.beseda.webproject.model.entity.user.Driver;

public class CarDriverService extends OneToOneDependenceService<Car, Driver>{

    public CarDriverService() {
        super();
        dependenceDAO = DAODependenceFactory.getCarDriverDAO();
    }

//    private static class SingletonHolder {
//        public static final CarDriverService instance = new CarDriverService();
//    }
//
//    public static CarDriverService getService() {
//        return SingletonHolder.instance;
//    }

//
//    public void addDependence(Car car, Driver driver) throws ServiceTechnicalException {
////        boolean succeed = false;
//        if (car != null && driver != null) {
//            try {
//                carDriverDependenceDAO.setDependence(car, driver);
////                driver.setCar(car);
////                succeed = true;
//            } catch (DAOTechnicalException e) {
//                throw new ServiceTechnicalException();
////            } catch (IllegalCarException e) {
////                throw new ServiceLogicException(e);
//            }
//        }
////        return succeed;
//    }

//    public void deleteDependence(Car car, Driver driver) throws ServiceTechnicalException {
////        boolean succeed = false;
//        if (car != null && driver != null) {
//            try {
//                carDriverDependenceDAO.deleteDependence(car);
//  //              driver.removeCar();
//    //            succeed = true;
//            } catch (DAOTechnicalException e) {
//                throw new ServiceTechnicalException();
//            }
//        }
//      //  return succeed;
//    }

//    public Car getCar(Driver driver) throws ServiceLayerException {
//        Car car = null;
//        if (driver != null) {
//            try {
//                int carId = carDriverDependenceDAO.getEntityIdByDependence(driver);
//                car = CarDAO.getDAO().getEntityById(carId);
//                driver.setCar(car);
//            } catch (DAOLayerException e) {
//                throw new ServiceLayerException(e);
//            } catch (IllegalCarException e) {
//                throw new ServiceLogicException(e);
//            }
//        }
//        return car;
//    }

//    public Driver getDriver(Car car) throws ServiceLayerException {
//        Driver driver = null;
//        if (car != null) {
//            try {
//                int driverId = carDriverDependenceDAO.getDependenceId(car);
//                driver = DriverDAO.getDAO().getEntityById(driverId);
//                driver.setCar(car);
//            } catch (DAOLayerException e) {
//                throw new ServiceLayerException(e);
//            } catch (IllegalCarException e) {
//                throw new ServiceLogicException(e);
//            }
//        }
//        return driver;
//    }
}
