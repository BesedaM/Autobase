package by.epam.javatraining.beseda.webproject.entity.route;

import by.epam.javatraining.beseda.webproject.entity.EntityBase;
import by.epam.javatraining.beseda.webproject.entity.DefaultValue;
import by.epam.javatraining.beseda.webproject.entity.exception.AddressException;

import java.util.Objects;

public class Address extends EntityBase {

	private String country;
	private String district;
	private String city;
	private String street;
	private int house;
	private String building;

	{
		country = DefaultValue.COUNTRY;
		district = DefaultValue.DISTRICT;
		city = DefaultValue.EMPTY_STRING;
		street = DefaultValue.EMPTY_STRING;
		house = DefaultValue.ZERO;
		building = DefaultValue.EMPTY_STRING;
	}

	public Address() {
		super();
	}

	public Address(String country, String district, String city) {
		super();
		this.country = country;
		this.district = district;
		this.city = city;
	}

	public Address(String country, String district, String city, String street, int house, String building) {
		super();
		this.country = country;
		this.district = district;
		this.city = city;
		if (street != null) {
			this.street = street;
		}
		if (house > 0) {
			this.house = house;
		}
		if (building != null) {
			this.building = building;
		}
	}

	public void setCountry(String country) throws AddressException {
		if (country != null) {
			this.country = country;
		} else {
			throw new AddressException();
		}
	}

	public void setDistrict(String district) throws AddressException {
		if (district != null) {
			this.district = district;
		} else {
			throw new AddressException();
		}
	}

	public void setCity(String city) throws AddressException {
		if (city != null) {
			this.city = city;
		} else {
			throw new AddressException();
		}
	}

	public void setStreet(String street) throws AddressException {
		if (street != null) {
			this.street = street;
		} else {
			throw new AddressException();
		}
	}

	public void setHouse(int house) throws AddressException {
		if (house >= 0) {
			this.house = house;
		} else {
			throw new AddressException();
		}
	}

	public void setBuilding(String building) throws AddressException {
		if (building != null) {
			this.building = building;
		} else {
			throw new AddressException();
		}
	}

	public String getCountry() {
		return country;
	}

	public String getDistrict() {
		return district;
	}

	public String getCity() {
		return city;
	}

	public String getStreet() {
		return street;
	}

	public int getHouse() {
		return house;
	}

	public String getBuilding() {
		return building;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Address))
			return false;
		if (!super.equals(o))
			return false;
		Address address = (Address) o;
		return house == address.house && Objects.equals(country, address.country)
				&& Objects.equals(district, address.district) && Objects.equals(city, address.city)
				&& Objects.equals(street, address.street) && Objects.equals(building, address.building);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), country, district, city, street, house, building);
	}

	@Override
	public String toString() {
		String addr = country + ", " + district + ", " + city;
		if (!building.equals(DefaultValue.EMPTY_STRING) && !building.equals(DefaultValue.HYPHEN)) {
			addr = addr + ", " + street + ", " + house + DefaultValue.SLASH + building;
		} else if (house != DefaultValue.ZERO) {
			addr = addr + ", " + street + ", " + house;
		} else if (!street.equals(DefaultValue.EMPTY_STRING)) {
			addr = addr + ", " + street;
		}
		return addr;
	}

}