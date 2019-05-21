package by.epam.javatraining.beseda.webproject.model.entity.car;

import by.epam.javatraining.beseda.webproject.model.entity.BaseEntity;
import by.epam.javatraining.beseda.webproject.model.exception.entityexception.car.IllegalCarModelException;
import by.epam.javatraining.beseda.webproject.model.exception.entityexception.car.IllegalCarNumberException;
import by.epam.javatraining.beseda.webproject.model.exception.entityexception.car.IllegalCarStateException;
import by.epam.javatraining.beseda.webproject.model.exception.entityexception.car.IllegalCarStatusException;

import java.util.Objects;

public class Car extends BaseEntity {

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

    public void setNumber(String number) throws IllegalCarNumberException {
        if (number != null) {
            this.number = number;
        } else {
            throw new IllegalCarNumberException();
        }
    }

    public void setModel(String model) throws IllegalCarModelException {
        if (model != null) {
            this.model = model;
        } else {
            throw new IllegalCarModelException();
        }
    }

    public void setStatus(String status) throws IllegalCarStatusException {
        if (status != null) {
            this.status = status;
        } else {
            throw new IllegalCarStatusException();
        }
    }

    public void setState(String state) throws IllegalCarStateException {
        if (state != null) {
            this.state = state;
        } else {
            throw new IllegalCarStateException();
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
