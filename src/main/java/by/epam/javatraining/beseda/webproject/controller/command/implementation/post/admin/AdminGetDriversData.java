package by.epam.javatraining.beseda.webproject.controller.command.implementation.post.admin;

import by.epam.javatraining.beseda.webproject.controller.command.ActionCommand;
import by.epam.javatraining.beseda.webproject.controller.command.util.srcontent.SessionRequestContent;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPPath.DRIVER_LIST_PAGE;;


public class AdminGetDriversData implements ActionCommand{
	
	public String execute(SessionRequestContent content) {
		return DRIVER_LIST_PAGE;
	}

}