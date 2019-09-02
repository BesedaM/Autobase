package by.epam.javatraining.beseda.webproject.logic.comparator;

import by.epam.javatraining.beseda.webproject.entity.user.Customer;

import java.util.Comparator;

public class CustomerCompanyNameComparator implements Comparator<Customer> {

    @Override
    public int compare(Customer o1, Customer o2) {
        return o2.getCompanyName().compareTo(o2.getCompanyName());
    }
}
