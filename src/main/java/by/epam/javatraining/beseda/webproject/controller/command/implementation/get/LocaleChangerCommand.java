package by.epam.javatraining.beseda.webproject.controller.command.implementation.get;

import by.epam.javatraining.beseda.webproject.controller.command.ActionCommand;
import by.epam.javatraining.beseda.webproject.controller.command.util.srcontent.SessionRequestContent;

import javax.servlet.http.HttpSession;
import java.util.Map;

import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPParameter.CURRENT_PAGE;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPParameter.LANGUAGE_SELECT;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPSessionAttribute.*;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.CommandConstant.CONTEXT_TO_REPLACE;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.CommandConstant.EMPTY_STRING;


public class LocaleChangerCommand implements ActionCommand {

	@Override
	public String execute(SessionRequestContent content) {

		HttpSession session = content.getSession();
		Map<String, String[]> data = content.requestParameters();

		String language = data.get(LANGUAGE_SELECT)[0];
		String locale = null;
		session.setAttribute(LANGUAGE, language);

		if (language.equals(LANGUAGE_EN)) {
			locale = LOCALE_EN;
		} else if (language.equals(LANGUAGE_RU)) {
			locale = LOCALE_RU;
		}

		session.setAttribute(LOCALE_FILE, locale);

		return data.get(CURRENT_PAGE)[0].replace(CONTEXT_TO_REPLACE, EMPTY_STRING);
	}
}