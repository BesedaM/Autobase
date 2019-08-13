package by.epam.javatraining.beseda.webproject.model.command.implementation.parts;

import by.epam.javatraining.beseda.webproject.model.entity.Request;
import by.epam.javatraining.beseda.webproject.model.entity.car.Car;
import by.epam.javatraining.beseda.webproject.model.entity.route.Route;
import by.epam.javatraining.beseda.webproject.model.entity.user.Customer;
import by.epam.javatraining.beseda.webproject.model.entity.user.Driver;
import by.epam.javatraining.beseda.webproject.model.service.EnumService;
import by.epam.javatraining.beseda.webproject.model.service.dependenceservice.CarDriverService;
import by.epam.javatraining.beseda.webproject.model.service.entityservice.DriverService;
import by.epam.javatraining.beseda.webproject.model.service.entityservice.RequestService;
import by.epam.javatraining.beseda.webproject.model.service.entitybuilder.RequestBuilder;
import by.epam.javatraining.beseda.webproject.model.service.exception.ServiceLayerException;
import by.epam.javatraining.beseda.webproject.model.service.factory.EntityBuilderFactory;
import by.epam.javatraining.beseda.webproject.model.service.factory.ServiceDependenceFactory;
import by.epam.javatraining.beseda.webproject.model.service.factory.ServiceEntityFactory;

import javax.servlet.http.HttpSession;
import java.util.*;

import static by.epam.javatraining.beseda.webproject.model.command.util.constant.JSPSessionAttribute.*;
import static by.epam.javatraining.beseda.webproject.model.service.ServiceConstants.REQUEST_STATUS;

public class CustomerProcessor {

    private static EntityBuilderFactory entityBuilderFactory = EntityBuilderFactory.getFactory();
    private static ServiceEntityFactory serviceEntityFactory = ServiceEntityFactory.getFactory();
    private static ServiceDependenceFactory serviceDependenceFactory = ServiceDependenceFactory.getFactory();
    private static Map<String, HashMap> enumCollection = EnumService.getEnumCollection();

    public static void processCustomerData(HttpSession session) throws ServiceLayerException {

        RequestService requestService = serviceEntityFactory.getRequestService();
        RequestBuilder requestBuilder = entityBuilderFactory.getRequestBuilder();
        Customer customer=(Customer)session.getAttribute(USER_DATA);

        int[] requestId = requestService.selectActiveCustomerRequestsId(customer.getId());

        List<Request> requestList = new ArrayList<>();
        Map<Integer, List<Driver>> driverMap = new HashMap<>();
        for (int i = 0; i < requestId.length; i++) {
            Request req = requestBuilder.getEntity(requestId[i]);
            requestList.add(req);
            List<Driver> driverList = addDriversData(req.getRoute());
            driverMap.put(req.getId(), driverList);
        }

        session.setAttribute(REQUEST_LIST, requestList);
        session.setAttribute(REQUEST_STATUS_LIST, enumCollection.get(REQUEST_STATUS));
        session.setAttribute(DRIVER_MAP, driverMap);
    }

    private static List<Driver> addDriversData(Route route) throws ServiceLayerException {
        List<Driver> driverList = new ArrayList<>();
        if (route != null) {
            CarDriverService carDriverService = serviceDependenceFactory.getCarDriverService();
            DriverService driverService = serviceEntityFactory.getDriverService();
            List<Car> carList = route.getCarsList();

            for (int i = 0; i < carList.size(); i++) {
                int driverId = carDriverService.getEntity02Id(carList.get(i));
                Driver driver = driverService.getEntityById(driverId);
                driverList.add(driver);
            }
        }
        return driverList;
    }


}
