package by.epam.javatraining.beseda.webproject.model.command;

import by.epam.javatraining.beseda.webproject.model.command.implementation.get.LocaleChangerCommand;
import by.epam.javatraining.beseda.webproject.model.command.implementation.get.LogoutCommand;
import by.epam.javatraining.beseda.webproject.model.command.implementation.get.UpdateCustomerRequestsCommand;
import by.epam.javatraining.beseda.webproject.model.command.implementation.gotopage.GoToCustomerMain;
import by.epam.javatraining.beseda.webproject.model.command.implementation.gotopage.GoToCustomerRegister;
import by.epam.javatraining.beseda.webproject.model.command.implementation.gotopage.GoToLogin;
import by.epam.javatraining.beseda.webproject.model.command.implementation.gotopage.GoToPersonalCustomerData;
import by.epam.javatraining.beseda.webproject.model.command.implementation.post.*;
import by.epam.javatraining.beseda.webproject.model.command.util.srcontent.SessionRequestContent;
import by.epam.javatraining.beseda.webproject.model.service.exception.ServiceLogicException;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static by.epam.javatraining.beseda.webproject.model.command.util.constant.JSPParameter.COMMAND;
import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;

public class CommandManager {

    private static class SingletonHolder {
        public static final CommandManager instance = new CommandManager();
    }

    private static Map<ActionCommandName, ActionCommand> manager;
    private static Logger log;

    static {
        manager = new HashMap<>();
        log = Logger.getLogger(ERROR_LOGGER);
        manager.put(ActionCommandName.LOGIN, new LoginCommand());
        manager.put(ActionCommandName.LOGOUT, new LogoutCommand());
        manager.put(ActionCommandName.CUSTOMER_REGISTER, new CustomerRegisterCommand());
        manager.put(ActionCommandName.CHANGE_LOCALE, new LocaleChangerCommand());
        manager.put(ActionCommandName.LOGIN_PAGE, new GoToLogin());
        manager.put(ActionCommandName.CUSTOMER_REGISTER_PAGE, new GoToCustomerRegister());
        manager.put(ActionCommandName.PERSONAL_CUSTOMER_DATA_PAGE, new GoToPersonalCustomerData());
        manager.put(ActionCommandName.CUSTOMER_MAIN_PAGE, new GoToCustomerMain());
        manager.put(ActionCommandName.CHANGE_ROUTE_STATUS, new RouteStatusChanger());
        manager.put(ActionCommandName.CHANGE_CAR_STATE, new CarStateChanger());
        manager.put(ActionCommandName.CHANGE_ROUTE_STATUS_CAR_STATE, new RouteStatusCarStateChanger());
        manager.put(ActionCommandName.CHANGE_CUSTOMER_PASSWORD, new CustomerPasswordChanger());
        manager.put(ActionCommandName.CHANGE_CUSTOMER_DATA, new CustomerDataChanger());
        manager.put(ActionCommandName.ADD_REQUEST, new AddRequestCommand());
        manager.put(ActionCommandName.UPDATE_CUSTOMER_REQUEST_LIST, new UpdateCustomerRequestsCommand());
        manager.put(ActionCommandName.DELETE_REQUEST, new RequestDeleteCommand());
    }

    public static CommandManager getCommandManager() {
        return SingletonHolder.instance;
    }

    public ActionCommand getCommand(SessionRequestContent content) {
        ActionCommand actionCommand = manager.get(ActionCommandName.LOGIN);
        String page = content.requestParameters().get(COMMAND)[0];
        if (page != null) {
            try {
                actionCommand = manager.get(ActionCommandName.valueOf(page.toUpperCase()));
            } catch (IllegalArgumentException e) {
                log.error(new ServiceLogicException(new EnumConstantNotPresentException(ActionCommandEnum.class, page)));
            }
        }
        return actionCommand;
    }

}
