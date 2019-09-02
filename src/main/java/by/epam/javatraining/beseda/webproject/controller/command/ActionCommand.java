package by.epam.javatraining.beseda.webproject.controller.command;

import by.epam.javatraining.beseda.webproject.controller.command.util.srcontent.SessionRequestContent;

public interface ActionCommand {

    String execute(SessionRequestContent content);
}
