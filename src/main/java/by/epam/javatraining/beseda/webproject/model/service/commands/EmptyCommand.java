package by.epam.javatraining.beseda.webproject.model.service.commands;

import by.epam.javatraining.beseda.webproject.util.srcontent.SessionRequestContent;

import static by.epam.javatraining.beseda.webproject.util.jspproperties.JSPPath.LOGIN_PAGE;

public class EmptyCommand implements ActionCommand {

    @Override
    public String execute(SessionRequestContent content) {
        return LOGIN_PAGE;
    }
}
