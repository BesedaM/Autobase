package by.epam.javatraining.beseda.webproject.controller.command.implementation.post.admin;

import by.epam.javatraining.beseda.webproject.controller.command.ActionCommand;
import by.epam.javatraining.beseda.webproject.controller.command.util.srcontent.SessionRequestContent;
import by.epam.javatraining.beseda.webproject.entity.route.Route;
import by.epam.javatraining.beseda.webproject.service.entityservice.ServiceEntityFactory;
import by.epam.javatraining.beseda.webproject.service.entityservice.TaskService;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLogicException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import java.util.Map;

import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPParameter.CURRENT_PAGE;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPParameter.ID;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPSessionAttribute.CHANGING_ROUTE;
import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;

public class DeleteTask implements ActionCommand {

	private static Logger log = Logger.getLogger(ERROR_LOGGER);
	private static ServiceEntityFactory serviceEntityFactory = ServiceEntityFactory.getFactory();

	private static TaskService taskService = serviceEntityFactory.getTaskService();

	@Override
	public String execute(SessionRequestContent content) {
		Map<String, String[]> requestParam = content.requestParameters();
		HttpSession session = content.getSession();
		Route route = (Route) session.getAttribute(CHANGING_ROUTE);

		int taskId = Integer.parseInt(requestParam.get(ID)[0]);
		try {
			taskService.delete(taskId);
			route.removeTask(taskId);
		} catch (ServiceLogicException e) {
			log.error(e);
		}
		return requestParam.get(CURRENT_PAGE)[0].replace("/Trucking_company", "");
	}
}