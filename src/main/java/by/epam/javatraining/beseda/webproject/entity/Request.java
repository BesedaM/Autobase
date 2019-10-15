package by.epam.javatraining.beseda.webproject.entity;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Objects;

import by.epam.javatraining.beseda.webproject.entity.route.Route;
import by.epam.javatraining.beseda.webproject.entity.user.Customer;
import static by.epam.javatraining.beseda.webproject.entity.DefaultValue.REQUEST_DEFAULT_STATUS;

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
		this.status = REQUEST_DEFAULT_STATUS;
	}

	public Request(Customer customer, String comment) {
		super();
		this.customer = customer;
		this.comment = comment;
	}
	
	public Request(Customer customer, String comment, String status) {
		super();
		this.customer = customer;
		this.comment = comment;
		this.status = status;
	}

	public Request(Customer customer, Route route, String comment, String status) {
		this(customer, comment, status);
		this.route = route;
	}

	public void setRoute(Route route) {
			this.route = route;
	}

	public void setStatus(String status) {
			this.status = status;
	}

	public void setCreationTime(GregorianCalendar creationTime) {
			this.creationTime = creationTime;
	}

	public void setComment(String comment) {
			this.comment = comment;
	}

	public void setCustomer(Customer customer) {
			this.customer = customer;
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
		if (this == o)
			return true;
		if (!(o instanceof Request))
			return false;
		if (!super.equals(o))
			return false;
		Request request = (Request) o;
		return Objects.equals(route, request.route) && Objects.equals(comment, request.comment);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), route, comment);
	}

	@Override
	public String toString() {
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return "Request{" + "id=" + id + ", route=" + route + ", customer=" + customer + ", status=" + status
				+ ", comment='" + comment + '\'' + ", set on " + sdfDate.format(creationTime) + '}';
	}

}