package by.epam.javatraining.beseda.webproject.logic.comparator;

import by.epam.javatraining.beseda.webproject.entity.user.Person;

import java.util.Comparator;

public class PersonPhoneNumberComparator implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {
        return o1.compareTo(o2);
    }
}
