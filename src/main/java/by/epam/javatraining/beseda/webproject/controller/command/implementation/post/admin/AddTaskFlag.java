package by.epam.javatraining.beseda.webproject.controller.command.implementation.post.admin;

import by.epam.javatraining.beseda.webproject.controller.command.ActionCommand;
import by.epam.javatraining.beseda.webproject.controller.command.util.srcontent.SessionRequestContent;

import javax.servlet.http.HttpSession;
import java.util.Map;

import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPParameter.CURRENT_PAGE;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPSessionAttribute.ADD_TASK_FLAG;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPSessionAttribute.STATUS_TRUE;

public class AddTaskFlag implements ActionCommand {

    @Override
    public String execute(SessionRequestContent content) {
        Map<String, String[]> requestParam = content.requestParameters();
        HttpSession session = content.getSession();

        session.setAttribute(ADD_TASK_FLAG,STATUS_TRUE);
        return requestParam.get(CURRENT_PAGE)[0];
    }
}
