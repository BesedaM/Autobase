package by.epam.javatraining.beseda.webproject.controller.command.implementation.post.admin;

import by.epam.javatraining.beseda.webproject.controller.command.ActionCommand;
import by.epam.javatraining.beseda.webproject.controller.command.implementation.parts.CarsDataProcessor;
import by.epam.javatraining.beseda.webproject.controller.command.util.srcontent.SessionRequestContent;

import javax.servlet.http.HttpSession;

import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPPath.CURRENT_REQUESTS_PAGE;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPSessionAttribute.CHANGE_CAR;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPSessionAttribute.CHANGING_ROUTE;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPSessionAttribute.TASK_TO_CHANGE;

public class GoToCurrentRequests implements ActionCommand {
	@Override
	public String execute(SessionRequestContent content) {
		HttpSession session = content.getSession();
		session.setAttribute(CHANGE_CAR, null);
		session.setAttribute(CHANGING_ROUTE, null);
		session.setAttribute(TASK_TO_CHANGE, null);
		CarsDataProcessor.processCarsData(session);
		return CURRENT_REQUESTS_PAGE;
	}
}