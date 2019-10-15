package by.epam.javatraining.beseda.webproject.controller.command.implementation.post;

import by.epam.javatraining.beseda.webproject.controller.command.ActionCommand;
import by.epam.javatraining.beseda.webproject.controller.command.ActionCommandEnum;
import by.epam.javatraining.beseda.webproject.controller.command.util.srcontent.SessionRequestContent;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.service.processors.DriverProcessor;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;

import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;

public class RouteStatusCarStateChanger implements ActionCommand {

    private static ActionCommand updateRouteStatus = ActionCommandEnum.CHANGE_ROUTE_STATUS.getCommand();
    private static ActionCommand updateCarState = ActionCommandEnum.CHANGE_CAR_STATE.getCommand();

    private static Logger log = Logger.getLogger(ERROR_LOGGER);

    @Override
    public String execute(SessionRequestContent content) {
        String page = updateRouteStatus.execute(content);
        updateCarState.execute(content);
        updateData(content.getSession());
        return page;
    }


    private void updateData(HttpSession session) {
        try {
            DriverProcessor.processDriverData(session);
        } catch (ServiceLayerException e) {
            log.error(e);
        }
    }

}
