package by.epam.javatraining.beseda.webproject.controller;

import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPPath.DRIVER_MAIN_PAGE;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.USER_DRIVER;
import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import by.epam.javatraining.beseda.webproject.controller.util.Decoder;
import by.epam.javatraining.beseda.webproject.service.entityservice.CarService;
import by.epam.javatraining.beseda.webproject.service.entityservice.RouteService;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.service.processors.DriverProcessor;

@Controller
@PreAuthorize("hasAuthority('" + USER_DRIVER + "')")
@ResponseBody
@RequestMapping(value = {"/driver"})
public class DriverController {

    private static Logger log = Logger.getLogger(ERROR_LOGGER);

    @Autowired
    private DriverProcessor driverProcessor;

    @Autowired
    private CarService carService;

    @Autowired
    private RouteService routeService;

    @RequestMapping("/driver_main")
    public ModelAndView driverMain() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName(DRIVER_MAIN_PAGE);
        return mav;
    }

    @PostMapping(value = {"/driver_main/update_car_route"})
    public ModelAndView changeRouteStatusCarState(@RequestParam String route_status_changer,
                                                  @RequestParam String car_state_changer, @RequestParam String car_id, @RequestParam String route_id,
                                                  HttpSession session) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName(DRIVER_MAIN_PAGE);
        try {
            String carState = Decoder.decode(car_state_changer);
            int carId = Integer.parseInt(car_id);
            carService.updateCarState(carId, carState);

            String routeStatus = Decoder.decode(route_status_changer);
            int routeId = Integer.parseInt(route_id);
            routeService.updateRouteStatus(routeId, routeStatus);

            driverProcessor.processDriverData(session);
        } catch (ServiceLayerException e) {
            log.error(e);
        }
        return mav;
    }

    @PostMapping(value = {"/driver_main/update_route"})
    public ModelAndView changeRouteStatus(@RequestParam String route_status_changer, @RequestParam String route_id,
                                          HttpSession session) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName(DRIVER_MAIN_PAGE);
        try {
            String routeStatus = Decoder.decode(route_status_changer);
            int routeId = Integer.parseInt(route_id);
            routeService.updateRouteStatus(routeId, routeStatus);
            driverProcessor.processDriverData(session);
        } catch (ServiceLayerException e) {
            log.error(e);
        }
        return mav;
    }

}
