package by.epam.javatraining.beseda.webproject.model.command.implementation.post;

import by.epam.javatraining.beseda.webproject.model.command.ActionCommand;
import by.epam.javatraining.beseda.webproject.model.command.implementation.parts.CustomerProcessor;
import by.epam.javatraining.beseda.webproject.model.command.implementation.parts.DriverProcessor;
import by.epam.javatraining.beseda.webproject.model.command.util.constant.JSPPath;
import by.epam.javatraining.beseda.webproject.model.command.util.srcontent.SessionRequestContent;
import by.epam.javatraining.beseda.webproject.model.entity.user.User;
import by.epam.javatraining.beseda.webproject.model.service.entityservice.CustomerService;
import by.epam.javatraining.beseda.webproject.model.service.entityservice.DriverService;
import by.epam.javatraining.beseda.webproject.model.service.entityservice.UserService;
import by.epam.javatraining.beseda.webproject.model.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.model.service.exception.ServiceLogicException;
import by.epam.javatraining.beseda.webproject.model.service.factory.ServiceEntityFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;

import static by.epam.javatraining.beseda.webproject.model.command.util.constant.JSPAttribute.ERROR_MESSAGE;
import static by.epam.javatraining.beseda.webproject.model.command.util.constant.JSPAttribute.STATUS_TRUE;
import static by.epam.javatraining.beseda.webproject.model.command.util.constant.JSPParameter.LOGIN;
import static by.epam.javatraining.beseda.webproject.model.command.util.constant.JSPParameter.PASSWORD;
import static by.epam.javatraining.beseda.webproject.model.command.util.constant.JSPSessionAttribute.USER_DATA;
import static by.epam.javatraining.beseda.webproject.model.dao.util.database.DBEnumTable.*;
import static by.epam.javatraining.beseda.webproject.util.LoggerName.AUTHORIZATION_LOGGER;
import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;


public class LoginCommand implements ActionCommand {

    private static Logger log;
    private static Logger authLog;
    private ServiceEntityFactory serviceEntityFactory;
    private DriverService driverService;
    private CustomerService customerService;

    {
        log = Logger.getLogger(ERROR_LOGGER);
        authLog = Logger.getLogger(AUTHORIZATION_LOGGER);
        serviceEntityFactory = ServiceEntityFactory.getFactory();
        driverService = serviceEntityFactory.getDriverService();
        customerService = serviceEntityFactory.getCustomerService();
    }


    @Override
    public String execute(SessionRequestContent content) {
        HttpSession httpSession = content.getSession();

        String page = JSPPath.LOGIN_PAGE.getPath();
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
            } else {
                content.requestAttributes().put(ERROR_MESSAGE, STATUS_TRUE);
            }
        } catch (ServiceLayerException e) {
            log.error(e);
        }
        return page;
    }

    private String definePageByUserRole(String userRole) {
        String page = JSPPath.LOGIN_PAGE.getPath();
        if (userRole != null) {
            switch (userRole) {
                case USER_ADMIN:
                    page = JSPPath.ADMIN_MAIN_PAGE.getPath();
                    break;
                case USER_CUSTOMER:
                    page = JSPPath.CUSTOMER_MAIN_PAGE.getPath();
                    break;
                case USER_DRIVER:
                    page = JSPPath.DRIVER_MAIN_PAGE.getPath();
                    break;
            }
        }
        return page;
    }

    private void bindUserData(User user, HttpSession session) throws ServiceLayerException {
        String userRole = user.getRole();
        int userId = user.getId();
        User fullUserData = null;
        if (userRole != null) {
            switch (userRole) {
                case USER_ADMIN:
//                    processAdminData(user, session),
                    break;
                case USER_CUSTOMER:
                    fullUserData = customerService.getEntityById(userId);
                    session.setAttribute(USER_DATA, fullUserData);
                    CustomerProcessor.processCustomerData(session);
                    break;
                case USER_DRIVER:
                    fullUserData = driverService.getEntityById(userId);
                    session.setAttribute(USER_DATA, fullUserData);
                    DriverProcessor.processDriverData(session);
                    break;
                default:
                    throw new ServiceLogicException("Unknown user type");

            }
            authLog.trace(fullUserData);
        }
    }

}
