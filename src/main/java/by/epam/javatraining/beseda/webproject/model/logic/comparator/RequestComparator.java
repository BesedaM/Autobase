package by.epam.javatraining.beseda.webproject.model.logic.comparator;

import by.epam.javatraining.beseda.webproject.model.entity.Request;

import java.util.Comparator;

public class RequestComparator implements Comparator<Request> {

    @Override
    public int compare(Request o1, Request o2) {
        return o1.getDate().compareTo(o2.getDate());
    }
}
