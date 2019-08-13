package by.epam.javatraining.beseda.webproject.model.service.dependenceservice;

import by.epam.javatraining.beseda.webproject.model.entity.route.Route;
import by.epam.javatraining.beseda.webproject.model.entity.route.Task;

public class TaskRouteService extends ManyToOneDependenceService<Task, Route>{

    public TaskRouteService(){
        super();
        dependenceDAO = daoDependenceFactory.getTaskRouteDAO();
    }

}
