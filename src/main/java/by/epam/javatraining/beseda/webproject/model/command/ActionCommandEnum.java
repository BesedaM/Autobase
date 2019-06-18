package by.epam.javatraining.beseda.webproject.model.command;

import by.epam.javatraining.beseda.webproject.model.command.commands.CustomerRegisterCommand;
import by.epam.javatraining.beseda.webproject.model.command.commands.LoginCommand;
import by.epam.javatraining.beseda.webproject.model.command.commands.LogoutCommand;

public enum ActionCommandEnum {
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    CUSTOMER_REGISTER(new CustomerRegisterCommand()),
    EMPTY_COMMAND(new LogoutCommand());

    ActionCommand command;

    ActionCommandEnum(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCommand() {
        return command;
    }
}
