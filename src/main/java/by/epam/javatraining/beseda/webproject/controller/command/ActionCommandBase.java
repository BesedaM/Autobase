package by.epam.javatraining.beseda.webproject.controller.command;

import by.epam.javatraining.beseda.webproject.service.dependenceservice.ServiceDependenceFactory;
import by.epam.javatraining.beseda.webproject.service.entitybuilder.EntityBuilderFactory;
import by.epam.javatraining.beseda.webproject.service.entityservice.ServiceEntityFactory;
import org.apache.log4j.Logger;

import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;

@SuppressWarnings("unused")
public abstract class ActionCommandBase implements ActionCommand {

	private static final Logger log = Logger.getLogger(ERROR_LOGGER);
	private static final ServiceEntityFactory serviceEntityFactory = ServiceEntityFactory.getFactory();
	private static final ServiceDependenceFactory serviceDependenceFactory = ServiceDependenceFactory.getFactory();
	private static final EntityBuilderFactory entityBuilderFactory = EntityBuilderFactory.getFactory();

}