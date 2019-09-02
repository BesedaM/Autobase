package by.epam.javatraining.beseda.webproject.controller.command.implementation.post.admin;

import by.epam.javatraining.beseda.webproject.controller.command.ActionCommand;
import by.epam.javatraining.beseda.webproject.controller.command.util.Decoder;
import by.epam.javatraining.beseda.webproject.controller.command.util.constant.CommandConstant;
import by.epam.javatraining.beseda.webproject.controller.command.util.srcontent.SessionRequestContent;
import by.epam.javatraining.beseda.webproject.entity.exception.task.IllegalAddressException;
import by.epam.javatraining.beseda.webproject.entity.route.Address;
import by.epam.javatraining.beseda.webproject.entity.route.Task;
import by.epam.javatraining.beseda.webproject.service.dependenceservice.ServiceDependenceFactory;
import by.epam.javatraining.beseda.webproject.service.dependenceservice.TaskAddressService;
import by.epam.javatraining.beseda.webproject.service.dependenceservice.TaskRouteService;
import by.epam.javatraining.beseda.webproject.service.entityservice.AddressService;
import by.epam.javatraining.beseda.webproject.service.entityservice.ServiceEntityFactory;
import by.epam.javatraining.beseda.webproject.service.entityservice.TaskService;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPParameter.*;
import static by.epam.javatraining.beseda.webproject.controller.command.util.constant.JSPSessionAttribute.TASK_LIST;
import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;

public class AddNewTask implements ActionCommand {

    private static Logger log = Logger.getLogger(ERROR_LOGGER);
    private static ServiceEntityFactory serviceEntityFactory = ServiceEntityFactory.getFactory();
    private static ServiceDependenceFactory serviceDependenceFactory = ServiceDependenceFactory.getFactory();
    private static TaskService taskService = serviceEntityFactory.getTaskService();
    private static AddressService addressService = serviceEntityFactory.getAddressService();
    private static TaskAddressService taskAddressService = serviceDependenceFactory.getTaskAddressService();
    private static TaskRouteService taskRouteService = serviceDependenceFactory.getTaskRouteService();

    @Override
    public String execute(SessionRequestContent content) {
        Map<String, String[]> requestParam = content.requestParameters();
        HttpSession session = content.getSession();

        try {

            int route_id = Integer.parseInt(requestParam.get(ROUTE_ID)[0]);
            String details = Decoder.decode(requestParam.get(DETAILS)[0]);

            Date time = getTime(requestParam);
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(time);

            Address address = getAddress(requestParam);
            addressService.add(address);

            Task task = taskService.createTask(calendar, details);
            taskService.add(task);
            task.setAddress(address);

            taskAddressService.addDependence(task.getId(), address.getId());
            taskRouteService.addDependence(task.getId(), route_id);

            List<Task> taskList = (ArrayList<Task>) session.getAttribute(TASK_LIST);
            taskList.add(task);

        } catch (ServiceLayerException | IllegalAddressException e) {
            log.error(e);
        }
        return requestParam.get(CURRENT_PAGE)[0];
    }


    private Address getAddress(Map<String, String[]> requestParam) throws ServiceLayerException {
        String country = Decoder.decode(requestParam.get(COUNTRY)[0]);
        String district = Decoder.decode(requestParam.get(DISTRICT)[0]);
        String city = Decoder.decode(requestParam.get(CITY)[0]);
        String street = Decoder.decode(requestParam.get(STREET)[0]);
        String houseStr = Decoder.decode(requestParam.get(HOUSE)[0]);
        int house = 0;
        if (houseStr.length() > 0) {
            house = Integer.parseInt(houseStr);
        }
        String building = Decoder.decode(requestParam.get(BUILDING)[0]);
        return addressService.createAddress(country, district, city, street, house, building);
    }


    private Date getTime(Map<String, String[]> requestParam) throws ServiceLayerException {
        String date = Decoder.decode(requestParam.get(DATE)[0]);
        String time = Decoder.decode(requestParam.get(TIME)[0]);

        SimpleDateFormat dateFormat = new SimpleDateFormat(CommandConstant.DATE_FORMAT);
        SimpleDateFormat timeFormat = new SimpleDateFormat(CommandConstant.TIME_FORMAT);
        timeFormat.setTimeZone(TimeZone.getTimeZone(CommandConstant.TIMEZONE));
        Date parsedDate = null;
        Date parsedTime = null;
        try {
            parsedDate = dateFormat.parse(date);
            parsedTime = timeFormat.parse(time);
        } catch (ParseException e) {
            throw new ServiceLayerException(e);
        }
        return new Date(parsedDate.getTime() + parsedTime.getTime());
    }
}
