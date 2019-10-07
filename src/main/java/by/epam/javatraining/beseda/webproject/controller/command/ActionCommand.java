package by.epam.javatraining.beseda.webproject.controller.command;

import by.epam.javatraining.beseda.webproject.controller.command.util.srcontent.SessionRequestContent;

/**
 * Functional interface, being used in command pattern.
 * 
 * @author Maryia_Biaseda
 *
 */
public interface ActionCommand {

    String execute(SessionRequestContent content);
}