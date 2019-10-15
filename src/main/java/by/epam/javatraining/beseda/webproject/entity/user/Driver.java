package by.epam.javatraining.beseda.webproject.entity.user;

import java.util.Objects;

import by.epam.javatraining.beseda.webproject.entity.car.Car;

public class Driver extends Person {
	protected Car car;

	public Driver() {
	}

	public Driver(User userData, String name, String surname, String phone) {
		super(userData, name, surname, phone);
	}

	public Driver(String login, byte[] password, String name, String surname, String phone) {
		super(login, password, name, surname, phone);
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public void removeCar() {
		car = null;
	}

	public Car getCar() {
		return car;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Driver))
			return false;
		if (!super.equals(o))
			return false;
		Driver driver = (Driver) o;
		return Objects.equals(car, driver.car);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), car);
	}

	@Override
	public String toString() {
		return "Driver{" + "id=" + id + ", car=" + car + ", name='" + name + '\'' + ", surname='" + surname + '\''
				+ ", phone='" + phone + '\'' + '}';
	}

}