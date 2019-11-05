package by.epam.javatraining.beseda.webproject.controller;

import by.epam.javatraining.beseda.webproject.controller.util.Decoder;
import by.epam.javatraining.beseda.webproject.entity.user.Customer;
import by.epam.javatraining.beseda.webproject.entity.user.User;
import by.epam.javatraining.beseda.webproject.logic.RegisterLogic;
import by.epam.javatraining.beseda.webproject.service.entityservice.CustomerService;
import by.epam.javatraining.beseda.webproject.service.entityservice.UserService;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

import static by.epam.javatraining.beseda.webproject.controller.util.constant.CommandConstant.MINIMUM_COMPANY_NAME_LENGTH;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPAttribute.*;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPParameter.*;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPPath.CUSTOMER_MAIN_PAGE;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPPath.CUSTOMER_REGISTER_PAGE;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPSessionAttribute.USER_DATA;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.*;
import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;

@Controller
@ResponseBody
@SessionAttributes("user")
@RequestMapping(value = {"/register"})
public class RegisterController {

    private static Logger log = Logger.getLogger(ERROR_LOGGER);

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = {"/customer"})
    public ModelAndView gotoCustomerRegister() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName(CUSTOMER_REGISTER_PAGE);
        return mav;
    }

    @PostMapping(value = "/customer/add")
    public ModelAndView registerCustomer(HttpServletRequest request, HttpSession session) throws ServiceLayerException {
        ModelAndView mav = new ModelAndView();
        mav.setViewName(CUSTOMER_REGISTER_PAGE);
        Map<String, String[]> data = request.getParameterMap();
        String login = data.get(LOGIN)[0];
        String password = data.get(PASSWORD)[0];

            if (isValidUserData(request)) {
                String companyName = Decoder.decode(data.get(COMPANY_NAME)[0]);
                String customerType = defineCustomerType(companyName);
                String name = Decoder.decode(data.get(NAME)[0]);
                String surname = Decoder.decode(data.get(SURNAME)[0]);
                String phone = data.get(PHONE)[0];
                String email = data.get(EMAIL)[0];

                User user = userService.createEntity(login, password, USER_CUSTOMER);
                userService.add(user);
                Customer customer = customerService.createCustomer(user, name, surname, customerType, phone, email,
                        companyName);
                customerService.add(customer);
                session.setAttribute(USER_DATA, user);
                mav.setViewName(CUSTOMER_MAIN_PAGE);
            }
        return mav;
    }

    private boolean isValidUserData(HttpServletRequest request) throws ServiceLayerException {
        boolean correctData = true;
            if (userService.loginExists(request.getParameterValues(LOGIN)[0])) {
                request.setAttribute(ERROR_REGISTER_LOGIN, STATUS_TRUE);
                correctData = false;
            }

            if (!request.getParameterValues(PASSWORD)[0].equals(request.getParameterValues(PASSWORD_CONFIRM)[0])) {
                request.setAttribute(ERROR_REGISTER_PASSWORD, STATUS_TRUE);
                correctData = false;
            }

            if (!RegisterLogic.legalPassword(request.getParameterValues(PASSWORD)[0])) {
                request.setAttribute(UNSAFE_PASSWORD, STATUS_TRUE);
                correctData = false;
            }
        return correctData;
    }

    private String defineCustomerType(String companyName) {
        String customerType;
        if (companyName == null || companyName.trim().length() < MINIMUM_COMPANY_NAME_LENGTH) {
            customerType = CUSTOMER_INDIVIDUAL;
        } else {
            customerType = CUSTOMER_LEGAL;
        }
        return customerType;
    }

}
