package by.epam.javatraining.beseda.webproject.controller.command.implementation.gotopage;

import by.epam.javatraining.beseda.webproject.controller.command.ActionCommand;
import by.epam.javatraining.beseda.webproject.controller.command.util.srcontent.SessionRequestContent;

import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPPath.CUSTOMER_REGISTER_PAGE;

public class GoToCustomerRegister implements ActionCommand {

    @Override
    public String execute(SessionRequestContent content) {
        return CUSTOMER_REGISTER_PAGE;
    }
}
