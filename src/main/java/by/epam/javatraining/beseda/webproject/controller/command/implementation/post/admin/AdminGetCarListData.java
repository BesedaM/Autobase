package by.epam.javatraining.beseda.webproject.controller.command.implementation.post.admin;

import by.epam.javatraining.beseda.webproject.controller.command.ActionCommand;
import by.epam.javatraining.beseda.webproject.controller.command.util.srcontent.SessionRequestContent;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPPath.CAR_LIST_PAGE;


public class AdminGetCarListData implements ActionCommand{
	
	public String execute(SessionRequestContent content) {
		return CAR_LIST_PAGE;
	}

}
