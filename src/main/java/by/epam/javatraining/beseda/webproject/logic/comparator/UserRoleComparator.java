package by.epam.javatraining.beseda.webproject.logic.comparator;

import by.epam.javatraining.beseda.webproject.entity.user.User;

import java.util.Comparator;

public class UserRoleComparator implements Comparator<User> {

    @Override
    public int compare(User o1, User o2) {
        return o1.getRole().compareTo(o2.getRole());
    }
}
