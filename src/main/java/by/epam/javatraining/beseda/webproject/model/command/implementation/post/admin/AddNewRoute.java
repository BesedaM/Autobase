package by.epam.javatraining.beseda.webproject.model.command.implementation.post.admin;

import by.epam.javatraining.beseda.webproject.model.command.ActionCommand;
import by.epam.javatraining.beseda.webproject.model.command.util.srcontent.SessionRequestContent;
import by.epam.javatraining.beseda.webproject.model.entity.user.Customer;
import by.epam.javatraining.beseda.webproject.model.service.entityservice.CustomerService;
import by.epam.javatraining.beseda.webproject.model.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.model.service.factory.ServiceEntityFactory;
import org.apache.log4j.Logger;

import java.util.Map;

import static by.epam.javatraining.beseda.webproject.model.command.util.constant.JSPAttribute.CUSTOMER;
import static by.epam.javatraining.beseda.webproject.model.command.util.constant.JSPAttribute.TEXT;
import static by.epam.javatraining.beseda.webproject.model.command.util.constant.JSPParameter.CUSTOMER_ID;
import static by.epam.javatraining.beseda.webproject.model.command.util.constant.JSPParameter.ID;
import static by.epam.javatraining.beseda.webproject.model.command.util.constant.JSPParameter.REQUEST_TEXT;
import static by.epam.javatraining.beseda.webproject.model.command.util.constant.JSPPath.ADD_NEW_ROUTE;
import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;

public class AddNewRoute implements ActionCommand {

    private static Logger log = Logger.getLogger(ERROR_LOGGER);
    private ServiceEntityFactory serviceEntityFactory = ServiceEntityFactory.getFactory();

    @Override
    public String execute(SessionRequestContent content) {
        CustomerService customerService = serviceEntityFactory.getCustomerService();

        Map<String, String[]> requestParam = content.requestParameters();
        Map<String, Object> requestAttributes = content.requestAttributes();
        Integer requestId = Integer.parseInt(requestParam.get(ID)[0]);
        int customerId = Integer.parseInt(requestParam.get(CUSTOMER_ID)[0]);
        try {
            Customer customer = customerService.getEntityById(customerId);
            requestAttributes.put(CUSTOMER, customer);
        } catch (ServiceLayerException e) {
            log.error(e);
        }

        String text = requestParam.get(REQUEST_TEXT)[0];
        requestAttributes.put(ID, requestId);
        requestAttributes.put(TEXT, text);

        return ADD_NEW_ROUTE;
    }
}
