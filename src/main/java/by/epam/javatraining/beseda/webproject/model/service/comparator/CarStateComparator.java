package by.epam.javatraining.beseda.webproject.model.service.comparator;

import by.epam.javatraining.beseda.webproject.model.entity.car.Car;

import java.util.Comparator;

public class CarStateComparator implements Comparator<Car> {

    @Override
    public int compare(Car o1, Car o2) {
        return o1.getState().compareTo(o2.getState());
    }
}
