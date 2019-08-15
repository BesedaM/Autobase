package by.epam.javatraining.beseda.webproject.model.command.implementation.gotopage;

import by.epam.javatraining.beseda.webproject.model.command.ActionCommand;
import by.epam.javatraining.beseda.webproject.model.command.util.srcontent.SessionRequestContent;

import static by.epam.javatraining.beseda.webproject.model.command.util.constant.JSPPath.ADMIN_MAIN_PAGE;

public class GoToAdminMain implements ActionCommand {

    @Override
    public String execute(SessionRequestContent content) {
        return ADMIN_MAIN_PAGE;
    }
}
