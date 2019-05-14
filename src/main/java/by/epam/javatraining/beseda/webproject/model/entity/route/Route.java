package by.epam.javatraining.beseda.webproject.model.entity.route;

import by.epam.javatraining.beseda.webproject.model.entity.BaseEntity;
import by.epam.javatraining.beseda.webproject.model.entity.car.Car;

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

    public void setName(String name) {
        this.name = name;
    }

    public void setTasksList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void setCarsList(List<Car> cars) {
        this.cars = cars;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void deleteTask(Task task) {
        if (this.tasks.contains(task)) {
            this.tasks.remove(task);
        }
    }

    public void addCar(Car car) {
        this.cars.add(car);
    }

    public void deleteCar(Car car) {
        if (this.cars.contains(car)) {
            this.cars.remove(car);
        }
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
        return "Route{" +"id='" + id +
                ", name='" + name + '\'' +
                ", tasks=" + tasks +
                ", cars=" + cars +
                ", status=" + status +
                '}';
    }
}
