package by.epam.javatraining.beseda.webproject.model.command.implementation.post.admin;

import by.epam.javatraining.beseda.webproject.model.command.ActionCommand;
import by.epam.javatraining.beseda.webproject.model.command.util.srcontent.SessionRequestContent;
import by.epam.javatraining.beseda.webproject.model.entity.Request;
import by.epam.javatraining.beseda.webproject.model.entity.car.Car;
import by.epam.javatraining.beseda.webproject.model.entity.route.Route;
import by.epam.javatraining.beseda.webproject.model.entity.route.Task;
import by.epam.javatraining.beseda.webproject.model.entity.user.Customer;
import by.epam.javatraining.beseda.webproject.model.service.dependenceservice.CarRouteService;
import by.epam.javatraining.beseda.webproject.model.service.dependenceservice.RequestCustomerService;
import by.epam.javatraining.beseda.webproject.model.service.entitybuilder.RouteBuilder;
import by.epam.javatraining.beseda.webproject.model.service.entityservice.CarService;
import by.epam.javatraining.beseda.webproject.model.service.entityservice.CustomerService;
import by.epam.javatraining.beseda.webproject.model.service.entityservice.RequestService;
import by.epam.javatraining.beseda.webproject.model.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.model.service.factory.EntityBuilderFactory;
import by.epam.javatraining.beseda.webproject.model.service.factory.ServiceDependenceFactory;
import by.epam.javatraining.beseda.webproject.model.service.factory.ServiceEntityFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import java.util.*;

import static by.epam.javatraining.beseda.webproject.model.command.util.constant.JSPPath.NEW_REQUESTS_PAGE;
import static by.epam.javatraining.beseda.webproject.model.command.util.constant.JSPSessionAttribute.CAR_BUSY_DATES;
import static by.epam.javatraining.beseda.webproject.model.command.util.constant.JSPSessionAttribute.REQUEST_CUSTOMER_MAP;
import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;

public class AdminGetNewRequests implements ActionCommand {

    private static Logger log = Logger.getLogger(ERROR_LOGGER);
    private ServiceEntityFactory serviceEntityFactory = ServiceEntityFactory.getFactory();
    private ServiceDependenceFactory serviceDependenceFactory = ServiceDependenceFactory.getFactory();

    @Override
    public String execute(SessionRequestContent content) {
        Map<Request, Customer> requestCustomerMap = new HashMap<>();
        HttpSession httpSession = content.getSession();
        RequestService requestService = serviceEntityFactory.getRequestService();
        CustomerService customerService = serviceEntityFactory.getCustomerService();
        CarService carService = serviceEntityFactory.getCarService();
        RequestCustomerService requestCustomerService = serviceDependenceFactory.getRequestCustomerService();

        try {
            List<Request> requestList = requestService.getNewRequests();
            Collections.sort(requestList);
            for (int i = 0; i < requestList.size(); i++) {
                int customerId = requestCustomerService.getEntity02Id(requestList.get(i));
                Customer customer = customerService.getEntityById(customerId);
                requestCustomerMap.put(requestList.get(i), customer);
            }

            Map<Car, SortedSet<Date>> carSetMap = new TreeMap<>();

            List<Car> carList = carService.getAll();

            for (int i = 0; i < carList.size(); i++) {
                SortedSet<Date> dates = getCarBusyDates(carList.get(i));
                carSetMap.put(carList.get(i), dates);
            }

            httpSession.setAttribute(REQUEST_CUSTOMER_MAP, requestCustomerMap);
            httpSession.setAttribute(CAR_BUSY_DATES, carSetMap);

        } catch (ServiceLayerException e) {
            log.error(e);
        }

        return NEW_REQUESTS_PAGE;
    }


    private SortedSet<Date> getCarBusyDates(Car car) throws ServiceLayerException {
        CarRouteService carRouteService = serviceDependenceFactory.getCarRouteService();
        RouteBuilder routeBuilder = EntityBuilderFactory.getFactory().getRouteBuilder();
        SortedSet<Date> dates = new TreeSet<>();

        int[] routesId = carRouteService.getActivePlannedRoutesId(car);

        for (int i = 0; i < routesId.length; i++) {
            Route route = routeBuilder.getEntity(routesId[i]);
            List<Task> taskList = route.getTasksList();
            for (int j = 0; j < taskList.size(); j++) {
                GregorianCalendar gregorianCalendar=taskList.get(j).getTime();
                gregorianCalendar.set(Calendar.AM_PM,0);
                gregorianCalendar.set(Calendar.HOUR,0);
                gregorianCalendar.set(Calendar.MINUTE,0);
                gregorianCalendar.set(Calendar.SECOND,0);
                gregorianCalendar.set(Calendar.MILLISECOND,0);
                Date date = gregorianCalendar.getTime();
                dates.add(date);
            }
        }
        return dates;
    }
}
