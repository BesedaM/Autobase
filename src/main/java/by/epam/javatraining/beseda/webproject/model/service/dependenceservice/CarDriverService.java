package by.epam.javatraining.beseda.webproject.model.service.dependenceservice;

import by.epam.javatraining.beseda.webproject.model.entity.car.Car;
import by.epam.javatraining.beseda.webproject.model.entity.user.Driver;

public class CarDriverService extends OneToOneDependenceService<Car, Driver>{

    public CarDriverService() {
        super();
        dependenceDAO = daoDependenceFactory.getCarDriverDAO();
    }

}
