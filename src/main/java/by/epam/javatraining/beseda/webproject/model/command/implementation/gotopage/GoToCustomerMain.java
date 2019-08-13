package by.epam.javatraining.beseda.webproject.model.command.implementation.gotopage;

import by.epam.javatraining.beseda.webproject.model.command.ActionCommand;
import by.epam.javatraining.beseda.webproject.model.command.util.srcontent.SessionRequestContent;

import static by.epam.javatraining.beseda.webproject.model.command.util.constant.JSPPath.CUSTOMER_MAIN_PAGE;

public class GoToCustomerMain implements ActionCommand {

    @Override
    public String execute(SessionRequestContent content) {
        return CUSTOMER_MAIN_PAGE;
    }
}
