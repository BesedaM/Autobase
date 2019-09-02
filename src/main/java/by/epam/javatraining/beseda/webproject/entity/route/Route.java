package by.epam.javatraining.beseda.webproject.entity.route;

import by.epam.javatraining.beseda.webproject.entity.BaseEntity;
import by.epam.javatraining.beseda.webproject.entity.car.Car;
import by.epam.javatraining.beseda.webproject.entity.exception.route.IllegalRouteNameException;
import by.epam.javatraining.beseda.webproject.entity.exception.route.IllegalRouteStatusException;

import java.util.*;

public class Route extends BaseEntity {

    private String name;
    private List<Task> tasks;
    private Set<Car> cars;
    private String status;

    {
        tasks = new ArrayList<>();
        cars = new HashSet<>();
    }

    public Route() {
        super();
    }

    public Route(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Route(int id, String name, String status) {
        this.id = id;
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
        if (task != null && !this.tasks.contains(task)) {
            this.tasks.add(task);
        }
    }

    public void removeTask(int taskId) {
        if (taskId > 0) {
            ListIterator<Task> it = tasks.listIterator();
            while (it.hasNext()) {
                Task task = it.next();
                if (task.getId() == taskId) {
                    it.remove();
                }
            }
        }
    }


    public void addCar(Car car) {
        if (car != null) {
            this.cars.add(car);
        }
    }

    public void removeCar(int carId) {
        if (carId > 0) {
            for (Car car : this.cars
                    ) {
                if (car.getId() == carId) {
                    this.cars.remove(car);
                }
            }
        }
    }

    public String getName() {
        return name;
    }

    public List<Task> getTasksList() {
        return tasks;
    }

    public Set<Car> getCarsList() {
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