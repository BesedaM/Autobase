package by.epam.javatraining.beseda.webproject.model.service.comparator;

import by.epam.javatraining.beseda.webproject.model.entity.user.Customer;

import java.util.Comparator;

public class CustomerCompanyNameComparator implements Comparator<Customer> {

    @Override
    public int compare(Customer o1, Customer o2) {
        return o2.getCompanyName().compareTo(o2.getCompanyName());
    }
}
