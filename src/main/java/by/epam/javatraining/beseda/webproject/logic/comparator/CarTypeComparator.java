package by.epam.javatraining.beseda.webproject.logic.comparator;

import by.epam.javatraining.beseda.webproject.entity.car.Car;

import java.util.Comparator;

public class CarTypeComparator implements Comparator<Car> {

    @Override
    public int compare(Car o1, Car o2) {
        return o1.getClass().getSimpleName().compareTo(o2.getClass().getSimpleName());
    }
}
