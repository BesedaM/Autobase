package by.epam.javatraining.beseda.webproject.entity.car;

import by.epam.javatraining.beseda.webproject.entity.exception.car.IllegalBusSeatsNumberException;

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

    public void setSeats(int seats) throws IllegalBusSeatsNumberException {
        if(seats>0) {
            this.seats = seats;
        }else{
            throw new IllegalBusSeatsNumberException();
        }
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
        return "Bus{id=" + id +
                ", seats=" + seats +
                ", number='" + number + '\'' +
                ", model='" + model + '\'' +
                ", status=" + status +
                ", state=" + state + '}';
    }
}
