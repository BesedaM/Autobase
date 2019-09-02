package test;

import by.epam.javatraining.beseda.webproject.controller.command.util.constant.CommandConstant;
import by.epam.javatraining.beseda.webproject.dao.util.connectionpool.DBConnector;
import by.epam.javatraining.beseda.webproject.entity.exception.address.IllegalCityNameException;
import by.epam.javatraining.beseda.webproject.entity.route.Address;
import by.epam.javatraining.beseda.webproject.entity.route.Task;
import by.epam.javatraining.beseda.webproject.service.dependenceservice.ServiceDependenceFactory;
import by.epam.javatraining.beseda.webproject.service.dependenceservice.TaskAddressService;
import by.epam.javatraining.beseda.webproject.service.dependenceservice.TaskRouteService;
import by.epam.javatraining.beseda.webproject.service.entityservice.AddressService;
import by.epam.javatraining.beseda.webproject.service.entityservice.ServiceEntityFactory;
import by.epam.javatraining.beseda.webproject.service.entityservice.TaskService;
import by.epam.javatraining.beseda.webproject.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.util.ReversalHashMap;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class MyTest {

    public static void main(String[] args) {
//        ReversalHashMap<Integer,String> reversalMap=new ReversalHashMap<>();
//
//        reversalMap.put(1,"one");
//        reversalMap.put(2,"two");
//        reversalMap.put(3,"three");
//
//        System.out.println(reversalMap.get(1));
//        System.out.println(reversalMap.getKey("one"));

        ServiceEntityFactory serviceEntityFactory = ServiceEntityFactory.getFactory();
        ServiceDependenceFactory serviceDependenceFactory = ServiceDependenceFactory.getFactory();
        TaskService taskService = serviceEntityFactory.getTaskService();
        AddressService addressService = serviceEntityFactory.getAddressService();
        TaskAddressService taskAddressService = serviceDependenceFactory.getTaskAddressService();
        TaskRouteService taskRouteService = serviceDependenceFactory.getTaskRouteService();

        try {
            String time="23:15";
            String date="2015-12-25";

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

            Date wholeTime=new Date(parsedTime.getTime()+parsedDate.getTime());

            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(wholeTime);

            System.out.println("address added");

            SimpleDateFormat both=new SimpleDateFormat((CommandConstant.DATE_FORMAT+" "+CommandConstant.TIME_FORMAT));

            System.out.println(both.format(wholeTime));

            Task task = taskService.createTask(calendar, "Забрать пассажиров");
            taskService.add(task);

            System.out.println(parsedTime);
            System.out.println(parsedDate);




            System.out.println("task added");


        } catch (ServiceLayerException e) {
            System.out.println(e);
        }

    }
}
