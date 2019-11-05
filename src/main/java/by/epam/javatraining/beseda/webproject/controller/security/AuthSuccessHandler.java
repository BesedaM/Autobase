package by.epam.javatraining.beseda.webproject.controller.security;

import by.epam.javatraining.beseda.webproject.entity.user.User;
import by.epam.javatraining.beseda.webproject.service.entityservice.CustomerService;
import by.epam.javatraining.beseda.webproject.service.entityservice.DriverService;
import by.epam.javatraining.beseda.webproject.service.entityservice.UserService;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLogicException;
import by.epam.javatraining.beseda.webproject.service.processors.CarsDataProcessor;
import by.epam.javatraining.beseda.webproject.service.processors.CustomerProcessor;
import by.epam.javatraining.beseda.webproject.service.processors.DriverProcessor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPPath.*;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPSessionAttribute.USER_DATA;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.*;
import static by.epam.javatraining.beseda.webproject.util.LoggerName.AUTHORIZATION_LOGGER;
import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;

@Component
public class AuthSuccessHandler implements AuthenticationSuccessHandler {

    private Logger log = Logger.getLogger(ERROR_LOGGER);

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private CarsDataProcessor carsDataProcessor;

    @Autowired
    private CustomerProcessor customerProcessor;

    @Autowired
    private DriverProcessor driverProcessor;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication)
            throws IOException {
        handle(request, response, authentication);
    }

    protected void handle(HttpServletRequest request,
                          HttpServletResponse response, Authentication authentication)
            throws IOException {
        String targetUrl = determineTargetUrl(authentication);
        String login = request.getParameter("username");
        HttpSession httpSession = request.getSession();

        try {
            bindUserData(login, httpSession);
        } catch (ServiceLayerException e) {
            log.error(e);
        }
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    private String determineTargetUrl(Authentication authentication) {
        String page = null;
        Collection<? extends GrantedAuthority> authorities
                = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            switch (grantedAuthority.getAuthority()) {
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

    private void bindUserData(String username, HttpSession session) throws ServiceLayerException {
        User user = userService.getUserByLogin(username);
        String userRole = user.getRole();
        int userId = user.getId();
        User fullUserData = null;
        if (userRole != null) {
            switch (userRole) {
                case USER_ADMIN:
                    carsDataProcessor.processCarsData(session);
                    fullUserData = user;
                    break;
                case USER_CUSTOMER:
                    fullUserData = customerService.getEntityById(userId);
                    session.setAttribute(USER_DATA, fullUserData);
                    customerProcessor.processCustomerData(session);
                    break;
                case USER_DRIVER:
                    fullUserData = driverService.getEntityById(userId);
                    session.setAttribute(USER_DATA, fullUserData);
                    driverProcessor.processDriverData(session);
                    break;
                default:
                    throw new ServiceLogicException("Unknown user type");
            }
        }
    }


    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }
}
