package by.epam.javatraining.beseda.webproject.model.entity.route;

import by.epam.javatraining.beseda.webproject.model.entity.BaseEntity;
import by.epam.javatraining.beseda.webproject.model.exception.EntityException.task.IllegalAddressException;
import by.epam.javatraining.beseda.webproject.model.exception.EntityException.task.IllegalDetailException;
import by.epam.javatraining.beseda.webproject.model.exception.EntityException.task.IllegalTimeException;

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

    public void setAddress(Address address) throws IllegalAddressException {
        if(address!=null) {
            this.address = address;
        }else{
            throw new IllegalAddressException();
        }
    }

    public void setTime(GregorianCalendar time) throws IllegalTimeException {
        if(time!=null) {
            this.time = time;
        }else{
            throw new IllegalTimeException();
        }
    }

    public void setDetail(String detail) throws IllegalDetailException {
        if(detail!=null) {
            this.detail = detail;
        }else{
            throw new IllegalDetailException();
        }
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
