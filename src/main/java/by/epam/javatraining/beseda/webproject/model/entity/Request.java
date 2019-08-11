package by.epam.javatraining.beseda.webproject.model.entity;

import by.epam.javatraining.beseda.webproject.model.entity.route.Route;
import by.epam.javatraining.beseda.webproject.model.exception.entityexception.request.IllegalDateException;
import by.epam.javatraining.beseda.webproject.model.exception.entityexception.request.IllegalRequestCommentException;
import by.epam.javatraining.beseda.webproject.model.exception.entityexception.request.IllegalRouteException;
import by.epam.javatraining.beseda.webproject.model.exception.entityexception.request.IllegalRequestStatusException;

import java.util.GregorianCalendar;
import java.util.Objects;

public class Request extends BaseEntity {

    private Route route;
    private String status;
    private String comment;
    private GregorianCalendar creationDate;

    public Request() {
        super();
    }

    {
        this.creationDate = new GregorianCalendar();
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

    public void setRoute(Route route) throws IllegalRouteException {
        if (route != null) {
            this.route = route;
        } else {
            throw new IllegalRouteException();
        }
    }

    public void setStatus(String status) throws IllegalRequestStatusException {
        if (status != null) {
            this.status = status;
        } else {
            throw new IllegalRequestStatusException();
        }
    }

    public void setCreationDate(GregorianCalendar creationDate) throws IllegalDateException {
        if (creationDate != null) {
            this.creationDate = creationDate;
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

    public GregorianCalendar getCreationDate() {
        return creationDate;
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
                ", set on " + creationDate +
                '}';
    }
}
