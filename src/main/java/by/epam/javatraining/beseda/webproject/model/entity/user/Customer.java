package by.epam.javatraining.beseda.webproject.model.entity.user;

import by.epam.javatraining.beseda.webproject.model.entity.Request;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Customer extends Person {

    private String customerType;
    private String email;
    private String companyName;
    private List<Request> requests;

    {
        companyName = "-";
        requests=new ArrayList<>();
    }

    public Customer() {
        super();
    }

    public Customer(User userData, String name, String surname, String phone, String customerType) {
        super(userData, name, surname, phone);
        this.customerType = customerType;
    }

    public Customer(User userData, String name, String surname, String phone, String customerType, String email, String companyName) {
        super(userData, name, surname, phone);
        this.customerType = customerType;
        this.email = email;
        this.companyName = companyName;
    }

    public void setCustomerType(String type) {
        this.customerType = type;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCustomerType() {
        return customerType;
    }

    public String getEmail() {
        return email;
    }

    public String getCompanyName() {
        return companyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        if (!super.equals(o)) return false;
        Customer customer = (Customer) o;
        return customerType == customer.customerType &&
                Objects.equals(email, customer.email) &&
                Objects.equals(companyName, customer.companyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), customerType, email, companyName);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerType=" + customerType +
                ", companyName='" + companyName + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
