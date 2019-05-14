package by.epam.javatraining.beseda.webproject.model.entity.car;

import java.util.Objects;

public class Truck extends Car {

    private int capacity;

    public Truck() {
        super();
    }

    public Truck(String number, String model, int capacity) {
        super(number, model);
        this.capacity = capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Truck)) return false;
        if (!super.equals(o)) return false;
        Truck truck = (Truck) o;
        return capacity == truck.capacity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), capacity);
    }

    @Override
    public String toString() {
        return "Truck{" + "id=" + id +
                ", capacity=" + capacity +
                ", number='" + number + '\'' +
                ", model='" + model + '\'' +
                ", status=" + status +
                ", state=" + state + '}';
    }
}
