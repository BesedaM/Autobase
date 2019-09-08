package by.epam.javatraining.beseda.webproject.dao.interfacedao;

import by.epam.javatraining.beseda.webproject.dao.exception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOTechnicalException;
import by.epam.javatraining.beseda.webproject.entity.car.Car;

import java.util.List;

public interface CarInterface extends EntityDAO<Car>{

    List<Car> getCarsByType(String carType) throws DAOLayerException;

    void updateCarState(int id, String state) throws DAOTechnicalException;
}
