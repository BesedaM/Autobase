package by.epam.javatraining.beseda.webproject.model.service.actioncommand;

import by.epam.javatraining.beseda.webproject.model.service.commands.ActionCommand;
import by.epam.javatraining.beseda.webproject.model.service.commands.EmptyCommand;
import by.epam.javatraining.beseda.webproject.model.service.commands.LoginCommand;
import by.epam.javatraining.beseda.webproject.model.service.commands.LogoutCommand;

public enum ActionCommandEnum {
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    EMPTY_COMMAND(new EmptyCommand());

    ActionCommand command;

    ActionCommandEnum(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCommand() {
        return command;
    }
}
