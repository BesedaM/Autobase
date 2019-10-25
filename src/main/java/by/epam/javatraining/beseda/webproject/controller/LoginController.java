package by.epam.javatraining.beseda.webproject.controller;

import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPAttribute.ERROR_MESSAGE;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPAttribute.STATUS_TRUE;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPPath.ADMIN_MAIN_PAGE;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPPath.CUSTOMER_MAIN_PAGE;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPPath.DRIVER_MAIN_PAGE;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPPath.LOGIN_PAGE;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPSessionAttribute.USER_DATA;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.USER_ADMIN;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.USER_CUSTOMER;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.USER_DRIVER;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.USER_ROLE;
import static by.epam.javatraining.beseda.webproject.util.LoggerName.AUTHORIZATION_LOGGER;
import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import by.epam.javatraining.beseda.webproject.entity.user.User;
import by.epam.javatraining.beseda.webproject.service.entityservice.CustomerService;
import by.epam.javatraining.beseda.webproject.service.entityservice.DriverService;
import by.epam.javatraining.beseda.webproject.service.entityservice.UserService;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLogicException;
import by.epam.javatraining.beseda.webproject.service.processors.CarsDataProcessor;
import by.epam.javatraining.beseda.webproject.service.processors.CustomerProcessor;
import by.epam.javatraining.beseda.webproject.service.processors.DriverProcessor;

@Controller
public class LoginController {

    private static Logger log;
    private static Logger authLog;

    @Autowired
    private DriverService driverService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private UserService userService;

    @Autowired
    private CarsDataProcessor carsDataProcessor;

    @Autowired
    private CustomerProcessor customerProcessor;

    @Autowired
    private DriverProcessor driverProcessor;

    {
        log = Logger.getLogger(ERROR_LOGGER);
        authLog = Logger.getLogger(AUTHORIZATION_LOGGER);
    }


    @RequestMapping(value = {"/", "/login", "/logout"})
    public String gotoLoginPage(HttpServletRequest request,
                                @RequestParam(value = "error", required = false) String error) {
        if (error != null) {
            request.setAttribute(ERROR_MESSAGE, STATUS_TRUE);
        }
        return LOGIN_PAGE;
    }

}
