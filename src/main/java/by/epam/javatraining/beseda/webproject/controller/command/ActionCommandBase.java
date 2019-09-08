package by.epam.javatraining.beseda.webproject.controller.command;

import by.epam.javatraining.beseda.webproject.service.dependenceservice.ServiceDependenceFactory;
import by.epam.javatraining.beseda.webproject.service.entitybuilder.EntityBuilderFactory;
import by.epam.javatraining.beseda.webproject.service.entityservice.ServiceEntityFactory;
import org.apache.log4j.Logger;

import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;

public abstract class ActionCommandBase implements ActionCommand{

    private static Logger log = Logger.getLogger(ERROR_LOGGER);
    private static ServiceEntityFactory serviceEntityFactory = ServiceEntityFactory.getFactory();
    private static ServiceDependenceFactory serviceDependenceFactory=ServiceDependenceFactory.getFactory();
    private static EntityBuilderFactory entityBuilderFactory=EntityBuilderFactory.getFactory();

}
