package by.epam.javatraining.beseda.webproject.entity;

import by.epam.javatraining.beseda.webproject.entity.exception.RequestException;
import by.epam.javatraining.beseda.webproject.entity.route.Route;
import by.epam.javatraining.beseda.webproject.entity.user.Customer;

import java.util.GregorianCalendar;
import java.util.Objects;

public class Request extends EntityBase {

    private Route route;
    private Customer customer;
    private String status;
    private String comment;
    private GregorianCalendar creationTime;

    public Request() {
        super();
    }

    {
        this.creationTime = new GregorianCalendar();
    }

    public Request(Customer customer, String comment, String status) {
        super();
        this.customer = customer;
        this.comment = comment;
        this.status = status;
    }

    public Request(Customer customer,Route route, String comment, String status) {
        this(customer,comment,status);
        this.route = route;
    }

    public void setRoute(Route route) throws RequestException {
        if (route != null) {
            this.route = route;
        } else {
            throw new RequestException();
        }
    }

    public void setStatus(String status) throws RequestException {
        if (status != null) {
            this.status = status;
        } else {
            throw new RequestException();
        }
    }

    public void setCreationTime(GregorianCalendar creationTime) throws RequestException {
        if (creationTime != null) {
            this.creationTime = creationTime;
        } else {
            throw new RequestException();
        }
    }

    public void setComment(String comment) throws RequestException {
        if (comment != null) {
            this.comment = comment;
        } else {
            throw new RequestException();
        }
    }

    public void setCustomer(Customer customer) throws RequestException {
        if (customer != null) {
            this.customer = customer;
        } else {
            throw new RequestException();
        }
    }

    public void removeRoute() {
        route = null;
    }

    public Route getRoute() {
        return route;
    }

    public String getStatus() {
        return status;
    }

    public String getComment() {
        return comment;
    }

    public GregorianCalendar getCreationTime() {
        return creationTime;
    }

    public Customer getCustomer() {
        return customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Request)) return false;
        if (!super.equals(o)) return false;
        Request request = (Request) o;
        return Objects.equals(route, request.route) &&
                Objects.equals(comment, request.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), route, comment);
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", route=" + route + ", customer=" + customer +
                ", status=" + status +
                ", comment='" + comment + '\'' +
                ", set on " + creationTime +
                '}';
    }
}
