package by.epam.javatraining.beseda.webproject.model.command.commands.post;

import by.epam.javatraining.beseda.webproject.model.command.ActionCommand;
import by.epam.javatraining.beseda.webproject.model.command.ActionCommandEnum;
import by.epam.javatraining.beseda.webproject.model.command.commands.parts.DriverProcessor;
import by.epam.javatraining.beseda.webproject.model.command.util.srcontent.SessionRequestContent;
import by.epam.javatraining.beseda.webproject.model.entity.user.User;
import by.epam.javatraining.beseda.webproject.model.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.model.service.factory.ServiceEntityFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;

import static by.epam.javatraining.beseda.webproject.model.command.util.jsp.JSPSessionAttribute.USER_DATA;
import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;

public class RouteStatusCarStateChanger implements ActionCommand {

    private static ActionCommand updateRouteStatus = ActionCommandEnum.CHANGE_ROUTE_STATUS.getCommand();
    private static ActionCommand updateCarState = ActionCommandEnum.CHANGE_CAR_STATE.getCommand();

    private static Logger log = Logger.getLogger(ERROR_LOGGER);
//    private static ServiceEntityFactory serviceEntityFactory = ServiceEntityFactory.getFactory();

    @Override
    public String execute(SessionRequestContent content) {
        String page = updateRouteStatus.execute(content);
        updateCarState.execute(content);
        updateData(content.getSession());
        return page;
    }


//    private void updateRouteStatus(Map<String, String[]> parameters, HttpSession httpSession) {
//        try {
//            String routeStatus = Decoder.decode(parameters.get(ROUTE_STATUS_CHANGER)[0]);
//            int route_id = Integer.parseInt(parameters.get(ROUTE_ID)[0]);
//            RouteService routeService = serviceEntityFactory.getRouteService();
//            routeService.updateRouteStatus(route_id, routeStatus);
//        } catch (ServiceLayerException e) {
//            log.error(e);
//        }
//    }
//
//
//    private void updateCarState(Map<String, String[]> parameters, HttpSession httpSession) {
//        try {
//            String carState = Decoder.decode(parameters.get(CAR_STATE_CHANGER)[0]);
//            Car car = (Car) httpSession.getAttribute(CAR);
//            car.setState(carState);
//            CarService carService = serviceEntityFactory.getCarService();
//            carService.update(car);
//        } catch (ServiceLayerException | EntityLogicException e) {
//            log.error(e);
//        }
//    }

    private void updateData(HttpSession session) {
        try {
            DriverProcessor.processDriverData((User) session.getAttribute(USER_DATA), session);
        } catch (ServiceLayerException e) {
            log.error(e);
        }
    }

}
