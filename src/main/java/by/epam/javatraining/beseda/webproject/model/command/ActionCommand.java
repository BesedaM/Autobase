package by.epam.javatraining.beseda.webproject.model.command;

import by.epam.javatraining.beseda.webproject.util.srcontent.SessionRequestContent;

public interface ActionCommand {

    String execute(SessionRequestContent content);
}
