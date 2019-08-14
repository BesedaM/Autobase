package by.epam.javatraining.beseda.webproject.model.command.implementation.gotopage;

import by.epam.javatraining.beseda.webproject.model.command.ActionCommand;
import by.epam.javatraining.beseda.webproject.model.command.util.srcontent.SessionRequestContent;

public class GoToCustomerRegister implements ActionCommand {

    @Override
    public String execute(SessionRequestContent content) {
        return CUSTOMER_REGISTER_PAGE;
    }
}
