package by.epam.javatraining.beseda.webproject.entity.car;

import by.epam.javatraining.beseda.webproject.entity.EntityBase;
import by.epam.javatraining.beseda.webproject.entity.exception.CarException;

import java.util.Objects;

public class Car extends EntityBase {

    protected String number;
    protected String model;
    protected String status;
    protected String state;

    public Car() {
        super();
    }

    public Car(String number, String model) {
        super();
        this.number = number;
        this.model = model;
    }

    public void setNumber(String number) throws CarException {
        if (number != null) {
            this.number = number;
        } else {
            throw new CarException();
        }
    }

    public void setModel(String model) throws CarException {
        if (model != null) {
            this.model = model;
        } else {
            throw new CarException();
        }
    }

    public void setStatus(String status) throws CarException {
        if (status != null) {
            this.status = status;
        } else {
            throw new CarException();
        }
    }

    public void setState(String state) throws CarException {
        if (state != null) {
            this.state = state;
        } else {
            throw new CarException();
        }
    }

    public String getNumber() {
        return number;
    }

    public String getModel() {
        return model;
    }

    public String getStatus() {
        return status;
    }

    public String getState() {
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        Car car = (Car) o;
        return Objects.equals(number, car.number) &&
                Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, model);
    }

    @Override
    public String toString() {
        return "Car{" + "id=" + id +
                ", number='" + number + '\'' +
                ", model='" + model + '\'' +
                ", status=" + status +
                ", state=" + state + '}';
    }
}
