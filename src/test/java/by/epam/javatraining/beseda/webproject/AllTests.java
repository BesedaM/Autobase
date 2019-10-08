package by.epam.javatraining.beseda.webproject;

import java.io.IOException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import by.epam.javatraining.beseda.webproject.integrationtests.databasecreator.DatabaseCreator;


public class AllTests {
	
	@BeforeSuite
	public static void init() {
		DatabaseCreator.init();
	}

	@AfterSuite
	public static void destroy() throws IOException {
		DatabaseCreator.closeDatabase();
	}
}
