package by.epam.javatraining.beseda.webproject.controller;

import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPAttribute.CUSTOMER;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPParameter.CARS_ID;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPParameter.CURRENT_PAGE;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPParameter.CUSTOMER_ID;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPParameter.ID;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPParameter.NEW_REQUEST_ID;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPParameter.NEW_REQUEST_TEXT;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPParameter.REQUEST_TEXT;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPParameter.ROUTE_NAME;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPPath.ADD_NEW_ROUTE_PAGE;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPPath.CHANGE_ROUTE_PAGE;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPSessionAttribute.CHANGING_ROUTE;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPSessionAttribute.CURRENT_ROUTE;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPSessionAttribute.REQUEST_CUSTOMER_MAP;
import static by.epam.javatraining.beseda.webproject.controller.util.constant.JSPSessionAttribute.TASK_LIST;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.USER_ADMIN;
import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
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
import by.epam.javatraining.beseda.webproject.controller.util.constant.JSPParameter;
import by.epam.javatraining.beseda.webproject.entity.Request;
import by.epam.javatraining.beseda.webproject.entity.route.Route;
import by.epam.javatraining.beseda.webproject.entity.route.Task;
import by.epam.javatraining.beseda.webproject.entity.user.Customer;
import by.epam.javatraining.beseda.webproject.service.entityservice.CustomerService;
import by.epam.javatraining.beseda.webproject.service.entityservice.RouteService;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;

@Controller
@PreAuthorize("hasAuthority('" + USER_ADMIN + "')")
@ResponseBody
public class AdminRouteController {

    private static Logger log = Logger.getLogger(ERROR_LOGGER);

    @Autowired
    private CustomerService customerService;

    @Autowired
    private RouteService routeService;

    @PostMapping(value = {"/admin/create_route"})
    public ModelAndView gotoAddNewRoute(HttpServletRequest request, HttpSession session) {
        ModelAndView mav = new ModelAndView();
        Integer requestId = Integer.parseInt(request.getParameter(ID));
        int customerId = Integer.parseInt(request.getParameter(CUSTOMER_ID));
        Customer customer = customerService.getEntityById(customerId);
        request.setAttribute(CUSTOMER, customer);

        String text = request.getParameter(REQUEST_TEXT);
        session.setAttribute(NEW_REQUEST_ID, requestId);
        session.setAttribute(NEW_REQUEST_TEXT, text);
        mav.setViewName(ADD_NEW_ROUTE_PAGE);
        return mav;
    }

    @PostMapping(value = {"/admin/add_new_route"})
    public ModelAndView addNewRoute(HttpServletRequest request, HttpSession session) throws ServiceLayerException {
        ModelAndView mav = new ModelAndView();
        Route route = null;
        int routeId = -1;
        String routeName = Decoder.decode(request.getParameter(ROUTE_NAME));
        routeId = Integer.parseInt(request.getParameter(ID));
        route = routeService.createRoute(routeId, routeName);
        routeService.add(route);
        String[] carsId = request.getParameterValues(CARS_ID);
        for (int i = 0; i < carsId.length; i++) {
            int carId = Integer.parseInt(carsId[i]);
            routeService.addCar(routeId, carId);
        }
        session.setAttribute(CURRENT_ROUTE, route);
        session.setAttribute(TASK_LIST, new ArrayList<Task>());
        mav.setViewName(CurrentPageProcessor.processPage(request.getParameter(CURRENT_PAGE)));
        return mav;
    }


    @PostMapping(value = {"/admin/change_route"})
    public ModelAndView addChangeRoute(@RequestParam String id, HttpSession session) {
        ModelAndView mav = new ModelAndView();
        int routeId = Integer.parseInt(id);
        Map<Request, Customer> requestCustomerMap = (Map<Request, Customer>) session.getAttribute(REQUEST_CUSTOMER_MAP);
        Route route = null;
        Set<Request> keys = requestCustomerMap.keySet();
        for (Request req : keys) {
            if (req.getId() == routeId) {
                route = req.getRoute();
            }
        }
        session.setAttribute(CHANGING_ROUTE, route);
        mav.setViewName(CHANGE_ROUTE_PAGE);
        return mav;
    }

}
