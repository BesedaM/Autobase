package by.epam.javatraining.beseda.webproject.model.command;

import by.epam.javatraining.beseda.webproject.model.command.implementation.get.LocaleChangerCommand;
import by.epam.javatraining.beseda.webproject.model.command.implementation.post.CustomerRegisterCommand;
import by.epam.javatraining.beseda.webproject.model.command.implementation.post.LoginCommand;
import by.epam.javatraining.beseda.webproject.model.command.implementation.get.LogoutCommand;
import by.epam.javatraining.beseda.webproject.model.command.implementation.get.UpdateCustomerRequestsCommand;
import by.epam.javatraining.beseda.webproject.model.command.implementation.gotopage.GoToCustomerMain;
import by.epam.javatraining.beseda.webproject.model.command.implementation.gotopage.GoToCustomerRegister;
import by.epam.javatraining.beseda.webproject.model.command.implementation.gotopage.GoToLogin;
import by.epam.javatraining.beseda.webproject.model.command.implementation.gotopage.GoToPersonalCustomerData;
import by.epam.javatraining.beseda.webproject.model.command.implementation.post.*;

public enum ActionCommandEnum {
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    CUSTOMER_REGISTER(new CustomerRegisterCommand()),

    CHANGE_LOCALE(new LocaleChangerCommand()),

    LOGIN_PAGE(new GoToLogin()),
    CUSTOMER_REGISTER_PAGE(new GoToCustomerRegister()),
    PERSONAL_DATA_PAGE(new GoToPersonalCustomerData()),
    CUSTOMER_MAIN_PAGE(new GoToCustomerMain()),

    CHANGE_ROUTE_STATUS(new RouteStatusChanger()),
    CHANGE_CAR_STATE(new CarStateChanger()),
    CHANGE_ROUTE_STATUS_CAR_STATE(new RouteStatusCarStateChanger()),
    CHANGE_CUSTOMER_PASSWORD(new CustomerPasswordChanger()),
    CHANGE_CUSTOMER_DATA(new CustomerDataChanger()),
    ADD_REQUEST(new AddRequestCommand()),
    UPDATE_CUSTOMER_REQUEST_LIST(new UpdateCustomerRequestsCommand()),

    DELETE_REQUEST(new RequestDeleteCommand());


    ActionCommand command;

    ActionCommandEnum(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCommand() {
        return command;
    }
}
