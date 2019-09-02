package by.epam.javatraining.beseda.webproject.service.dependenceservice;

import by.epam.javatraining.beseda.webproject.entity.route.Route;
import by.epam.javatraining.beseda.webproject.entity.route.Task;

public class TaskRouteService extends ManyToOneDependenceService<Task, Route>{

    TaskRouteService(){
        super();
        dependenceDAO = daoDependenceFactory.getTaskRouteDAO();
    }

}
