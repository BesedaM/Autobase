package by.epam.javatraining.beseda.webproject.service.dependenceservice;

import by.epam.javatraining.beseda.webproject.entity.car.Car;
import by.epam.javatraining.beseda.webproject.entity.user.Driver;

public class CarDriverService extends OneToOneDependenceService<Car, Driver>{

    CarDriverService() {
        super();
        dependenceDAO = daoDependenceFactory.getCarDriverDAO();
    }

}
