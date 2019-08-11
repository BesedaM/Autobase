package by.epam.javatraining.beseda.webproject.model.service.dependence;

import by.epam.javatraining.beseda.webproject.model.entity.route.Address;
import by.epam.javatraining.beseda.webproject.model.entity.route.Task;

public class TaskAddressService extends ToOneDependenceService<Task, Address>{

    public TaskAddressService(){
        super();
        dependenceDAO = daoDependenceFactory.getTaskAddressDAO();
    }

}
