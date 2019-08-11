package by.epam.javatraining.beseda.webproject.model.command.commands.gotopage;

import by.epam.javatraining.beseda.webproject.model.command.ActionCommand;
import by.epam.javatraining.beseda.webproject.model.command.util.srcontent.SessionRequestContent;

import static by.epam.javatraining.beseda.webproject.model.command.util.jsp.JSPPath.CUSTOMER_REGISTER_PAGE;

public class GoToCustomerRegister implements ActionCommand {

    @Override
    public String execute(SessionRequestContent content) {
        return CUSTOMER_REGISTER_PAGE;
    }
}
