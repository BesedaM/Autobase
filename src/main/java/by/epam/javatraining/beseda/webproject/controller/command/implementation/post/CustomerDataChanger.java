package by.epam.javatraining.beseda.webproject.controller.command.implementation.post;

import by.epam.javatraining.beseda.webproject.controller.command.ActionCommand;
import by.epam.javatraining.beseda.webproject.controller.command.util.Decoder;
import by.epam.javatraining.beseda.webproject.controller.command.util.srcontent.SessionRequestContent;
import by.epam.javatraining.beseda.webproject.entity.user.Customer;
import by.epam.javatraining.beseda.webproject.entity.exception.EntityLogicException;
import by.epam.javatraining.beseda.webproject.service.entityservice.CustomerService;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.service.entityservice.ServiceEntityFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import java.util.Map;

import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPAttribute.DATA_CHANGED;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPAttribute.STATUS_TRUE;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPParameter.*;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPSessionAttribute.USER_DATA;
import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.CommandConstant.CONTEXT_TO_REPLACE;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.CommandConstant.EMPTY_STRING;


public class CustomerDataChanger implements ActionCommand {

	private static Logger log = Logger.getLogger(ERROR_LOGGER);
	private CustomerService customerService = ServiceEntityFactory.getFactory().getCustomerService();

	@Override
	public String execute(SessionRequestContent content) {
		Map<String, Object> attributes = content.requestAttributes();
		Map<String, String[]> data = content.requestParameters();
		HttpSession session = content.getSession();

		Customer customer = (Customer) session.getAttribute(USER_DATA);

		try {
			String companyName = Decoder.decode(data.get(COMPANY_NAME)[0]);
			String name = Decoder.decode(data.get(NAME)[0]);
			String surname = Decoder.decode(data.get(SURNAME)[0]);
			String phone = data.get(PHONE)[0];
			String email = data.get(EMAIL)[0];
			boolean dataChanged = false;

			if (parameterNonNull(companyName)) {
				customer.setCompanyName(companyName);
				dataChanged = true;
			}
			if (parameterNonNull(name)) {
				customer.setName(name);
				dataChanged = true;
			}
			if (parameterNonNull(surname)) {
				customer.setSurname(surname);
				dataChanged = true;
			}
			if (parameterNonNull(phone)) {
				customer.setPhone(phone);
				dataChanged = true;
			}
			if (parameterNonNull(email)) {
				customer.setEmail(email);
				dataChanged = true;
			}
			customerService.update(customer);

			if (dataChanged) {
				attributes.put(DATA_CHANGED, STATUS_TRUE);
			}
		} catch (ServiceLayerException | EntityLogicException e) {
			log.error(e);
		}

		return data.get(CURRENT_PAGE)[0].replace(CONTEXT_TO_REPLACE, EMPTY_STRING);
	}

	private boolean parameterNonNull(String parameter) {
		return parameter.trim().length() > 0;
	}
}