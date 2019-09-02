package by.epam.javatraining.beseda.webproject.controller.command.implementation.get;

import by.epam.javatraining.beseda.webproject.controller.command.ActionCommand;
import by.epam.javatraining.beseda.webproject.controller.command.util.srcontent.SessionRequestContent;

import java.util.Map;

import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPParameter.CAR_ID;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPParameter.CURRENT_PAGE;

public class ReloadCarsInRoutes implements ActionCommand {
    @Override
    public String execute(SessionRequestContent content) {
        Map<String, String[]> requestParam = content.requestParameters();
        Map<String, Object> requestAttr = content.requestAttributes();
        int carId = Integer.parseInt(requestParam.get(CAR_ID)[0]);
        requestAttr.put(CAR_ID, carId);
        return requestParam.get(CURRENT_PAGE)[0];
    }
}
