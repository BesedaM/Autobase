package by.epam.javatraining.beseda.webproject.model.command.implementation.gotopage;

import by.epam.javatraining.beseda.webproject.model.command.ActionCommand;
import by.epam.javatraining.beseda.webproject.model.command.util.srcontent.SessionRequestContent;

import static by.epam.javatraining.beseda.webproject.model.command.util.constant.JSPPath.LOGIN_PAGE;

public class GoToLogin implements ActionCommand {

    @Override
    public String execute(SessionRequestContent content) {
        return LOGIN_PAGE;
    }
}
