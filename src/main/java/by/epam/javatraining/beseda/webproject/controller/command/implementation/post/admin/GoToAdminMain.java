package by.epam.javatraining.beseda.webproject.controller.command.implementation.post.admin;

import by.epam.javatraining.beseda.webproject.controller.command.ActionCommand;
import by.epam.javatraining.beseda.webproject.controller.command.util.srcontent.SessionRequestContent;

import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPPath.ADMIN_MAIN_PAGE;

public class GoToAdminMain implements ActionCommand {

    @Override
    public String execute(SessionRequestContent content) {
        return ADMIN_MAIN_PAGE;
    }

}
