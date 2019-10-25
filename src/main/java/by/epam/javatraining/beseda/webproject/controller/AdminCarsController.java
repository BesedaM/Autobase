package by.epam.javatraining.beseda.webproject.controller;

import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPParameter.CAR_ID;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPSessionAttribute.CHANGE_CAR;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPSessionAttribute.CHANGING_ROUTE;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPSessionAttribute.STATUS_TRUE;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.USER_ADMIN;
import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import by.epam.javatraining.beseda.webproject.controller.util.CurrentPageProcessor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import by.epam.javatraining.beseda.webproject.controller.util.Decoder;
import by.epam.javatraining.beseda.webproject.entity.car.Car;
import by.epam.javatraining.beseda.webproject.entity.route.Route;
import by.epam.javatraining.beseda.webproject.service.entityservice.CarService;
import by.epam.javatraining.beseda.webproject.service.entityservice.RouteService;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;

@Controller
@PreAuthorize("hasAuthority('" + USER_ADMIN + "')")
@ResponseBody
public class AdminCarsController {

    @Autowired
    private CarService carService;

    @Autowired
    private RouteService routeService;

    private static Logger log = Logger.getLogger(ERROR_LOGGER);

    @PostMapping(value = {"/admin/cars/change_state"})
    public ModelAndView changeCarState(@RequestParam String car_state_changer, @RequestParam String car_id, @RequestParam String current_page) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName(CurrentPageProcessor.processPage(current_page));
        try {
            String carState = Decoder.decode(car_state_changer);
            int carId = Integer.parseInt(car_id);
            carService.updateCarState(carId, carState);
        } catch (ServiceLayerException e) {
            log.error(this.getClass().getSimpleName() + e);
        }
        return mav;
    }

    @PostMapping(value = {"/admin/cars/reload_cars_in_routes"})
    public ModelAndView reloadCarsInRoutes(@RequestParam String current_page, @RequestParam String car_id,
                                           Model model) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName(CurrentPageProcessor.processPage(current_page));
        int carId = Integer.parseInt(car_id);
        model.addAttribute(CAR_ID, carId);
        return mav;
    }

    @PostMapping(value = {"/admin/cars/change_cars_list"})
    public ModelAndView changeCarList(@RequestParam String current_page, HttpSession session) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName(CurrentPageProcessor.processPage(current_page));
        session.setAttribute(CHANGE_CAR, STATUS_TRUE);
        return mav;
    }

    @PostMapping(value = {"/admin/cars/change_cars"})
    public ModelAndView changeCars(@RequestParam String current_page, @RequestParam String[] cars_id, HttpSession session) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName(CurrentPageProcessor.processPage(current_page));
        Route route = (Route) session.getAttribute(CHANGING_ROUTE);
        List<Integer> previousCars = routeService.getCarsId(route.getId());
        List<Integer> newCars = new ArrayList<>();
        for (int i = 0; i < cars_id.length; i++) {
            int carId = Integer.parseInt(cars_id[i]);
            newCars.add(carId);
        }
        processCarsData(route, newCars, previousCars);
        session.setAttribute(CHANGE_CAR, null);
        return mav;
    }


    private void processCarsData(Route route, List<Integer> newCars, List<Integer> previousCars) {
        int routeId = route.getId();
        for (Integer carId : previousCars) {
            if (!newCars.contains(carId)) {
                routeService.deleteCar(routeId, carId);
                route.removeCar(carId);
            }
        }

        for (Integer carId : newCars) {
            if (!previousCars.contains(carId)) {
                routeService.addCar(routeId, carId);
                Car car = carService.getEntityById(carId);
                route.addCar(car);
            }
        }
    }


}
