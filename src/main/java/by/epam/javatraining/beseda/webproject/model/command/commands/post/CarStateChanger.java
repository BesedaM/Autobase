package by.epam.javatraining.beseda.webproject.model.command.commands.post;

import by.epam.javatraining.beseda.webproject.model.command.ActionCommand;
import by.epam.javatraining.beseda.webproject.model.command.util.Decoder;
import by.epam.javatraining.beseda.webproject.model.command.util.srcontent.SessionRequestContent;
import by.epam.javatraining.beseda.webproject.model.entity.car.Car;
import by.epam.javatraining.beseda.webproject.model.exception.entityexception.EntityLogicException;
import by.epam.javatraining.beseda.webproject.model.service.entity.CarService;
import by.epam.javatraining.beseda.webproject.model.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.model.service.factory.ServiceEntityFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import java.util.Map;

import static by.epam.javatraining.beseda.webproject.model.command.util.jsp.JSPParameter.CAR_ID;
import static by.epam.javatraining.beseda.webproject.model.command.util.jsp.JSPParameter.CAR_STATE_CHANGER;
import static by.epam.javatraining.beseda.webproject.model.command.util.jsp.JSPParameter.CURRENT_PAGE;
import static by.epam.javatraining.beseda.webproject.model.command.util.jsp.JSPSessionAttribute.CAR;
import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;

public class CarStateChanger implements ActionCommand {

    private static ServiceEntityFactory serviceEntityFactory = ServiceEntityFactory.getFactory();
    private static Logger log = Logger.getLogger(ERROR_LOGGER);

    @Override
    public String execute(SessionRequestContent content) {

        Map<String, String[]> parameters = content.requestParameters();

        try {
            String carState = Decoder.decode(parameters.get(CAR_STATE_CHANGER)[0]);
            int carId = Integer.parseInt(parameters.get(CAR_ID)[0]);
            CarService carService = serviceEntityFactory.getCarService();
            carService.updateCarState(carId, carState);
        } catch (ServiceLayerException e) {
            log.error(this.getClass().getSimpleName() + e);
        }
        return parameters.get(CURRENT_PAGE)[0];
    }
}
