package by.epam.javatraining.beseda.webproject.model.command.commands.post;

import by.epam.javatraining.beseda.webproject.model.command.ActionCommand;
import by.epam.javatraining.beseda.webproject.model.command.util.srcontent.SessionRequestContent;

import javax.servlet.http.HttpSession;
import java.util.Map;

import static by.epam.javatraining.beseda.webproject.model.command.util.jsp.JSPParameter.CURRENT_PAGE;
import static by.epam.javatraining.beseda.webproject.model.command.util.jsp.JSPParameter.LANGUAGE_SELECT;
import static by.epam.javatraining.beseda.webproject.model.command.util.jsp.JSPSessionAttribute.*;

public class LocaleChangeCommand implements ActionCommand {

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

        return data.get(CURRENT_PAGE)[0];
    }
}
