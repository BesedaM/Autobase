package by.epam.javatraining.beseda.webproject.controller.command.implementation.get;

import by.epam.javatraining.beseda.webproject.controller.command.ActionCommand;
import by.epam.javatraining.beseda.webproject.controller.command.util.srcontent.SessionRequestContent;

import javax.servlet.http.HttpSession;

import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPPath.LOGIN_PAGE;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPSessionAttribute.LANGUAGE;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPSessionAttribute.LOCALE_FILE;

public class LogoutCommand implements ActionCommand {
	
	@Override
	public String execute(SessionRequestContent content) {
		HttpSession httpSession = content.getSession();
		String language = (String) httpSession.getAttribute(LANGUAGE);
		String localeFile = (String) httpSession.getAttribute(LOCALE_FILE);
		httpSession.invalidate();

		httpSession = content.renewSession();
		httpSession.setAttribute(LANGUAGE, language);
		httpSession.setAttribute(LOCALE_FILE, localeFile);

		return LOGIN_PAGE;
	}
}