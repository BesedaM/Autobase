package by.epam.javatraining.beseda.webproject.model.command.implementation.gotopage;

import by.epam.javatraining.beseda.webproject.model.command.ActionCommand;
import by.epam.javatraining.beseda.webproject.model.command.util.srcontent.SessionRequestContent;

import static by.epam.javatraining.beseda.webproject.model.command.util.constant.JSPPath.CUSTOMER_PERSONAL_DATA_PAGE;

public class GoToPersonalCustomerData implements ActionCommand {

    @Override
    public String execute(SessionRequestContent content) {
        return CUSTOMER_PERSONAL_DATA_PAGE;
    }
}
