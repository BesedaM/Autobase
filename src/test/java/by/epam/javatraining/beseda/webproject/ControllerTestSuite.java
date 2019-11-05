package by.epam.javatraining.beseda.webproject;

import by.epam.javatraining.beseda.webproject.controller.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({LoginControllerTest.class, RegisterControllerTest.class,
        LocaleChangerControllerTest.class,DriverControllerTest.class,
        CustomerControllerTest.class, AdminTaskControllerTest.class,
        AdminRouteControllerTest.class,AdminRequestsControllerTest.class,
        AdminCarsControllerTest.class})
public class ControllerTestSuite {
}
