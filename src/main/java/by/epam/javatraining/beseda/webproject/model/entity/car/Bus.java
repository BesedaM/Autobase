package by.epam.javatraining.beseda.webproject.model.entity.car;

import java.util.Objects;

public class Bus extends Car{

    private int seats;

    public Bus() {
        super();
    }

    public Bus(String number, String model, int seats) {
        super(number, model);
        this.seats = seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public int getSeats() {
        return seats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bus)) return false;
        if (!super.equals(o)) return false;
        Bus bus = (Bus) o;
        return seats == bus.seats;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), seats);
    }

    @Override
    public String toString() {
        return "Bus{" +", id=" + id +
                ", seats=" + seats +
                ", number='" + number + '\'' +
                ", model='" + model + '\'' +
                ", status=" + status +
                ", state=" + state + '}';
    }
}
