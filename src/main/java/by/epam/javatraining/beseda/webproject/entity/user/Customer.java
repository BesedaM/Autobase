package by.epam.javatraining.beseda.webproject.entity.user;

import by.epam.javatraining.beseda.webproject.entity.exception.CustomerException;
import static by.epam.javatraining.beseda.webproject.entity.DefaultValue.HYPHEN;

import java.util.Objects;

public class Customer extends Person {

	private String customerType;
	private String email;
	private String companyName;

	{
		companyName = HYPHEN;
	}

	public Customer() {
		super();
	}

	public Customer(User userData, String name, String surname, String phone, String customerType, String email) {
		super(userData, name, surname, phone);
		this.customerType = customerType;
		this.email = email;
	}

	public Customer(User userData, String name, String surname, String phone, String customerType, String email,
			String companyName) {
		super(userData, name, surname, phone);
		this.customerType = customerType;
		this.email = email;
		this.companyName = companyName;
	}

	public void setCustomerType(String type) throws CustomerException {
		if (type != null) {
			this.customerType = type;
		} else {
			throw new CustomerException();
		}
	}

	public void setEmail(String email) throws CustomerException {
		if (email != null) {
			this.email = email;
		} else {
			throw new CustomerException();
		}
	}

	public void setCompanyName(String companyName) throws CustomerException {
		if (companyName != null) {
			this.companyName = companyName;
		} else {
			throw new CustomerException();
		}
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
		if (this == o)
			return true;
		if (!(o instanceof Customer))
			return false;
		if (!super.equals(o))
			return false;
		Customer customer = (Customer) o;
		return customerType.equals(customer.customerType) && Objects.equals(email, customer.email)
				&& Objects.equals(companyName, customer.companyName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), customerType, email, companyName);
	}

	@Override
	public String toString() {
		return "Customer{" + "customerType=" + customerType + ", companyName='" + companyName + '\'' + ", name='" + name
				+ '\'' + ", surname='" + surname + '\'' + ", phone='" + phone + '\'' + ", email='" + email + '\'' + '}';
	}
}