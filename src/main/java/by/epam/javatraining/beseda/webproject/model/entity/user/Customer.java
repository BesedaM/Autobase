package by.epam.javatraining.beseda.webproject.model.entity.user;

import by.epam.javatraining.beseda.webproject.model.entity.Request;
import by.epam.javatraining.beseda.webproject.model.exception.entityexception.user.IllegalCompanyNameException;
import by.epam.javatraining.beseda.webproject.model.exception.entityexception.user.IllegalCustomerEmailException;
import by.epam.javatraining.beseda.webproject.model.exception.entityexception.user.IllegalCustomerTypeException;

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
        requests = new ArrayList<>();
    }

    public Customer() {
        super();
    }

    public Customer(User userData, String name, String surname, String phone, String customerType, String email) {
        super(userData, name, surname, phone);
        this.customerType = customerType;
        this.email = email;
    }

    public Customer(User userData, String name, String surname, String phone, String customerType, String email, String companyName) {
        super(userData, name, surname, phone);
        this.customerType = customerType;
        this.email = email;
        this.companyName = companyName;
    }

    public void setCustomerType(String type) throws IllegalCustomerTypeException {
        if (type != null) {
            this.customerType = type;
        } else {
            throw new IllegalCustomerTypeException();
        }
    }

    public void setEmail(String email) throws IllegalCustomerEmailException {
        if (email != null) {
            this.email = email;
        } else {
            throw new IllegalCustomerEmailException();
        }
    }

    public void setCompanyName(String companyName) throws IllegalCompanyNameException {
        if (companyName != null) {
            this.companyName = companyName;
        } else {
            throw new IllegalCompanyNameException();
        }
    }

    public void setRequestList(List<Request> list) {
        if (list != null) {
            requests = list;
        }
    }

    public void addRequest(Request request) {
        if (request != null && !this.requests.contains(request)) {
            this.requests.add(request);
        }
    }

    public void removeRequest(Request request) {
        if (request != null) {
            this.requests.remove(request);
        }
    }

    public List<Request> getRequestsList() {
        return requests;
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
