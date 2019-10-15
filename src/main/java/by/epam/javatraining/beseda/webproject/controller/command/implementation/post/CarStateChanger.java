package by.epam.javatraining.beseda.webproject.controller.command.implementation.post;

import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.CommandConstant.CONTEXT_TO_REPLACE;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.CommandConstant.EMPTY_STRING;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPParameter.CAR_ID;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPParameter.CAR_STATE_CHANGER;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPParameter.CURRENT_PAGE;
import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import by.epam.javatraining.beseda.webproject.controller.command.ActionCommand;
import by.epam.javatraining.beseda.webproject.controller.command.util.Decoder;
import by.epam.javatraining.beseda.webproject.controller.command.util.srcontent.SessionRequestContent;
import by.epam.javatraining.beseda.webproject.service.entityservice.CarService;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;


public class CarStateChanger implements ActionCommand {

	private static Logger log = Logger.getLogger(ERROR_LOGGER);

	@Autowired
	private CarService carService;
	
	@Override
	public String execute(SessionRequestContent content) {

		Map<String, String[]> parameters = content.requestParameters();

		try {
			String carState = Decoder.decode(parameters.get(CAR_STATE_CHANGER)[0]);
			int carId = Integer.parseInt(parameters.get(CAR_ID)[0]);
			carService.updateCarState(carId, carState);
		} catch (ServiceLayerException e) {
			log.error(this.getClass().getSimpleName() + e);
		}
		return parameters.get(CURRENT_PAGE)[0].replace(CONTEXT_TO_REPLACE, EMPTY_STRING);
	}
}