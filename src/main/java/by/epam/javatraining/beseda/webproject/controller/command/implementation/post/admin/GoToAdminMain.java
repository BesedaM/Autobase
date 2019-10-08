package by.epam.javatraining.beseda.webproject.controller.command.implementation.post.admin;

import by.epam.javatraining.beseda.webproject.controller.command.ActionCommand;
import by.epam.javatraining.beseda.webproject.controller.command.util.srcontent.SessionRequestContent;

import javax.servlet.http.HttpSession;

import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPPath.ADMIN_MAIN_PAGE;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPSessionAttribute.REQUEST_CUSTOMER_MAP;

public class GoToAdminMain implements ActionCommand {

	@Override
	public String execute(SessionRequestContent content) {
		HttpSession session = content.getSession();
		session.setAttribute(REQUEST_CUSTOMER_MAP, null);
		return ADMIN_MAIN_PAGE;
	}
}