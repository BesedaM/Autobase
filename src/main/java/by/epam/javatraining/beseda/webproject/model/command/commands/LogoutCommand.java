package by.epam.javatraining.beseda.webproject.model.command.commands;

import by.epam.javatraining.beseda.webproject.model.command.ActionCommand;
import by.epam.javatraining.beseda.webproject.model.command.util.srcontent.SessionRequestContent;

import javax.servlet.http.HttpSession;

import static by.epam.javatraining.beseda.webproject.model.command.util.jsp.JSPPath.LOGIN_PAGE;
import static by.epam.javatraining.beseda.webproject.model.command.util.jsp.JSPSessionAttribute.LANGUAGE;
import static by.epam.javatraining.beseda.webproject.model.command.util.jsp.JSPSessionAttribute.LOCALE_FILE;

public class LogoutCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent content) {
        HttpSession httpSession=content.getSession();
        String language= (String) httpSession.getAttribute(LANGUAGE);
        String locale_file= (String) httpSession.getAttribute(LOCALE_FILE);
        httpSession.invalidate();

        httpSession=content.renewSession();
        httpSession.setAttribute(LANGUAGE, language);
        httpSession.setAttribute(LOCALE_FILE, locale_file);

        return LOGIN_PAGE;
    }
}
