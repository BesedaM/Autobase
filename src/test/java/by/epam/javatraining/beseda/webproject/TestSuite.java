package by.epam.javatraining.beseda.webproject;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import by.epam.javatraining.beseda.webproject.dao.entitydao.CarDAOTest;
import by.epam.javatraining.beseda.webproject.dao.entitydao.DriverDAOTest;
import by.epam.javatraining.beseda.webproject.dao.entitydao.EnumDAOTest;
import by.epam.javatraining.beseda.webproject.dao.entitydao.RequestDAOTest;
import by.epam.javatraining.beseda.webproject.dao.entitydao.RouteDAOTest;
import by.epam.javatraining.beseda.webproject.dao.entitydao.TaskDAOTest;
import by.epam.javatraining.beseda.webproject.dao.entitydao.UserDAOTest;

@RunWith(Suite.class)
@SuiteClasses({ EnumDAOTest.class, CarDAOTest.class, TaskDAOTest.class, RouteDAOTest.class, UserDAOTest.class, RequestDAOTest.class,
		DriverDAOTest.class })
public class TestSuite {


}
