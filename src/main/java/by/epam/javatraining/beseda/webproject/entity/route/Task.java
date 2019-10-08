package by.epam.javatraining.beseda.webproject.entity.route;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Objects;

import by.epam.javatraining.beseda.webproject.entity.EntityBase;
import by.epam.javatraining.beseda.webproject.entity.exception.TaskException;

public class Task extends EntityBase {

	private Address address;
	private GregorianCalendar time;
	private String details;

	public Task() {
		super();
	}

	public Task(GregorianCalendar time, String details) {
		this.time = time;
		this.details = details;
	}

	public Task(Address address, GregorianCalendar time, String details) {
		this.address = address;
		this.time = time;
		this.details = details;
	}

	public void setAddress(Address address) throws TaskException {
		if (address != null) {
			this.address = address;
		} else {
			throw new TaskException();
		}
	}

	public void removeAddress() {
		this.address = null;
	}

	public void setTime(GregorianCalendar time) throws TaskException {
		if (time != null) {
			this.time = time;
		} else {
			throw new TaskException();
		}
	}

	public void setDetails(String details) throws TaskException {
		if (details != null) {
			this.details = details;
		} else {
			throw new TaskException();
		}
	}

	public Address getAddress() {
		return address;
	}

	public GregorianCalendar getTime() {
		return time;
	}

	public String getDetails() {
		return details;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Task))
			return false;
		if (!super.equals(o))
			return false;
		Task task = (Task) o;
		return Objects.equals(address, task.address) && Objects.equals(time, task.time)
				&& Objects.equals(details, task.details);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), address, time, details);
	}

	@Override
	public String toString() {
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return "Task{" + "address=" + address + ", time=" + sdfDate.format(time) + ", details='" + details + '\'' + '}';
	}

}