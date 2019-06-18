package by.epam.javatraining.beseda.webproject.model.command.commands;

import by.epam.javatraining.beseda.webproject.model.command.ActionCommand;
import by.epam.javatraining.beseda.webproject.model.entity.user.Customer;
import by.epam.javatraining.beseda.webproject.model.entity.user.User;
import by.epam.javatraining.beseda.webproject.model.exception.serviceexception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.model.exception.serviceexception.ServiceTechnicalException;
import by.epam.javatraining.beseda.webproject.model.service.entity.CustomerService;
import by.epam.javatraining.beseda.webproject.model.service.entity.UserService;
import by.epam.javatraining.beseda.webproject.util.srcontent.SessionRequestContent;
import org.apache.log4j.Logger;

import java.util.Map;

import static by.epam.javatraining.beseda.webproject.util.database.DBEnumTable.CUSTOMER_INDIVIDUAL;
import static by.epam.javatraining.beseda.webproject.util.database.DBEnumTable.CUSTOMER_LEGAL;
import static by.epam.javatraining.beseda.webproject.util.database.DBEnumTable.USER_CUSTOMER;
import static by.epam.javatraining.beseda.webproject.util.jspproperties.JSPAttribute.*;
import static by.epam.javatraining.beseda.webproject.util.jspproperties.JSPParameter.*;
import static by.epam.javatraining.beseda.webproject.util.jspproperties.JSPPath.CUSTOMER_MAIN_PAGE;
import static by.epam.javatraining.beseda.webproject.util.jspproperties.JSPPath.CUSTOMER_REGISTER_PAGE;
import static by.epam.javatraining.beseda.webproject.util.jspproperties.JSPPath.LOGIN_PAGE;

public class CustomerRegisterCommand implements ActionCommand {

    private Logger log = Logger.getLogger("error");

    @Override
    public String execute(SessionRequestContent content) {
        String page = LOGIN_PAGE;
        UserService us = UserService.getService();
        CustomerService cs = CustomerService.getService();
        Map<String, String[]> data = content.requestParameters();
        String login = data.get(LOGIN)[0];
        String password = data.get(PASSWORD)[0];
        String passwordConfirm = data.get(PASSWORD_CONFIRM)[0];
        try {
            if (us.loginExists(login)) {
                content.requestAttributes().put(ERROR_REGISTER_LOGIN, ERROR_REGISTER_LOGIN_MESSAGE);
                page = CUSTOMER_REGISTER_PAGE;
            }
            if (!password.equals(passwordConfirm)) {
                content.requestAttributes().put(ERROR_REGISTER_PASSWORD, ERROR_REGISTER_PASSWORD_MESSAGE);
                page = CUSTOMER_REGISTER_PAGE;
            }
            if (page.equals(CUSTOMER_REGISTER_PAGE)) {
                return page;
            }
        } catch (ServiceTechnicalException e) {
            log.error(e);
        }

        String companyName = data.get(COMPANY_NAME)[0];
        String customerType;
        if (companyName == null || companyName.length() < 3) {
            customerType = CUSTOMER_INDIVIDUAL;
        } else {
            customerType = CUSTOMER_LEGAL;
        }
        String name = data.get(NAME)[0];
        String surname = data.get(SURNAME)[0];
        String phone = data.get(PHONE)[0];
        String email = data.get(EMAIL)[0];
        try {
            User user = us.createEntity(login, password, USER_CUSTOMER);
            Customer customer = cs.createCustomer(user, name, surname, customerType, phone, email, companyName);
            cs.add(customer);
            page = CUSTOMER_MAIN_PAGE;
        } catch (ServiceLayerException e) {
            log.error(e);
        }
        return page;
    }

}
