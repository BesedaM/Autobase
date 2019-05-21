package by.epam.javatraining.beseda.webproject.model.service.comparator;

import by.epam.javatraining.beseda.webproject.model.entity.user.Customer;

import java.util.Comparator;

public class CustomerEmailComparator implements Comparator<Customer> {

    @Override
    public int compare(Customer o1, Customer o2) {
        return o1.getEmail().compareTo(o2.getEmail());
    }
}
