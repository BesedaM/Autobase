package by.epam.javatraining.beseda.webproject.model.entity.route;

import by.epam.javatraining.beseda.webproject.model.entity.BaseEntity;
import by.epam.javatraining.beseda.webproject.model.entity.car.Car;
import by.epam.javatraining.beseda.webproject.model.exception.entityexception.route.IllegalRouteNameException;
import by.epam.javatraining.beseda.webproject.model.exception.entityexception.route.IllegalRouteStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Route extends BaseEntity {

    private String name;
    private List<Task> tasks;
    private List<Car> cars;
    private String status;

    {
        tasks = new ArrayList<>();
        cars = new ArrayList<>();
    }

    public Route() {
        super();
    }

    public Route(String name) {
        this.name = name;
    }

    public Route(String name, String status) {
        this.name = name;
        this.status = status;
    }

    public void setName(String name) throws IllegalRouteNameException {
        if (name != null) {
            this.name = name;
        } else {
            throw new IllegalRouteNameException();
        }
    }

    public void setStatus(String status) throws IllegalRouteStatusException {
        if (status != null) {
            this.status = status;
        } else {
            throw new IllegalRouteStatusException();
        }
    }

    public void addTask(Task task) {
        if (task != null) {
            this.tasks.add(task);
        }
    }

    public void deleteTask(Task task) {
        this.tasks.remove(task);
    }

    public void addCar(Car car) {
        if (car != null) {
            this.cars.add(car);
        }
    }

    public void deleteCar(Car car) {
        this.cars.remove(car);
    }

    public String getName() {
        return name;
    }

    public List<Task> getTasksList() {
        return tasks;
    }

    public List<Car> getCarsList() {
        return cars;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Route)) return false;
        if (!super.equals(o)) return false;
        Route route = (Route) o;
        return Objects.equals(name, route.name) &&
                Objects.equals(tasks, route.tasks) &&
                Objects.equals(cars, route.cars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, tasks, cars);
    }

    @Override
    public String toString() {
        return "Route{" + "id='" + id +
                ", name='" + name + '\'' +
                ", tasks=" + tasks +
                ", cars=" + cars +
                ", status=" + status +
                '}';
    }
}
