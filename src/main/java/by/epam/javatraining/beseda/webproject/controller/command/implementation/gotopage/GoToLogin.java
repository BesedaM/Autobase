package by.epam.javatraining.beseda.webproject.controller.command.implementation.gotopage;

import by.epam.javatraining.beseda.webproject.controller.command.ActionCommand;
import by.epam.javatraining.beseda.webproject.controller.command.util.srcontent.SessionRequestContent;

import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPPath.LOGIN_PAGE;

public class GoToLogin implements ActionCommand {

    @Override
    public String execute(SessionRequestContent content) {
        return LOGIN_PAGE;
    }
}
