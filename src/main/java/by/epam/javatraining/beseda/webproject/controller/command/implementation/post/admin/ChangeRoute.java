package by.epam.javatraining.beseda.webproject.controller.command.implementation.post.admin;

import by.epam.javatraining.beseda.webproject.controller.command.ActionCommand;
import by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPParameter;
import by.epam.javatraining.beseda.webproject.controller.command.util.srcontent.SessionRequestContent;
import by.epam.javatraining.beseda.webproject.entity.Request;
import by.epam.javatraining.beseda.webproject.entity.route.Route;
import by.epam.javatraining.beseda.webproject.entity.user.Customer;

import javax.servlet.http.HttpSession;
import java.util.Map;

import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPPath.CHANGE_ROUTE_PAGE;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPSessionAttribute.CHANGING_ROUTE;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPSessionAttribute.REQUEST_CUSTOMER_MAP;

public class ChangeRoute implements ActionCommand {

	@SuppressWarnings("unchecked")
	@Override
	public String execute(SessionRequestContent content) {
		Map<String, String[]> parameters = content.requestParameters();
		HttpSession session = content.getSession();
		int routeId = Integer.parseInt(parameters.get(JSPParameter.ID)[0]);
		Map<Request, Customer> requestCustomerMap = (Map<Request, Customer>) session.getAttribute(REQUEST_CUSTOMER_MAP);
		Route route = null;
		for (Map.Entry<Request, Customer> entry : requestCustomerMap.entrySet()) {
			if (entry.getKey().getId() == routeId) {
				route = entry.getKey().getRoute();
			}
		}
		session.setAttribute(CHANGING_ROUTE, route);

		return CHANGE_ROUTE_PAGE;
	}
}