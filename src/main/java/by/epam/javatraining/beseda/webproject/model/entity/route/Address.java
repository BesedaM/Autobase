package by.epam.javatraining.beseda.webproject.model.entity.route;

import by.epam.javatraining.beseda.webproject.model.entity.BaseEntity;

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
        this.street = street;
        this.house = house;
        this.building = building;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setHouse(int house) {
        this.house = house;
    }

    public void setBuilding(String building) {
        this.building = building;
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
