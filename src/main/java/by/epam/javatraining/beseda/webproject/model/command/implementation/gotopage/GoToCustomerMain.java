package by.epam.javatraining.beseda.webproject.model.command.implementation.gotopage;

import by.epam.javatraining.beseda.webproject.model.command.ActionCommand;
import by.epam.javatraining.beseda.webproject.model.command.util.constant.JSPPath;
import by.epam.javatraining.beseda.webproject.model.command.util.srcontent.SessionRequestContent;

public class GoToCustomerMain implements ActionCommand {

    @Override
    public String execute(SessionRequestContent content) {
        return JSPPath.CUSTOMER_MAIN_PAGE.getPath();
    }
}
