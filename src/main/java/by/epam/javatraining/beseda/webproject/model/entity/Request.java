package by.epam.javatraining.beseda.webproject.model.entity;

import by.epam.javatraining.beseda.webproject.model.entity.route.Route;
import by.epam.javatraining.beseda.webproject.model.entity.user.Customer;

import java.util.Objects;

public class Request extends BaseEntity {

    private Route route;
    private String status;
    private String comment;

    public Request() {
        super();
    }

    public Request(Route route, String comment, String status) {
        this.route = route;
        this.comment = comment;
        this.status = status;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Request)) return false;
        if (!super.equals(o)) return false;
        Request request = (Request) o;
        return  Objects.equals(route, request.route) &&
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
                ", route=" + route +
                ", status=" + status +
                ", comment='" + comment + '\'' +
                '}';
    }
}
