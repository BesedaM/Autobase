package by.epam.javatraining.beseda.webproject.model.command.commands;

import by.epam.javatraining.beseda.webproject.model.command.ActionCommand;
import by.epam.javatraining.beseda.webproject.model.command.commands.parts.DriverProcessor;
import by.epam.javatraining.beseda.webproject.model.command.util.srcontent.SessionRequestContent;
import by.epam.javatraining.beseda.webproject.model.entity.user.User;
import by.epam.javatraining.beseda.webproject.model.service.entity.UserService;
import by.epam.javatraining.beseda.webproject.model.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.model.service.exception.ServiceLogicException;
import by.epam.javatraining.beseda.webproject.model.service.factory.ServiceEntityFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;

import static by.epam.javatraining.beseda.webproject.model.command.util.jsp.JSPAttribute.ERROR_MESSAGE;
import static by.epam.javatraining.beseda.webproject.model.command.util.jsp.JSPAttribute.ERROR_MESSAGE_TEXT;
import static by.epam.javatraining.beseda.webproject.model.command.util.jsp.JSPParameter.LOGIN;
import static by.epam.javatraining.beseda.webproject.model.command.util.jsp.JSPParameter.PASSWORD;
import static by.epam.javatraining.beseda.webproject.model.command.util.jsp.JSPPath.*;
import static by.epam.javatraining.beseda.webproject.model.dao.util.database.DBEnumTable.*;
import static by.epam.javatraining.beseda.webproject.util.LoggerName.AUTHORIZATION_LOGGER;
import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;


public class LoginCommand implements ActionCommand {

    private Logger log;
    private Logger authLog;
    private ServiceEntityFactory serviceEntityFactory;

    {
        log = Logger.getLogger(ERROR_LOGGER);
        authLog = Logger.getLogger(AUTHORIZATION_LOGGER);
        serviceEntityFactory = ServiceEntityFactory.getFactory();
    }


    @Override
    public String execute(SessionRequestContent content) {
        HttpSession httpSession = content.getSession();

        String page = LOGIN_PAGE;
        String login = content.requestParameters().get(LOGIN)[0];
        String password = content.requestParameters().get(PASSWORD)[0];

        try {
            UserService userService = serviceEntityFactory.getUserService();
            User user = userService.getUserByLoginAndPassword(login, password);
            if (user != null) {
                String userRole = user.getRole();
                httpSession.setAttribute(USER_ROLE, userRole);

                bindUserData(user, httpSession);

                page = definePageByUserRole(userRole);

                authLog.trace(login);
            } else {
                content.requestAttributes().put(ERROR_MESSAGE, ERROR_MESSAGE_TEXT);
            }
        } catch (ServiceLayerException e) {
            log.error(e);
        }
        return page;
    }

    private String definePageByUserRole(String userRole) {
        String page = LOGIN_PAGE;
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

    private void bindUserData(User user, HttpSession session) throws ServiceLayerException {
        String userRole = user.getRole();
        if (userRole != null) {
            switch (userRole) {
                case USER_ADMIN:
//                    processAdminData(user, session),
                    break;
                case USER_CUSTOMER:
//                    processCustomerData(user, session);
                    break;
                case USER_DRIVER:
                    DriverProcessor.processDriverData(user, session);
                    break;
                default:
                    throw new ServiceLogicException("Unknown user");

            }
        }
    }

}
