package by.epam.javatraining.beseda.webproject.model.command.implementation.gotopage;

import by.epam.javatraining.beseda.webproject.model.command.ActionCommand;
import by.epam.javatraining.beseda.webproject.model.command.util.srcontent.SessionRequestContent;

public class GoToLogin implements ActionCommand {

    @Override
    public String execute(SessionRequestContent content) {
        return LOGIN_PAGE;
    }
}
