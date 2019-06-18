package by.epam.javatraining.beseda.webproject.model.command.commands;

import by.epam.javatraining.beseda.webproject.model.command.ActionCommand;
import by.epam.javatraining.beseda.webproject.model.entity.user.User;
import by.epam.javatraining.beseda.webproject.model.exception.serviceexception.ServiceTechnicalException;
import by.epam.javatraining.beseda.webproject.model.service.entity.UserService;
import by.epam.javatraining.beseda.webproject.util.srcontent.SessionRequestContent;
import org.apache.log4j.Logger;

import static by.epam.javatraining.beseda.webproject.util.LoggerName.AUTHORIZATION_LOGGER;
import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;
import static by.epam.javatraining.beseda.webproject.util.database.DBEnumTable.*;
import static by.epam.javatraining.beseda.webproject.util.jspproperties.JSPAttribute.ERROR_MESSAGE;
import static by.epam.javatraining.beseda.webproject.util.jspproperties.JSPAttribute.ERROR_MESSAGE_TEXT;
import static by.epam.javatraining.beseda.webproject.util.jspproperties.JSPParameter.LOGIN;
import static by.epam.javatraining.beseda.webproject.util.jspproperties.JSPParameter.PASSWORD;
import static by.epam.javatraining.beseda.webproject.util.jspproperties.JSPPath.*;
import static by.epam.javatraining.beseda.webproject.util.jspproperties.JSPSessionAttribute.IS_VALID_USER;
import static by.epam.javatraining.beseda.webproject.util.jspproperties.JSPSessionAttribute.USER_DATA;

public class LoginCommand implements ActionCommand {

    private Logger log = Logger.getLogger(ERROR_LOGGER);
    private Logger authLog = Logger.getLogger(AUTHORIZATION_LOGGER);

    @Override
    public String execute(SessionRequestContent content) {
        String page = LOGIN_PAGE;
        UserService us = UserService.getService();
        String login = content.requestParameters().get(LOGIN)[0];
        String password = content.requestParameters().get(PASSWORD)[0];
        try {
            User user = us.getUserByLoginAndPassword(login, password);
            if (user != null) {
                content.sessionAttributes().put(IS_VALID_USER, "true");
                content.sessionAttributes().put(USER_DATA, user);
                String userRole = user.getRole();
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
                content.sessionAttributes().put(USER_ROLE, userRole);
                authLog.trace(login);
            } else {
                content.requestAttributes().put(ERROR_MESSAGE, ERROR_MESSAGE_TEXT);
            }
        } catch (ServiceTechnicalException e) {
            log.error(e);
        }
        return page;
    }
}
