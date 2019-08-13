package by.epam.javatraining.beseda.webproject.model.command;

import by.epam.javatraining.beseda.webproject.model.service.exception.ServiceLogicException;
import by.epam.javatraining.beseda.webproject.model.command.util.srcontent.SessionRequestContent;
import org.apache.log4j.Logger;

import static by.epam.javatraining.beseda.webproject.model.command.util.constant.JSPParameter.COMMAND;

public class ActionCommandFactory {

    private static Logger log = Logger.getLogger(ActionCommandFactory.class.getSimpleName());

    public static ActionCommand defineCommand(SessionRequestContent content) {
        ActionCommand current = ActionCommandEnum.LOGIN.getCommand();
        String actionPage = content.requestParameters().get(COMMAND)[0];
        if (actionPage != null) {
            try {
                ActionCommandEnum currentEnum = ActionCommandEnum.valueOf(actionPage.toUpperCase());
                current = currentEnum.getCommand();
            } catch (IllegalArgumentException e) {
                log.error(new ServiceLogicException(new EnumConstantNotPresentException(ActionCommandEnum.class, actionPage)));
            }
        }
        return current;
    }
}
