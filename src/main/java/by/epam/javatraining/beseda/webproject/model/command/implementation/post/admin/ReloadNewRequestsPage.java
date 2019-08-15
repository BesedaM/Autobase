package by.epam.javatraining.beseda.webproject.model.command.implementation.post.admin;

import by.epam.javatraining.beseda.webproject.model.command.ActionCommand;
import by.epam.javatraining.beseda.webproject.model.command.util.srcontent.SessionRequestContent;

import java.util.Map;

import static by.epam.javatraining.beseda.webproject.model.command.util.constant.JSPParameter.CAR_ID;
import static by.epam.javatraining.beseda.webproject.model.command.util.constant.JSPPath.NEW_REQUESTS_PAGE;

public class ReloadNewRequestsPage implements ActionCommand {

    @Override
    public String execute(SessionRequestContent content) {
        Map<String, String[]> requestParam = content.requestParameters();
        Map<String, Object> requestAttr = content.requestAttributes();
        int carId = Integer.parseInt(requestParam.get(CAR_ID)[0]);
        requestAttr.put(CAR_ID, carId);
        return NEW_REQUESTS_PAGE;
    }
}
