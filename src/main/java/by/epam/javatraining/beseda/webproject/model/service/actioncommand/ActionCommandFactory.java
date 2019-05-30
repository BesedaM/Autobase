package by.epam.javatraining.beseda.webproject.model.service.actioncommand;

import by.epam.javatraining.beseda.webproject.model.exception.serviceexception.ServiceLogicException;
import by.epam.javatraining.beseda.webproject.model.service.commands.ActionCommand;
import by.epam.javatraining.beseda.webproject.util.srcontent.SessionRequestContent;
import org.apache.log4j.Logger;

import static by.epam.javatraining.beseda.webproject.util.jspproperties.JSPParameter.COMMAND;

public class ActionCommandFactory {

    private Logger log = Logger.getLogger(ActionCommandFactory.class.getSimpleName());

    public ActionCommand defineCommand(SessionRequestContent content) {
        ActionCommand current = ActionCommandEnum.EMPTY_COMMAND.getCommand();
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
