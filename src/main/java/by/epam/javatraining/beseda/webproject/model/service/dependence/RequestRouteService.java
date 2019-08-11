package by.epam.javatraining.beseda.webproject.model.service.dependence;

import by.epam.javatraining.beseda.webproject.model.entity.Request;
import by.epam.javatraining.beseda.webproject.model.entity.route.Route;

public class RequestRouteService extends OneToOneDependenceService<Request, Route>{

    public RequestRouteService(){
        super();
        dependenceDAO = daoDependenceFactory.getRequestRouteDAO();
    }

}
