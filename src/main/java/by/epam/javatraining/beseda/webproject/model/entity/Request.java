package by.epam.javatraining.beseda.webproject.model.entity;

import by.epam.javatraining.beseda.webproject.model.entity.route.Route;
import by.epam.javatraining.beseda.webproject.model.entity.user.Customer;
import by.epam.javatraining.beseda.webproject.model.exception.EntityException.request.IllegalRequestCommentException;
import by.epam.javatraining.beseda.webproject.model.exception.EntityException.request.IllegalRequestRouteException;
import by.epam.javatraining.beseda.webproject.model.exception.EntityException.request.IllegalRequestStatusException;

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

    public void setRoute(Route route) throws IllegalRequestRouteException {
        if(route!=null) {
            this.route = route;
        }else{
            throw new IllegalRequestRouteException();
        }
    }

    public void setStatus(String status) throws IllegalRequestStatusException {
        if(status!=null) {
            this.status = status;
        }else{
            throw new IllegalRequestStatusException();
        }
    }

    public void setComment(String comment) throws IllegalRequestCommentException {
        if(comment!=null) {
            this.comment = comment;
        }else{
            throw new IllegalRequestCommentException();
        }
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
