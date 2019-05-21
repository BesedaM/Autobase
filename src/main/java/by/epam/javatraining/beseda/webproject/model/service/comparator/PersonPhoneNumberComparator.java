package by.epam.javatraining.beseda.webproject.model.service.comparator;

import by.epam.javatraining.beseda.webproject.model.entity.user.Person;

import java.util.Comparator;

public class PersonPhoneNumberComparator implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {
        return o1.compareTo(o2);
    }
}
