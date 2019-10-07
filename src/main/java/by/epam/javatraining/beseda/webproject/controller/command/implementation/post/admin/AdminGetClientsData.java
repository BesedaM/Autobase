package by.epam.javatraining.beseda.webproject.controller.command.implementation.post.admin;

import by.epam.javatraining.beseda.webproject.controller.command.ActionCommand;
import by.epam.javatraining.beseda.webproject.controller.command.util.srcontent.SessionRequestContent;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPPath.CLIENT_LIST_PAGE;;


public class AdminGetClientsData implements ActionCommand{
	
	public String execute(SessionRequestContent content) {
		return CLIENT_LIST_PAGE;
	}

}