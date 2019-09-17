package by.epam.javatraining.beseda.webproject.controller.command.implementation.post.admin;

import by.epam.javatraining.beseda.webproject.controller.command.ActionCommand;
import by.epam.javatraining.beseda.webproject.controller.command.util.srcontent.SessionRequestContent;
import by.epam.javatraining.beseda.webproject.entity.route.Route;
import by.epam.javatraining.beseda.webproject.entity.route.Task;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPParameter.CURRENT_PAGE;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPParameter.ID;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPSessionAttribute.CHANGE_CAR;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPSessionAttribute.CHANGING_ROUTE;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPSessionAttribute.TASK_TO_CHANGE;

public class ChangeTaskFlag implements ActionCommand {

	@Override
	public String execute(SessionRequestContent content) {
		Map<String, String[]> requestParam = content.requestParameters();
		HttpSession session = content.getSession();
		int taskId = Integer.parseInt(requestParam.get(ID)[0]);
		Route route = (Route) session.getAttribute(CHANGING_ROUTE);
		List<Task> taskList = route.getTasksList();
		Task changingTask = null;
		for (Task task : taskList) {
			if (task.getId() == taskId) {
				changingTask = task;
			}
		}
		session.setAttribute(CHANGE_CAR, null);
		session.setAttribute(TASK_TO_CHANGE, changingTask);
		return requestParam.get(CURRENT_PAGE)[0].replace("/Trucking_company", "");
	}
}