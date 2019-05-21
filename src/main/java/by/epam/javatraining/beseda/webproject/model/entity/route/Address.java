package by.epam.javatraining.beseda.webproject.model.entity.route;

import by.epam.javatraining.beseda.webproject.model.entity.BaseEntity;
import by.epam.javatraining.beseda.webproject.model.exception.entityexception.address.*;

import java.util.Objects;

public class Address extends BaseEntity {

    private String country;
    private String district;
    private String city;
    private String street;
    private int house;
    private String building;

    {
        country = "Беларусь";
        district = "Минский";
        street = "";
        house = 0;
        building = "";
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

    public void setCountry(String country) throws IllegalCountryNameException {
        if (country != null) {
            this.country = country;
        } else {
            throw new IllegalCountryNameException();
        }
    }

    public void setDistrict(String district) throws IllegalDistrictNameException {
        if (district != null) {
            this.district = district;
        } else {
            throw new IllegalDistrictNameException();
        }
    }

    public void setCity(String city) throws IllegalCityNameException {
        if (city != null) {
            this.city = city;
        } else {
            throw new IllegalCityNameException();
        }
    }

    public void setStreet(String street) throws IllegalStreetNameException {
        if (street != null) {
            this.street = street;
        } else {
            throw new IllegalStreetNameException();
        }
    }

    public void setHouse(int house) throws IllegalHouseNumberException {
        if (house >= 0) {
            this.house = house;
        } else {
            throw new IllegalHouseNumberException();
        }
    }

    public void setBuilding(String building) throws IllegalBuildingException {
        if (building != null) {
            this.building = building;
        } else {
            throw new IllegalBuildingException();
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
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        if (!super.equals(o)) return false;
        Address address = (Address) o;
        return house == address.house &&
                Objects.equals(country, address.country) &&
                Objects.equals(district, address.district) &&
                Objects.equals(city, address.city) &&
                Objects.equals(street, address.street) &&
                Objects.equals(building, address.building);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), country, district, city, street, house, building);
    }

    @Override
    public String toString() {
        return "Address{" +
                "country='" + country + '\'' +
                ", district='" + district + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", house=" + house +
                ", building='" + building + '\'' + '}';
    }
}
