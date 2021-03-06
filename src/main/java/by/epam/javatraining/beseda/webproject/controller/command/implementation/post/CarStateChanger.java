package by.epam.javatraining.beseda.webproject.controller.command.implementation.post;

import by.epam.javatraining.beseda.webproject.controller.command.ActionCommand;
import by.epam.javatraining.beseda.webproject.controller.command.util.Decoder;
import by.epam.javatraining.beseda.webproject.controller.command.util.srcontent.SessionRequestContent;
import by.epam.javatraining.beseda.webproject.service.entityservice.CarService;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.service.entityservice.ServiceEntityFactory;
import org.apache.log4j.Logger;

import java.util.Map;

import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPParameter.CAR_ID;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPParameter.CAR_STATE_CHANGER;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPParameter.CURRENT_PAGE;
import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.CommandConstant.CONTEXT_TO_REPLACE;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.CommandConstant.EMPTY_STRING;


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
		return parameters.get(CURRENT_PAGE)[0].replace(CONTEXT_TO_REPLACE, EMPTY_STRING);
	}
}