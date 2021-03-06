package by.epam.javatraining.beseda.webproject.controller.command.implementation.post;

import by.epam.javatraining.beseda.webproject.controller.command.ActionCommand;
import by.epam.javatraining.beseda.webproject.controller.command.util.srcontent.SessionRequestContent;
import by.epam.javatraining.beseda.webproject.entity.user.User;
import by.epam.javatraining.beseda.webproject.logic.RegisterLogic;
import by.epam.javatraining.beseda.webproject.service.entityservice.UserService;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.service.entityservice.ServiceEntityFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import java.util.Map;

import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPAttribute.ERROR_MESSAGE;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPAttribute.PASSWORD_CHANGED;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPAttribute.STATUS_TRUE;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPParameter.*;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPSessionAttribute.USER_DATA;
import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.CommandConstant.CONTEXT_TO_REPLACE;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.CommandConstant.EMPTY_STRING;


public class CustomerPasswordChanger implements ActionCommand {

	private ServiceEntityFactory serviceEntityFactory = ServiceEntityFactory.getFactory();
	private UserService userService = serviceEntityFactory.getUserService();
	private static Logger log = Logger.getLogger(ERROR_LOGGER);

	@Override
	public String execute(SessionRequestContent content) {

		Map<String, String[]> data = content.requestParameters();
		Map<String, Object> attributes = content.requestAttributes();
		HttpSession session = content.getSession();
		String newPassword = data.get(NEW_PASSWORD)[0];
		String password = data.get(PASSWORD)[0];
		String passwordConfirm = data.get(PASSWORD_CONFIRM)[0];

		if (password.equals(passwordConfirm) && RegisterLogic.legalPassword(newPassword)) {
			User user = (User) session.getAttribute(USER_DATA);
			try {
				if (userService.changePassword(user.getId(), newPassword)) {
					attributes.put(PASSWORD_CHANGED, STATUS_TRUE);
				}
			} catch (ServiceLayerException e) {
				log.error(e + " Impossible to change user's password");
			}
		} else {
			attributes.put(ERROR_MESSAGE, STATUS_TRUE);
		}

		return data.get(CURRENT_PAGE)[0].replace(CONTEXT_TO_REPLACE, EMPTY_STRING);
	}
}