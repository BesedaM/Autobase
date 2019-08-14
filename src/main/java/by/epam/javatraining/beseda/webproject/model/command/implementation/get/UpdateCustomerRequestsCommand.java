package by.epam.javatraining.beseda.webproject.model.command.implementation.get;

import by.epam.javatraining.beseda.webproject.model.command.ActionCommand;
import by.epam.javatraining.beseda.webproject.model.command.util.srcontent.SessionRequestContent;
import by.epam.javatraining.beseda.webproject.model.entity.Request;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

import static by.epam.javatraining.beseda.webproject.model.command.util.constant.JSPParameter.CURRENT_PAGE;
import static by.epam.javatraining.beseda.webproject.model.command.util.constant.JSPParameter.NEW_REQUEST;
import static by.epam.javatraining.beseda.webproject.model.command.util.constant.JSPSessionAttribute.REQUEST_LIST;

public class UpdateCustomerRequestsCommand implements ActionCommand {

    @Override
    public String execute(SessionRequestContent content) {
        HttpSession session = content.getSession();
        Map<String, String[]> parameters = content.requestParameters();
        List<Request> requestList=(List<Request>)session.getAttribute(REQUEST_LIST);
        Request newRequest=(Request)session.getAttribute(NEW_REQUEST);
        requestList.add(newRequest);
        session.setAttribute(NEW_REQUEST,null);
        return parameters.get(CURRENT_PAGE)[0];
    }
}
