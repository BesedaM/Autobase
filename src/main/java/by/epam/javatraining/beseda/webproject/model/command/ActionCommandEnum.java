package by.epam.javatraining.beseda.webproject.model.command;

import by.epam.javatraining.beseda.webproject.model.command.commands.CustomerRegisterCommand;
import by.epam.javatraining.beseda.webproject.model.command.commands.LoginCommand;
import by.epam.javatraining.beseda.webproject.model.command.commands.LogoutCommand;
import by.epam.javatraining.beseda.webproject.model.command.commands.gotopage.GoToCustomerRegister;
import by.epam.javatraining.beseda.webproject.model.command.commands.gotopage.GoToLogin;

public enum ActionCommandEnum {
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    CUSTOMER_REGISTER(new CustomerRegisterCommand()),

    LOGIN_PAGE(new GoToLogin()),
    CUSTOMER_REGISTER_PAGE(new GoToCustomerRegister());

    ActionCommand command;

    ActionCommandEnum(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCommand() {
        return command;
    }
}
