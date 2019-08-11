package by.epam.javatraining.beseda.webproject.model.command;

import by.epam.javatraining.beseda.webproject.model.command.commands.CustomerRegisterCommand;
import by.epam.javatraining.beseda.webproject.model.command.commands.LoginCommand;
import by.epam.javatraining.beseda.webproject.model.command.commands.LogoutCommand;
import by.epam.javatraining.beseda.webproject.model.command.commands.gotopage.GoToCustomerRegister;
import by.epam.javatraining.beseda.webproject.model.command.commands.gotopage.GoToLogin;
import by.epam.javatraining.beseda.webproject.model.command.commands.post.*;

public enum ActionCommandEnum {
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    CUSTOMER_REGISTER(new CustomerRegisterCommand()),

    CHANGE_LOCALE(new LocaleChangeCommand()),

    LOGIN_PAGE(new GoToLogin()),
    CUSTOMER_REGISTER_PAGE(new GoToCustomerRegister()),

    CHANGE_ROUTE_STATUS(new RouteStatusChanger()),
    CHANGE_CAR_STATE(new CarStateChanger()),
    CHANGE_ROUTE_STATUS_CAR_STATE(new RouteStatusCarStateChanger());


    ActionCommand command;

    ActionCommandEnum(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCommand() {
        return command;
    }
}
