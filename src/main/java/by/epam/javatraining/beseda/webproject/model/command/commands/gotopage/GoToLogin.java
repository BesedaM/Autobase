package by.epam.javatraining.beseda.webproject.model.command.commands.gotopage;

import by.epam.javatraining.beseda.webproject.model.command.ActionCommand;
import by.epam.javatraining.beseda.webproject.model.command.util.srcontent.SessionRequestContent;

import static by.epam.javatraining.beseda.webproject.model.command.util.jsp.JSPPath.LOGIN_PAGE;

public class GoToLogin implements ActionCommand {

    @Override
    public String execute(SessionRequestContent content) {
        content.getSession().invalidate();
        return LOGIN_PAGE;
    }
}
