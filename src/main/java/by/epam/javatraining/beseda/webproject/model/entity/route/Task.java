package by.epam.javatraining.beseda.webproject.model.entity.route;

import by.epam.javatraining.beseda.webproject.model.entity.BaseEntity;

import java.util.GregorianCalendar;
import java.util.Objects;

public class Task extends BaseEntity {

    private Address address;
    private GregorianCalendar time;
    private String detail;

    public Task() {
        super();
    }

    public Task(Address address, GregorianCalendar time, String detail) {
        this.address = address;
        this.time = time;
        this.detail = detail;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setTime(GregorianCalendar time) {
        this.time = time;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Address getAddress() {
        return address;
    }

    public GregorianCalendar getTime() {
        return time;
    }

    public String getDetail() {
        return detail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        if (!super.equals(o)) return false;
        Task task = (Task) o;
        return Objects.equals(address, task.address) &&
                Objects.equals(time, task.time) &&
                Objects.equals(detail, task.detail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), address, time, detail);
    }

    @Override
    public String toString() {
        return "Task{" +
                "address=" + address +
                ", time=" + time +
                ", detail='" + detail + '\'' +
                '}';
    }
}
