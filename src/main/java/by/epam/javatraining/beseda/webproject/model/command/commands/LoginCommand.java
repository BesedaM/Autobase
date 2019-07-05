package by.epam.javatraining.beseda.webproject.model.command.commands;

import by.epam.javatraining.beseda.webproject.model.command.ActionCommand;
import by.epam.javatraining.beseda.webproject.model.entity.user.User;
import by.epam.javatraining.beseda.webproject.model.service.exception.ServiceTechnicalException;
import by.epam.javatraining.beseda.webproject.model.service.entity.UserService;
import by.epam.javatraining.beseda.webproject.model.command.util.srcontent.SessionRequestContent;
import by.epam.javatraining.beseda.webproject.model.service.factory.ServiceEntityFactory;
import org.apache.log4j.Logger;

import static by.epam.javatraining.beseda.webproject.util.LoggerName.AUTHORIZATION_LOGGER;
import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;
import static by.epam.javatraining.beseda.webproject.model.dao.util.database.DBEnumTable.*;
import static by.epam.javatraining.beseda.webproject.model.command.util.jsp.JSPAttribute.ERROR_MESSAGE;
import static by.epam.javatraining.beseda.webproject.model.command.util.jsp.JSPAttribute.ERROR_MESSAGE_TEXT;
import static by.epam.javatraining.beseda.webproject.model.command.util.jsp.JSPParameter.LOGIN;
import static by.epam.javatraining.beseda.webproject.model.command.util.jsp.JSPParameter.PASSWORD;
import static by.epam.javatraining.beseda.webproject.model.command.util.jsp.JSPPath.*;
import static by.epam.javatraining.beseda.webproject.model.command.util.jsp.JSPSessionAttribute.USER_DATA;

public class LoginCommand implements ActionCommand {

    private Logger log = Logger.getLogger(ERROR_LOGGER);
    private Logger authLog = Logger.getLogger(AUTHORIZATION_LOGGER);
    private UserService userService = ServiceEntityFactory.getFactory().getUserService();

    @Override
    public String execute(SessionRequestContent content) {
        String page = LOGIN_PAGE;
        String login = content.requestParameters().get(LOGIN)[0];
        String password = content.requestParameters().get(PASSWORD)[0];

        try {
            User user = userService.getUserByLoginAndPassword(login, password);
            if (user != null) {
                String userRole = user.getRole();
                page = definePageByUserRole(userRole);
                content.getSession().setAttribute(USER_DATA, user);
                content.getSession().setAttribute(USER_ROLE, userRole);
                authLog.trace(login);
            } else {
                content.requestAttributes().put(ERROR_MESSAGE, ERROR_MESSAGE_TEXT);
            }
        } catch (ServiceTechnicalException e) {
            log.error(e);
        }
        return page;
    }

    private String definePageByUserRole(String userRole) {
        String page = null;
        if (userRole != null) {
            switch (userRole) {
                case USER_ADMIN:
                    page = ADMIN_MAIN_PAGE;
                    break;
                case USER_CUSTOMER:
                    page = CUSTOMER_MAIN_PAGE;
                    break;
                case USER_DRIVER:
                    page = DRIVER_MAIN_PAGE;
                    break;
            }
        }
        return page;
    }
}
