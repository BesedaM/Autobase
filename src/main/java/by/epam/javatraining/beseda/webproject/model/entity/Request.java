package by.epam.javatraining.beseda.webproject.model.entity;

import by.epam.javatraining.beseda.webproject.model.entity.route.Route;
import by.epam.javatraining.beseda.webproject.model.exception.entityexception.request.IllegalDateException;
import by.epam.javatraining.beseda.webproject.model.exception.entityexception.request.IllegalRequestCommentException;
import by.epam.javatraining.beseda.webproject.model.exception.entityexception.request.IllegalRequestRouteException;
import by.epam.javatraining.beseda.webproject.model.exception.entityexception.request.IllegalRequestStatusException;
import by.epam.javatraining.beseda.webproject.model.exception.entityexception.task.IllegalTimeException;

import java.util.GregorianCalendar;
import java.util.Objects;

public class Request extends BaseEntity {

    private Route route;
    private String status;
    private String comment;
    private GregorianCalendar date;

    public Request() {
        super();
    }

    {
        this.date = new GregorianCalendar();
    }

    public Request(String comment, String status) {
        this.comment = comment;
        this.status = status;
    }

    public Request(Route route, String comment, String status) {
        this.route = route;
        this.comment = comment;
        this.status = status;
    }

    public void setRoute(Route route) throws IllegalRequestRouteException {
        if (route != null) {
            this.route = route;
        } else {
            throw new IllegalRequestRouteException();
        }
    }

    public void setStatus(String status) throws IllegalRequestStatusException {
        if (status != null) {
            this.status = status;
        } else {
            throw new IllegalRequestStatusException();
        }
    }

    public void setDate(GregorianCalendar date) throws IllegalDateException {
        if (date != null) {
            this.date = date;
        } else {
            throw new IllegalDateException();
        }
    }

    public void setComment(String comment) throws IllegalRequestCommentException {
        if (comment != null) {
            this.comment = comment;
        } else {
            throw new IllegalRequestCommentException();
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

    public GregorianCalendar getDate() {
        return date;
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
                ", route=" + route +
                ", status=" + status +
                ", comment='" + comment + '\'' +
                ", set on " + date +
                '}';
    }
}
