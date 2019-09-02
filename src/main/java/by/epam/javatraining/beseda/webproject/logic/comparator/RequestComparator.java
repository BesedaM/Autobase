package by.epam.javatraining.beseda.webproject.logic.comparator;

import by.epam.javatraining.beseda.webproject.entity.Request;

import java.util.Comparator;

public class RequestComparator implements Comparator<Request> {

    @Override
    public int compare(Request o1, Request o2) {
        return o1.getCreationTime().compareTo(o2.getCreationTime());
    }
}
